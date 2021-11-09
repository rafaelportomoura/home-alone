import javax.swing.*;
import java.awt.*;
public class infoJogador extends JPanel{
    private JLabel labelVida;
    private JLabel valorVida;
    
    public infoJogador(){
        labelVida = new JLabel("Vida Restante",SwingConstants.LEFT);
        valorVida = new JLabel("100",SwingConstants.LEFT);
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(labelVida);
        add(valorVida);
    }
}