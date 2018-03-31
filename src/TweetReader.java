import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetReader {
	private HashSet<Tweet> tweets;
	private FileReader fileReader;
	private ReaderUtility utils;
	
	public TweetReader(String file) {
		fileReader = new FileReader(file);
		ArrayList<String> rawData = fileReader.getLines();
		utils = new ReaderUtility();
		tweets = new HashSet<Tweet>();
		ArrayList<Tweet> tweetsTemp = new ArrayList<>();
		
		for (int i = 0; i < rawData.size(); i++) {
			//create vars for Tweet
			double lat = 0;
			double lng = 0;
			Date date = null;
			String contents = null;
			Tweet newTweet = null;
			
			//make sure the line starts with lat/lng
			Matcher matcher = Pattern.compile("[\\[]+[-]?[0-9]*\\.[0-9]*\\,"
					+ "\\s[-]?[0-9]*\\.[0-9]*[\\]]+").matcher(rawData.get(i));
			//if it does, then proceed normally
			if (matcher.find()) {
				//split on the first ']' bracket
				String[] lineArray = rawData.get(i).split("\\]", 2);
				//get lat and long
				String[] latLngArray = lineArray[0].split("\\[", 2);
				latLngArray = latLngArray[1].split(",", 2);
				lat = Double.parseDouble(latLngArray[0]);
				lng = Double.parseDouble(latLngArray[1]);
				//get date
				String dateString = utils.parseDate(lineArray[1]);
				String[] dateParts = dateString.split("-");
				int[] dateArray = utils.convertToIntArray(dateParts);
				Calendar cal = Calendar.getInstance();
				cal.set(dateArray[0], dateArray[1] - 1, dateArray[2], 0, 0, 0);
				date = cal.getTime();
				//get contents
				String[] contentsArray = lineArray[1].split("[0-9]*\\:[0-9]*\\:[0-9]*");
				contents = contentsArray[1].trim();
				newTweet = new Tweet(lat, lng, date, contents);
			//if it doesnt then remove the tweet that was just added, and append
				//the contents of the extra line to that tweet that was just added,
				//and then re-add it.
			} else {
				tweets.remove(tweetsTemp.get(i - 1));
				StringBuilder sb = new StringBuilder();
				sb.append(tweetsTemp.get(i - 1).getContents());
				sb.append(" " + rawData.get(i).trim());
				tweetsTemp.get(i - 1).setContents(sb.toString());
				newTweet = tweetsTemp.get(i - 1);
			}
			tweets.add(newTweet);
			tweetsTemp.add(newTweet);
		}
	}

	/**
	 * @return the tweets
	 */
	public HashSet<Tweet> getTweets() {
		return tweets;
	}
	
	
}
