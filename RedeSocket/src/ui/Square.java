package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Square extends JPanel {
    JLabel label = new JLabel();

    public Square() {
        setBackground(Color.white);
        add(label);
    }

    public void setLetter(String letter) {
        label.setText(letter);
        label.setFont(new Font("Serif", Font.PLAIN, 40));
    }
}


