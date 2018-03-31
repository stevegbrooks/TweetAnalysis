import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class TweetAnalyzer {
	private HashSet<Tweet> tweets;
	private ArrayList<State> states;
	private ReaderUtility utils;
	
	public TweetAnalyzer(HashSet<Tweet> tweets, ArrayList<State> states) {
		this.tweets = tweets;
		this.states = states;
		utils = new ReaderUtility();
	}
	
	//Here is where I will do my check to make sure the tweet was in the 
	//northern and western hemisphere.
	
	public HashMap<State, Integer> countNumOfTweetsByState(String date) {
		
		String dateString = utils.parseDate(date);
		String[] dateParts = dateString.split("-");
		int[] dateArray = utils.convertToIntArray(dateParts);
		Calendar cal = Calendar.getInstance();
		cal.set(dateArray[0], dateArray[1] - 1, dateArray[2], 0, 0, 0);
		Date inputDate = cal.getTime();
		
		HashMap<State, Integer> hashMap = new HashMap<>();
		
		for (Tweet tweet : tweets) {
			double lat = tweet.getLat();
			double lng = tweet.getLng();

			if (lat > 0 && lng < 0) {
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
	 * Finds the closest state centroid to the tweet
	 * that is entered. Changes the "State" attribute in the Tweet object
	 * when it is found.
	 * 
	 * @param tweet
	 */
	public void assignClosestState(Tweet tweet) {
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
}
