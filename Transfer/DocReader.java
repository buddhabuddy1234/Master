import java.io.*;

public class DocReader{

	private String path;

	public DocReader(String file_path){
		path = file_path;
	}
	
	public String[] openFile() throws IOException{
		
		
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];
		
		int i;
		
		for( i = 0; i < numberOfLines; i ++){
			textData[i] = textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}
	
	public int readLines() throws IOException, FileNotFoundException{
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);
		
		String aLine;
		int numberOfLines = 0;
		
		while ((aLine = bf.readLine()) != null){
			numberOfLines++;
		}
		bf.close();
		
		return numberOfLines;
	}
	

}