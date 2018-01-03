import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	public static void readOut(){
		Scanner scanner = new Scanner(System.in);
		try{
			DocReader reader = new DocReader("copy.doc");
			String[] aryLines = reader.openFile();
			
			for (int i = 0; i < aryLines.length; i++){
				System.out.println(aryLines[i]);
			}
					//writing to file
		
		DocWriter data = new DocWriter("copy.doc", true);
		String text = scanner.nextLine();
		data.writeToDoc("This is different");
		
		System.out.println("file written to");
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}

	}
	
	public static void main(String[] args) throws IOException{
	
		int filesize = 2022386;
		int bytesRead;
		int currentTot;

		Socket socket = new Socket(Store.kf_ip,Store.port);
		byte[] bytearray = new byte[filesize];
		InputStream is = socket.getInputStream();
		FileOutputStream fos = new FileOutputStream("copy.doc");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bytesRead = is.read(bytearray,0,bytearray.length);
		currentTot = bytesRead;

		do{
		  bytesRead =
			is.read(bytearray,currentTot, (bytearray.length-currentTot));
		  if (bytesRead >= 0) currentTot += bytesRead;
		} while(bytesRead > -1);

		bos.write(bytearray,0,currentTot);
		bos.flush();
		bos.close();

		socket.close();
		
		readOut();

	}
}
