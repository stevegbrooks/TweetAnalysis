import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * This class reads in a file that is specified.
 */
public class FileReader {
	
	private String filename;
	private ArrayList<String> lines;
	
	/**
	 * The constructor
	 * @param file the file to read
	 */
	public FileReader(String file) {
		filename = file;
		lines = new ArrayList<String>();
		readFile();
	}

	/**
	 * This will read in the entire file.
	 * It will store the contents in the lines ArrayList.
	 */
	private void readFile() {
		try {
			File inputFile = new File(filename);
			Scanner in = new Scanner(inputFile, "utf-8");
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				lines.add(line);
			}
			
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The accessor method for lines
 	 * @return the lines arraylist
	 */
	public ArrayList<String> getLines() {
		return lines;
	}

}

