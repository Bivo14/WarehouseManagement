package utilities;
import java.util.*; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*; 

/**
 * 
 * @author Bivolaru Alexandru - 30225
 * 
 * <p>Clasa FileReader se foloseste ca si "utility" pentru stocarea continutului unui fisier
 * intr-o lista de Stringuri.</p>
 *
 */

public class FileReader 
{
	/**
	 * 
	 * @param fileName
	 * @return List<String> - continutul fisierului sub forma de lista de Stringuri
	 * <p>Metoda care salveaza continutul unui fisier specificat prin path intr-o lista de Stringuri.</p>
	 * 
	 */
	
	public List<String> readFileInList(String fileName)
	{
		List<String> lines = Collections.emptyList();
		try
		{
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return lines;
	}

}
