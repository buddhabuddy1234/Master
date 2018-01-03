import java.io.*;
import java.net.*;

public class Client {
  public static void main (String[] args) throws IOException{
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
  }
}
