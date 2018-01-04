import java.io.*;
import java.net.*;
import java.util.Scanner;
/*
* Sends out socket connection to IP and Port
* Takes in InputStream from Socket and through OutputStream writes into path
* BufferedOutputStream does something.
*/
public class Client {
	public static String path = "copy.doc";
	public static Scanner scanner = new Scanner(System.in);

	public static void readOut(){
		try{
			
			DocReader reader = new DocReader(path);
			//creates an array of lines of text in reader's file
			String[] aryLines = reader.openFile();
			//goes through all lines of array and prints
			for (int i = 0; i < aryLines.length; i++){
				System.out.println(aryLines[i]);
			}
		
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}

	}
	
	public static void writeIn(){
		try{
						//writing to file
						
			//creates writer for path & append == false
			DocWriter data = new DocWriter(path, false);
			
			System.out.println("Please enter text to be written to " + path);
			String text = scanner.nextLine();
			
			data.writeToDoc(text);
			
			System.out.println("file written to");
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws IOException{
		//maximum size of file
		int filesize = 2022386;
		//number of bytes read
		int bytesRead;
		//total bytes read
		int currentTot;
		
		//new Socket at IP, Port. Connects with Server's socket and makes equivalent socket
		Socket socket = new Socket(Store.kf_ip,Store.port);
		//new byte array at maximum file size of filesize
		byte[] bytearray = new byte[filesize];
		//gets server's OutputStream as this InputStream
		InputStream is = socket.getInputStream();
		//sets OutputStream to write into path
		FileOutputStream fos = new FileOutputStream(path);
		//new Buffer of OutputStream so can get OutputStream without having to call socket's output stream every time
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		//stores the number of bytes read from bytearray, starting at 0, going to the length of bytearray
		bytesRead = is.read(bytearray,0,bytearray.length);
		//stores the total number of bytes read, from 0 to length
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
		//writeIn();

	}
}
