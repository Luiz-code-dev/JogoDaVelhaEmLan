package negocio;

public class App {
	public static void main(String[] args) throws Exception {
		while (true) {
	        Client client = new Client();
	        client.play();
	        if (!client.wantsToPlayAgain()) {
	            break;
	        }
	    }
	}
}
