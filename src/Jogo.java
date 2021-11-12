import java.util.Objects;
import java.util.Scanner;
import java.util.TimerTask;

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

public class Jogo extends TimerTask {

    private Player player;
    private GUI gui;
    private Configuration configuration;
    private String input = "";
    private Analisador analisador;

    public Jogo() {
        gui = new GUI(this);
        analisador = new Analisador();
    }

    public void jogar() {

        gui.exibir();

        configuration = Configuration.getConfiguration(1);
        player = new Player(configuration.getAmbiente(1));
        gui.setDificuldade("Normal");

        boolean terminado = false;
        while (!terminado) {
            // if(!inputAtual.equals(input)){
            // System.out.println("Configuration é null? " +
            // !Objects.nonNull(configuration));
            Comando comando = analisador.pegarComando(input);
            terminado = processarComando(comando);
            if (Objects.nonNull(configuration)) {
                imprimirOpcoes();
            }
            gui.setVida(String.valueOf(player.getVida()));
            // inputAtual = input;
            // System.out.println("Setando inputAtual " + inputAtual);
            // System.out.println("input " + input + " atual " + inputAtual);
            // }
            // System.out.println("input " + input + " atual " + inputAtual);

        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    // private int imprimirBoasVindas(){

    // gui.setOutput("Bem-vindo ao Home Alone\nSelecione a dificuldade\n1 -
    // normal\n2 - dificil\n");

    // }

    private void imprimirOpcoes() {
        String output = "Comodo atual: " + player.getAmbiente().getNome() + "\nOpcoes de saida: ";

        int[] saidas = player.getAmbiente().getSaidas();

        for (int i = 0; i < saidas.length; i++) {
            output += " [ " + saidas[i] + " " + configuration.getAmbiente(saidas[i]).getNome() + " ] ";
        }

        gui.setOutput(output);
    }

    private void imprimirDificuldade() {
        String output = "Escolha a dificuldade: \n";

        output += "1 - normal\n";
        output += "2 - dificil\n";

        gui.setOutputConcat(output);
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
    private boolean processarComando(Comando comando)
    {
        if(!Objects.nonNull(comando)){
            return false;
        }

        boolean querSair = false;

        if (comando.ehDesconhecido()) {
            // System.out.println("Eu nao entendi o que voce disse...");
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
                System.out.println("SETANDO CONFIGURATION");
            } else {
                gui.setOutput("Dificuldade já foi declarada");
            }
        } else if (palavraDeComando.equals("buscar")) {
            String item = player.buscar();
            if (Objects.nonNull(item)) {
                String output;
                if (item.equals("dica")) {
                    gui.setEnigma("o enigma - viih tube");
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
        } else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
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

         if(player.getAmbiente().equals(configuration.getAmbiente(Integer.parseInt(comando.getSegundaPalavra())))){
             return;
         }

        player.mover(configuration.getAmbiente(Integer.parseInt(comando.getSegundaPalavra())));

    }

    /**
    * "Sair" foi digitado. Verifica o resto do comando pra ver
    * se nos queremos realmente sair do jogo.
    * @return true, se este comando sai do jogo, false, caso contrario
    */
    private boolean sair(Comando comando)
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        } else {
            return true; // sinaliza que nos queremos sair
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

}
