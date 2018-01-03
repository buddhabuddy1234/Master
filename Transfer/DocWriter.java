import java.io.*;

public class DocWriter{
	
	private String path;
	private boolean append = false;
	
	public DocWriter(String file_path){
		path = file_path;
	}
	
	public DocWriter(String file_path, boolean append_value){
		path = file_path;
		append = append_value;
	}
	
	public void writeToDoc(String textLine) throws IOException{
		FileWriter write = new FileWriter(path, append);
		PrintWriter print_line = new PrintWriter(write);
		
		print_line.printf("%s" + "%n", textLine);
		
		print_line.close();
	}
}