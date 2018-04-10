import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
/**
 * The TweetAnalyzer class is where all the magic happens. It requires file names be provided
 * for the tweet file and the state file, and then pulls together those two data sets for analysis.
 * 
 * Its one and only function right now is to provide the count of tweets by state given a date.
 * 
 * It will throw exceptions if there are input errors (eg a date out of range, or not formatted properly).
 * 
 * @author sgb
 *
 */
public class TweetAnalyzer {
	private TweetReader tweetReader;
	private StateReader stateReader;
	private HashSet<Tweet> tweets;
	private ArrayList<State> states;
	private ReaderUtility utils;

	public TweetAnalyzer(String tweetFileName, String stateFileName) {
		tweetReader = new TweetReader(tweetFileName);
		stateReader = new StateReader(stateFileName);
		tweets = tweetReader.getTweets();
		states = stateReader.getStates();
		utils = new ReaderUtility();
	}
	/**
	 * Provides a HashMap where the keys are State objects and the values
	 * are the count of tweets in that state for a given date.
	 * 
	 * @param date a user supplied date in the format of YYYY-MM-DD
	 * @return a HashMap
	 */
	@SuppressWarnings("deprecation")
	public HashMap<State, Integer> countNumOfTweetsByState(String date) {
		//input error checking #1
		if (!date.matches("[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}")) {
			throw new IllegalArgumentException("Error: invalid date format. Must be in the form 'YYYY-MM-DD'.");
		}

		String dateString = utils.parseDate(date);
		String[] dateParts = dateString.split("-");
		int[] dateArray = utils.convertToIntArray(dateParts);
		Calendar cal = Calendar.getInstance();
		cal.set(dateArray[0], dateArray[1] - 1, dateArray[2], 0, 0, 0);
		Date inputDate = cal.getTime();
		
		cal.setTime(tweetReader.getMinDate());
		Date minDate = (Date) cal.getTime().clone();
		cal.setTime(tweetReader.getMaxDate());
		Date maxDate = (Date) cal.getTime().clone();
		
		maxDate.setHours(23);
		maxDate.setMinutes(59);
		maxDate.setSeconds(59);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String minDateString = formatter.format(minDate);
		String maxDateString = formatter.format(maxDate);
		
		//input error checking #2
		if (inputDate.before(minDate) || inputDate.after(maxDate)) {
			throw new IllegalArgumentException("Error: the data only covers " 
					+ minDateString + " to " + maxDateString + ".");
		}
	
		HashMap<State, Integer> hashMap = new HashMap<>();

		for (Tweet tweet : tweets) {
			double lat = tweet.getLat();
			double lng = tweet.getLng();

			if (lat > 0 && lng < 0) { //northern hemisphere and western hemisphere only
				String tweetDate = tweet.getDate().toString();
				if (tweetDate.equals(inputDate.toString())) {

					assignClosestState(tweet);

					if (hashMap.containsKey(tweet.getState())) {
						hashMap.put(tweet.getState(), 
								hashMap.get(tweet.getState()) + 1);
					} else { 
						hashMap.put(tweet.getState(), 1); 
					}
				}
			}
		}
		return hashMap;
	}
	/**
	 * Finds the closest state centroid to the tweet entered. Closest is defined using
	 * euclidean distance.
	 * 
	 * Changes the "State" attribute in the Tweet object
	 * when it is found.
	 * 
	 * @param tweet
	 */
	private void assignClosestState(Tweet tweet) {
		double tweetLat = tweet.getLat();
		double tweetLng = tweet.getLng();
		double shortest = Long.MAX_VALUE;
		State closestState = null;

		for (State state : states) {
			double stateLat = state.getLat();
			double stateLng = state.getLng();
			double x = tweetLat - stateLat;
			double y = tweetLng - stateLng;

			double hypotenuse = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

			if (hypotenuse < shortest) {
				shortest = hypotenuse;
				closestState = state;
			}
		}
		tweet.setState(closestState);
	}

	/**
	 * This method returns a HashMap with dates (YYYY-MM-DD) as keys, and the amount
	 * of tweets on that date as values. The user supplies one of the 51 states (incl. DC),
	 * and the HashMap is returned showing the amount of tweets on each day for that state 
	 * in the supplied tweet data.
	 */

	public TreeMap<String, Integer> countNumOfTweetsByDate(String inputStateName) {
		//input error checking #1
		if (!inputStateName.matches("[a-zA-Z\\s]*")) {
			throw new IllegalArgumentException("Error: invalid state name.");
		}
		
		//Check that its a state in the state list
		State input = null;
		for (State state : states) {
			if (state.getName().equalsIgnoreCase(inputStateName)) {
				input = state;
				break;
			}
		}
		
		//input error checking #2
		if (input == null) throw new IllegalArgumentException("Error: invalid state name.");
		
		//Assemble the HashMap:
		//first assign each tweet to a state, then if it matches the input
		//add it to the hashmap and keep track of tweet count
		TreeMap<String, Integer> treeMap = new TreeMap<>();
		for (Tweet tweet : tweets) {
			Date tweetDate = tweet.getDate();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date = formatter.format(tweetDate);
			
			double lat = tweet.getLat();
			double lng = tweet.getLng();
			
			if (lat > 0 && lng < 0) { 
				assignClosestState(tweet);
				State tweetState = tweet.getState();
				if (tweetState.equals(input)) {
					if (treeMap.containsKey(date)) {
						treeMap.put(date, 
								treeMap.get(date) + 1);
					} else { 
						treeMap.put(date, 1); 
					}
				}
			}
		}
		return treeMap;
	}
}
