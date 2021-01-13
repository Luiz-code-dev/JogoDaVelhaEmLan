package negocio;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.Board;
public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Board board;
    private String letter;
    private String opponentLetter;
    
	public Client() {
		try {
			this.socket = new Socket("localhost", 4321);
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.out = new PrintWriter(this.socket.getOutputStream(),true);
				
			this.board = new Board(out);
			this.board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.board.setSize(550, 360);
			this.board.setVisible(true);
			this.board.setResizable(false);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() throws Exception {
		String response;
	    try {
	    	response = in.readLine();
	        if (response.startsWith("WELCOME")) {
	        	char iLetter = response.charAt(8);
	        	letter = (iLetter == 'X' ? "X" : "O");
	            opponentLetter = (iLetter == 'X' ? "O" : "X");
	            this.board.setTitle("Jogo da Velha - Jogador " + letter);
	        }
	        while (true) {
	        	response = this.in.readLine();
	            if (response.startsWith("VALID_MOVE")) {
	            	this.board.setMessageLabelText("Aguardando o Oponente...");
	            	this.board.setLetter(letter);
	            } else if (response.startsWith("OPPONENT_MOVED")) {
	            int loc = Integer.parseInt(response.substring(15));
	            	this.board.setLetter(opponentLetter, loc);
	            	this.board.setMessageLabelText("Seu Turno!");
	            } else if (response.startsWith("VICTORY")) {
	            	this.board.setMessageLabelText("Você Ganhou!!!!");
	                break;
	            } else if (response.startsWith("DEFEAT")) {
	            	this.board.setMessageLabelText("Você Perdeu!!!!");
	                break;
	            } else if (response.startsWith("TIE")) {
	            	this.board.setMessageLabelText("Empate!");
	                break;
	            } else if (response.startsWith("MESSAGE")) {
	            	this.board.setMessageLabelText(response.substring(8));
	            }
	        }
	        out.println("QUIT");
	        }
	        finally {
	            socket.close();
	        }
	    }

	public boolean wantsToPlayAgain() {
		int response = JOptionPane.showConfirmDialog(this.board,
				"Nova Partida?",
	            "Jogo da Velha",
	            JOptionPane.YES_NO_OPTION);
	        this.board.dispose();
	        return response == JOptionPane.YES_OPTION;
	}
	
}
