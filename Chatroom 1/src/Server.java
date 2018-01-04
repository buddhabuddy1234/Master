
public class Server implements Runnable {
	
	public void run() {
	
	}
	public static void main(String args[]) {
		(new Thread(new Server())).start();
	}
}
