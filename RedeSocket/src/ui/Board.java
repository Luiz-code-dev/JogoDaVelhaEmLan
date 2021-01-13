package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Square;

public class Board extends JFrame{
     
	private JLabel messageLabel = new JLabel("");
	private Square[] squares = new Square[9];
    private Square currentSquare;
	public Board(PrintWriter out) {
		
		 this.messageLabel.setBackground(Color.lightGray);
	     this.getContentPane().add(this.messageLabel, "South");
	
	     JPanel boardPanel = new JPanel();
	     boardPanel.setBackground(Color.black);
	     boardPanel.setLayout(new GridLayout(3, 3, 2, 2));
	     for (int i = 0; i < this.squares.length; i++) {
	         final int j = i;
	         this.squares[i] = new Square();
	         this.squares[i].addMouseListener(new MouseAdapter() {
	         public void mousePressed(MouseEvent e) {
	        	 currentSquare = squares[j];
	                 out.println("MOVE " + j);
	         	}
	         });
	         boardPanel.add(this.squares[i]);
	     }
     this.getContentPane().add(boardPanel, "Center");
	}
	public JLabel getMessageLabel() {
		return this.messageLabel;
	}
	public Square getCurrentSquare() {
		return currentSquare;
	}
	public void setMessageLabelText(String messsage) {
		this.messageLabel.setText(messsage);
	}
	public void setCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
	}
	public void setLetter(String letter, int square) {
		this.squares[square].setLetter(letter);
		this.squares[square].repaint();
	}
	public void setLetter(String letter) {
		this.getCurrentSquare().setLetter(letter);
		this.getCurrentSquare().repaint();
	}
	
}


