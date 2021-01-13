package negocio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import negocio.Game;

public class Player extends Thread {
    private String letter;
    private Player opponent;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private Game game;

    public Player(Socket socket, String letter, Game game) {
        this.socket = socket;
        this.letter = letter;
        this.game = game;
        try {
            input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("WELCOME " + letter);
            output.println("MESSAGE Aguardando os Jogadores se Conectarem...");
        } catch (IOException e) {
            System.out.println("Jogador Desconectado: " + e);
        }
    }
    
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
    
     public Player getOpponent() {
		return opponent;
	}
 
    public void otherPlayerMoved(int location) {
        output.println("OPPONENT_MOVED " + location);
        output.println(this.game.hasWinner() ? "DEFEAT" : this.game.boardFilledUp() ? "TIE" : "");
    }
    public void run() {
        try {
            output.println("MESSAGE jogadores Conectados");
            if (letter == "X") {
                output.println("MESSAGE Seu Turno!");
            }
            while (true) {
                String command = input.readLine();
                if (command.startsWith("MOVE")) {
                    int location = Integer.parseInt(command.substring(5));
                    if (game.legalMove(location, this)) {
                        output.println("VALID_MOVE");
                        output.println(game.hasWinner() ? "VICTORY" : game.boardFilledUp() ? "TIE" : "");
                    } else {
                        output.println("MESSAGE Movimento Invalido!");
                    }
                } else if (command.startsWith("QUIT")) {
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Jogador Desconectado: " + e);
        } finally {
            try {socket.close();} catch (IOException e) {}
        }
    }
}
