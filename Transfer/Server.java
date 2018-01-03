import java.io.*;
import java.net.*;


//http://mrbool.com/file-transfer-between-2-computers-with-java/24516

public class Server{
  public static void main (String[] args) throws IOException{
    /* Creates serverSocket @ 15123, waits for incoming client connection
       on port 15123 and accepts the connection when received */
    ServerSocket serverSocket = new ServerSocket(Store.port);
    //main logic

    /*waits to accept any client connection and then stores socket*/
    Socket socket = serverSocket.accept();
    System.out.println("Accepted connection: " + socket);
    File transferFile = new File ("Document.doc");
    byte[] bytearray = new byte[(int)transferFile.length()];
    FileInputStream fin = new FileInputStream(transferFile);
    BufferedInputStream bin = new BufferedInputStream(fin);
    bin.read(bytearray,0,bytearray.length);
    OutputStream os = socket.getOutputStream();
    System.out.println("Sending Files...");
    os.write(bytearray,0,bytearray.length);
    os.flush();
    socket.close();
    System.out.println("File transfer complete");


  }
}
