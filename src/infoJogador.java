import javax.swing.*;
import java.awt.*;
public class infoJogador extends JPanel{
    private JLabel labelVida;
    private JLabel valorVida;

    private JLabel labelTempo;
    private JLabel valorTempo;

    private JLabel espaco1;
    private JLabel espaco2;

    private JLabel chave;
    
    public infoJogador(){
        labelVida = new JLabel("Vida Restante:",SwingConstants.LEFT);
        valorVida = new JLabel("100",SwingConstants.LEFT);
        espaco1 = new JLabel("   ",SwingConstants.LEFT);
        labelTempo = new JLabel("Tempo do revista de ambiente restante:",SwingConstants.LEFT);
        valorTempo = new JLabel("5 Segundos",SwingConstants.CENTER);
        espaco2 = new JLabel("   ",SwingConstants.LEFT);
        chave = new JLabel("Chave: Não encontrada",SwingConstants.CENTER); 
        setBackground(Color.white);
 
        criarPainel();

    }

    public void criarPainel(){
                
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(labelVida);
        add(valorVida);

        add(espaco1);

        add(labelTempo);
        add(valorTempo);

        add(espaco2);

        add(chave);
    }

    public void setVida(String valor){
        valorVida.setText(valor);
    }

    public void setTempo(String valor){
        valorTempo.setText(valor + " Segundos");
    }

    public void setChave(String valor){
        chave.setText("Chave: " + valor);
    }

}