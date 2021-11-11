import javax.swing.*;
import java.awt.*;
public class infoJogo extends JPanel{
    private JLabel labelEnigma;
    private JLabel valorEnigma;

    private JLabel espaco;

    private JLabel labelDificuldade;
    private JLabel valorDificuldade;
    
    public infoJogo(){
        labelEnigma = new JLabel("Enigma:",SwingConstants.LEFT);
        valorEnigma = new JLabel("andam dizendo que o tuê é o demonio :)",SwingConstants.LEFT);
        espaco = new JLabel("   ",SwingConstants.LEFT);
        labelDificuldade = new JLabel("Dificuldade:",SwingConstants.LEFT);
        valorDificuldade = new JLabel("Dificil",SwingConstants.CENTER);
        setBackground(Color.black);

        montarPainel();
    }

    public void montarPainel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(labelEnigma);
        add(valorEnigma);

        add(espaco);

        add(labelDificuldade);
        add(valorDificuldade);
    }

    public void setEnigma(String valor){
        valorEnigma.setText(valor);
    }

    public void setDificuldade(String valor){
        valorDificuldade.setText(valor);
    }

}