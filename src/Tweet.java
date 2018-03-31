import java.util.Date;
/**
 * This is a POJO to represent a tweet. It has attributes of location (lat/long and State), the date
 * the tweet was sent, and the contents of the tweet.
 * 
 * @author sgb
 *
 */
public class Tweet {
	
	private double lat;
	private double lng;
	private State state;
	private Date date;
	private String contents;
	
	/**
	 * First constructor takes just lat, long and date.
	 * State is set to null and will be attributed to it in the TweetAnalysis class.
	 * @param lat
	 * @param lng
	 * @param date
	 */
	public Tweet(double lat, double lng, Date date) {
		this.lat = lat;
		this.lng = lng;
		this.date = date;
		this.contents = null;
		this.state = null;
	}
	/**
	 * The second constructor takes lat, long, date and contents.
	 * State is set to null and will be attributed to it in the TweetAnalysis class.
	 * @param lat
	 * @param lng
	 * @param date
	 * @param contents
	 */
	public Tweet(double lat, double lng, Date date, String contents) {
		this.lat = lat;
		this.lng = lng;
		this.date = date;
		this.contents = contents;
		this.state = null;
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

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(lat + ", ");
		sb.append(lng + " | ");
		sb.append(date + " | ");
		sb.append(contents + "\n");
		sb.append(state);
	
		return sb.toString();
		
	}


}
