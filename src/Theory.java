import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Theory {

	public static void main(String[] args) {
		TweetReader tr = new TweetReader("all_tweets.txt");
		StateReader sr = new StateReader("states.csv");
		
		HashSet<Tweet> tweets = tr.getTweets();
		ArrayList<State> states = sr.getStates();
		
		TweetAnalyzer tweetAnalyzer = new TweetAnalyzer(tweets, states);
		
//		for (Tweet tweet : tweets) {
//			tweetAnalyzer.assignClosestState(tweet);
//			System.out.println(tweet);
//		}
		System.out.println("Tweet HashSet size: " + tweets.size());
		
//		for (State state : states) {
//			System.out.println(state);
//		}
		System.out.println("State ArrayList size: " + states.size());
		
		HashMap<State, Integer> hashMap = tweetAnalyzer.countNumOfTweetsByState("2011-08-28");
		System.out.println("HashMap Size: " + hashMap.size());
		for (State key : hashMap.keySet()) {
			System.out.print(key + " = ");
			System.out.println(hashMap.get(key));
		}
	}

}
