/**
 * This is a POJO to represent a State. It has the attributes of location (lat/long) and 
 * the name of the state (Alabama, Arkansas, etc...).
 * 
 * @author sgb
 *
 */
public class State {
	private String name;
	private double lat;
	private double lng;
	
	/**
	 * The constructor takes in the name and lat/lng
	 * @param name
	 * @param lat
	 * @param lng
	 */
	public State(String name, double lat, double lng) {
		this.name = name;
		this.lat = lat;
		this.lng = lng;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);	
		return sb.toString();		
	}
	
	public boolean equals(State state) {
		return this.name.equalsIgnoreCase(state.getName());
	}

}
