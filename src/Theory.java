import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;


public class Theory {

	public static void main(String[] args) {
		System.out.println("Start:" + Instant.now());
		TweetAnalyzer tweetAnalyzer = new TweetAnalyzer("all_tweets.txt", "states.csv");
		System.out.println("End:" + Instant.now());
		
		System.out.println("Start Option 1" + Instant.now());
		HashMap<State, Integer> hashMap = tweetAnalyzer.countNumOfTweetsByState("2011-08-29");
		for (State key : hashMap.keySet()) {
			System.out.print(key + " = ");
			System.out.println(hashMap.get(key));
		}
		System.out.println("End Option 1" + Instant.now());
		
		System.out.println("Start Option 2" + Instant.now());
		TreeMap<String, Integer> treeMap = tweetAnalyzer.countNumOfTweetsByDate("Ohio");
		for (String key : treeMap.keySet()) {
			System.out.print(key + " = ");
			System.out.println(treeMap.get(key));
		}
		System.out.println("End Option 2" + Instant.now());
		
	}

}
