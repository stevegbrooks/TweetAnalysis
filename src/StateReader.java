import java.util.ArrayList;
/**
 * The StateReader class takes in a file name and produces an ArrayList of State objects.
 * There are assumptions about the formatting of the file:
 * 
 * 1. No column headers
 * 2. State name in first column
 * 3. Latitude in second column
 * 4. Longitude in third column
 * 
 * @author sgb
 *
 */
public class StateReader {
	private FileReader fileReader;
	private ArrayList<State> states;
	
	public StateReader(String file) {
		fileReader = new FileReader(file);
		ArrayList<String> rawData = fileReader.getLines();
		states = new ArrayList<State>();
		
		for (String line : rawData) {
			//create vars for state
			String name = null;
			double lat = 0;
			double lng = 0;
			
			String[] lineArray = line.split(",");
			name = lineArray[0];
			lat = Double.parseDouble(lineArray[1]);
			lng = Double.parseDouble(lineArray[2]);
			
			State newState = new State(name, lat, lng);
			states.add(newState);
		}
	}

	/**
	 * @return the states
	 */
	public ArrayList<State> getStates() {
		return states;
	}
	
	
}
