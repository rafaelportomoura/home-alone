import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
public class Interacao extends JPanel{
    private JTextField input;
    private JTextArea output;

    public Interacao(){
        input = new JTextField("", 10);

        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Pensar em alguma forma eficiente de capturar o input.getText() 
                System.out.println("texto digitado: " + input.getText()); //IMprime o valor digitado
                input.setText(""); // limpa o campo de texto
            }
        });
        input.setBackground(Color.red);

        output = new JTextArea();
        output.setColumns(4);
        output.setRows(10);
        output.setEnabled(false);
        output.setBackground(Color.black);
        montarPainel();
    }

    public void montarPainel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(output, "Center");
        add(input, "Center");
    }

    public void setOutput(String valor){
        output.setText(valor);
    }

}