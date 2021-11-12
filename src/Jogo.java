import java.util.Objects;
import java.util.TimerTask;
import java.util.Timer;
import java.util.List;

/**
 * Essa eh a classe principal da aplicacao "World of Zull". "World of Zuul" eh
 * um jogo de aventura muito simples, baseado em texto. Usuarios podem caminhar
 * em um cenario. E eh tudo! Ele realmente precisa ser estendido para fazer algo
 * interessante!
 *
 * Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 * "jogar".
 *
 * Essa classe principal cria e inicializa todas as outras: ela cria os
 * ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e executa os
 * comandos que o analisador retorna.
 *
 * @author Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo {

    private Player player;
    private GUI gui;
    private Configuration configuration;
    private String input = "";
    private Analisador analisador;
    private Timer timer;
    private Arquivo arquivo;
    private CronJob cronJob;

    public Jogo() {
        gui = new GUI(this);
        analisador = new Analisador();
        arquivo = new Arquivo();
    }

    public void jogar() {

        gui.exibir();

        configuration = Configuration.getConfiguration(1);
        player = new Player(configuration.getAmbiente(1));
        timer = new Timer();
        cronJob = new CronJob();
        timer.schedule(cronJob, 0, 1000);
        gui.setDificuldade("Normal");
        arquivo.salvarChave(configuration.getComodoComAChave());
        boolean terminado = false;
        boolean morreu = false;
        while (!terminado) {
            if (player.getVida() == 0) {
                terminado = true;
                morreu = true;
                gui.setOutput("OHHH NOOOO VC MORREUUUUU");
            } else {
                if (cronJob.getAmbientTime() >= 5) {
                    Comando comando = analisador.pegarComando(input);
                    terminado = processarComando(comando);
                    if (Objects.nonNull(configuration)) {
                        imprimirOpcoes();
                        gui.setVida(String.valueOf(player.getVida()));
                    }
                }
            }
        }
        timer.cancel();
        int vidaRestante = player.getVida();
        int tempoTotal = cronJob.getTotalTime();
        boolean encontrouAChave = player.getTemChave();
        arquivo.salvarHistorico(new Historico(vidaRestante, tempoTotal, encontrouAChave, morreu));

    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */

    private void imprimirOpcoes() {
        String output;

        output = "Comandos disponiveis: ir <nº >";

        output += "Comodo atual: " + player.getAmbiente().getNome() + "\nOpcoes de saida: ";

        int[] saidas = player.getAmbiente().getSaidas();

        for (int i = 0; i < saidas.length; i++) {
            output += " [ " + saidas[i] + " " + configuration.getAmbiente(saidas[i]).getNome() + " ] ";
        }

        gui.setOutput(output);
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) {
        if (!Objects.nonNull(comando)) {
            return false;
        }

        boolean querSair = false;

        if (comando.ehDesconhecido()) {
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();

        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        } else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        } else if (palavraDeComando.equals("dificuldade")) {
            if (!Objects.nonNull(configuration)) {
                configuration = Configuration.getConfiguration(Integer.parseInt(comando.getSegundaPalavra()));
                player = new Player(configuration.getAmbiente(1));
            } else {
                gui.setOutput("Dificuldade já foi declarada");
            }
        } else if (palavraDeComando.equals("buscar")) {
            String item = player.buscar();
            if (Objects.nonNull(item) && !item.equals("nao buscou")) {
                cronJob.resetAmbientTime();
            }
            if (Objects.nonNull(item)) {
                String output;
                if (item.equals("dica")) {
                    gui.setEnigma(Configuration.getEnigma(configuration.getQuartoEscolhido()));
                    output = "Dica encontrada!";
                } else if (item.equals("chave")) {
                    gui.setChave("Encontrada");
                    output = "Chave encontrada!";
                } else {
                    output = "Nada foi encontrado!";
                }
                gui.setOutputConcat(output);
            }
        } else if (palavraDeComando.equals("abrir") && player.getTemChave()) {
            querSair = true;
        } else if (palavraDeComando.equals("ranking")) {
            String output = "";
            List<Historico> ranking = arquivo.getRanking();
            output += ranking.get(0).rankingTemplate();
            output += ranking.get(1).rankingTemplate();
            output += ranking.get(2).rankingTemplate();
            gui.setOutput(output);
            imprimirOpcoes();
        }

        return querSair;
    }

    /**
     * Printe informacoes de ajuda. Aqui nos imprimimos algo bobo e enigmatico e a
     * lista de palavras de comando
     */
    private void imprimirAjuda() {
        gui.setOutput(
                "Voce esta perdido. Voce esta sozinho. Voce caminha\npela universidade.\n\nSuas palavras de comando sao:\n   ir sair ajuda");
    }

    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            gui.setOutput("Ir pra onde?");
            return;
        }

        /*
         * fazer player andar
         */

        if (player.getAmbiente().equals(configuration.getAmbiente(Integer.parseInt(comando.getSegundaPalavra())))) {
            return;
        }

        player.mover(configuration.getAmbiente(Integer.parseInt(comando.getSegundaPalavra())));

    }

    private class CronJob extends TimerTask {
        private int ambientTime;
        private int totalTime;

        public CronJob() {
            ambientTime = 5;
            totalTime = 0;
        }

        @Override
        public void run() {
            player.perderVida(configuration.getPercaDeVida());
            ambientTime += 1;
            totalTime += 1;
            if (player.getVida() <= 0) {
                timer.cancel();
            }
        }

        public int getAmbientTime() {
            return ambientTime;
        }

        public void resetAmbientTime() {
            this.ambientTime = 0;

        }

        public int getTotalTime() {
            return totalTime;
        }
    }

}
