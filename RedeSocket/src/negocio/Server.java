package negocio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import negocio.Player;
import negocio.Game;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(4321);
		System.out.println("Servidor em rede");
		try {
			while (true) {
				Game game = new Game();
				Player playerX = new Player(server.accept(), "X", game);
				Player playerO = new Player(server.accept(), "O", game);
	            playerX.setOpponent(playerO);
	            playerO.setOpponent(playerX);
	            game.currentPlayer = playerX;
	            playerX.start();
	            playerO.start();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.close();
		}
	}
}
