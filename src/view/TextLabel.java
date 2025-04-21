package view;

import javax.swing.JLabel;
import java.awt.Color;

public class TextLabel extends JLabel implements Observer {

    public TextLabel() {
        super("",JLabel.CENTER);
        this.setSize(250,100);
    }

    public void newText(String text, Color color) {
        this.setText(text);
        this.setForeground(color);
    }
}
