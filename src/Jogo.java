import java.util.Objects;
import java.util.Scanner;

/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 *
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 *
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e
 *  executa os comandos que o analisador retorna.
 *
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo
{

    private Player player;
    private Configuration configuration;
    private Scanner sc = new Scanner(System.in);


    public Jogo(){
    }


    public void jogar()
    {
        int dificuldade = imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.

        configuration = new Configuration(dificuldade);
        player = new Player(configuration.getAmbiente(1));

        boolean terminado = false;
        int escolha;
        while (!terminado) {
            int[] saidas = player.getAmbiente().getSaidas();

            System.out.println("ESTOU NO " + player.getAmbiente().getNome());
            for(int i=0; i < saidas.length;  i++){
                System.out.println(i + " " + configuration.getAmbiente(saidas[i]).getNome());
            }

            int maxValue = saidas.length;

            System.out.println(maxValue + 1 + " buscar");
            if(player.getAmbiente() instanceof Dispensa){
                System.out.println(maxValue + 2 + " tentar abrir");
            }
            System.out.println();

            escolha = sc.nextInt();

            if(escolha < maxValue){
                player.mover(configuration.getAmbiente(saidas[escolha]));
            }else{
                if(escolha == maxValue+1){
                    String resultado = player.buscar();

                    if(Objects.nonNull(resultado)){
                        switch(resultado){
                            case "dica":
                                //emite dica pra GUI
                                System.out.println("Achou dica");
                                break;
                            default:
                                //emite achou chave para gui
                                System.out.println("Achou chave");
                        }
                    }
                }else if(escolha == maxValue+2 && player.getAmbiente() instanceof Dispensa){
                    // tentar abrir dispensa
                    if(player.getTemChave()){
                        terminado = true;
                    }
                }else{
                    System.out.println("Escolha invalida");
                }
            }

        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private int imprimirBoasVindas(){
        System.out.println();
        System.out.println("Bem-vindo ao Home Alone");
        System.out.println("Selecione a dificuldade");
        System.out.println("1 - normal");
        System.out.println("2 - dificil");
        System.out.print("> ");
        int dificuldade = sc.nextInt();

        return dificuldade;
    }

}
