
import javax.swing.*;
import java.awt.*;

public class GUI{
    private JFrame janela;
    
    private Interacao entradaComandos; // Sul
   
    private JLabel imgMapa; // Centro
    private infoJogador conEsquerda; // Conteudos a esquerda da GUI
    private infoJogo conDireita; // || || direita || || 


    public GUI(){
        //Nomear a janela
        janela = new JFrame("Home Alone");

        imgMapa = new JLabel(new ImageIcon("mapa.png"),0);
        imgMapa.setBackground(Color.black);
        //Configurar a area de entrada de comandos
        entradaComandos = new Interacao();
        //entradaComandos.setText("Digite help para obter ajuda com os comandos\n>");
       // entradaComandos.setRows(15);
        //entradaComandos.setColumns(20);

        conEsquerda = new infoJogador();
        conDireita = new infoJogo();

        montarJanela();
        exibir();
    }

    private void montarJanela(){
        janela.setLayout(new BorderLayout());
        janela.add(BorderLayout.CENTER, imgMapa);
        janela.add(BorderLayout.SOUTH, entradaComandos);
        janela.add(BorderLayout.WEST, conEsquerda);
        janela.add(BorderLayout.EAST, conDireita);
        janela.pack();
        janela.setSize(1080,750); // Define o tamanho da janela;
        janela.setLocation(400,200); // Define o local na tela onde a janela aparecerá assim que o programa for executado
        //janela.setDefaultOperation(JFrame. EXIT_ON_CLOSE); // Quando finalizado pela interface, o programa é encerrado

    }

    public void exibir(){
        janela.setVisible(true);
    }

    public void setVida(String valor){
        conEsquerda.setVida(valor);
    }

    public void setTempo(String valor){
        conEsquerda.setTempo(valor);
    }

    public void setChave(String valor){
        conEsquerda.setChave(valor);
    }

    public void setEnigma(String valor){
        conDireita.setEnigma(valor);
    }

    public void setDificuldade(String valor){
        conDireita.setDificuldade(valor);
    }

    public void setOutput(String valor){
        entradaComandos.setOutput(valor);
    }
}
