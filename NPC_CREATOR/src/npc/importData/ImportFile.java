package npc.importData;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ImportFile {
	public static String importStream(String path,String fileName){
		
	
		//read file into stream, try-with-resources
		String content="";
		try {
			content = new String(Files.readAllBytes(Paths.get(path,fileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			return content;
	
	}
}

