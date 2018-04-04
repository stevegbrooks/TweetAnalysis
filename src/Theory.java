import java.time.Instant;
import java.util.HashMap;


public class Theory {

	public static void main(String[] args) {
		System.out.println("Start:" + Instant.now());
		TweetAnalyzer tweetAnalyzer = new TweetAnalyzer("all_tweets.txt", "states.csv");
		
//		for (Tweet tweet : tweets) {
//			tweetAnalyzer.assignClosestState(tweet);
//			System.out.println(tweet);
//		}
		
//		for (State state : states) {
//			System.out.println(state);
//		}
		
		HashMap<State, Integer> hashMap = tweetAnalyzer.countNumOfTweetsByState("2011-08-28");
		
		System.out.println("HashMap Size: " + hashMap.size());
		for (State key : hashMap.keySet()) {
			System.out.print(key + " = ");
			System.out.println(hashMap.get(key));
		}
		
		System.out.println("End :" + Instant.now());
	}

}
