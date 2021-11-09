
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.ComponentOrientation;
public class GUI{
    private JFrame janela;
    // Sul
    private JTextArea entradaComandos;
    // Centro
    private JLabel imgMapa;
    private infoJogador conEsquerda; // Conteudos a esquerda da GUI


    public GUI(){
        //Nomear a janela
        janela = new JFrame("Home Alone");

        imgMapa = new JLabel(new ImageIcon("mapa.png"),0);
        //Configurar a area de entrada de comandos
        entradaComandos = new JTextArea();
        entradaComandos.setText("Digite help para obter ajuda com os comandos\n>");
        entradaComandos.setRows(15);
        entradaComandos.setColumns(20);

        conEsquerda = new infoJogador();

        montarJanela();
    }

    private void montarJanela(){
        janela.setLayout(new BorderLayout());
        janela.add(BorderLayout.CENTER, imgMapa);
        janela.add(BorderLayout.SOUTH, entradaComandos);
        janela.add(BorderLayout.WEST, conEsquerda);
        janela.pack();
        janela.setSize(1200,1200); // Define o tamanho da janela;
        janela.setLocation(400,200); // Define o local na tela onde a janela aparecerá assim que o programa for executado
        //janela.setDefaultOperation(JFrame. EXIT_ON_CLOSE); // Quando finalizado pela interface, o programa é encerrado

    }

    public void exibir(){
        janela.setVisible(true);
    }

}