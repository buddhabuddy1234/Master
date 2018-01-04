import java.io.*;
import java.net.*;
import java.util.Scanner;

//http://mrbool.com/file-transfer-between-2-computers-with-java/24516

public class Server{
	public static Scanner scanner = new Scanner(System.in);
	public static String path = "Document.doc";
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
	
  public static void main (String[] args) throws IOException{
	System.out.println("Creating Socket");
    /* Creates serverSocket @ 15123, waits for incoming client connection
       on port 15123 and accepts the connection when received */
    ServerSocket serverSocket = new ServerSocket(Store.port);
    //main logic

    /*waits to accept any client connection and then stores socket*/
    Socket socket = serverSocket.accept();
    System.out.println("Accepted connection: " + socket);
    
    ////I know I have to do something here about checking if incoming connection
    
    //asks for file write
    writeIn();
	//Transfers file
	File transferFile = new File (path);
    byte[] bytearray = new byte[(int)transferFile.length()];
    FileInputStream fin = new FileInputStream(transferFile);
    BufferedInputStream bin = new BufferedInputStream(fin);
    bin.read(bytearray,0,bytearray.length);
    OutputStream os = socket.getOutputStream();
    System.out.println("Sending Files...");
    
	os.write(bytearray,0,bytearray.length);
    os.flush();
    
    ////I know incoming connection check ends here
    
   socket.close();
   // bin.close();
    //serverSocket.close();

    System.out.println("File transfer complete");


  }
  
}
