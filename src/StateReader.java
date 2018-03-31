import java.util.ArrayList;

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
