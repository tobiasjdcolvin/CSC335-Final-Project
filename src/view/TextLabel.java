package view;

import javax.swing.JLabel;

public class TextLabel extends JLabel implements Observer {

    public TextLabel() {
        super("This is the text before user input",JLabel.CENTER);
        this.setSize(250,100);
    }

    public void newText(String text) {
        this.setText(text);
    }
}
