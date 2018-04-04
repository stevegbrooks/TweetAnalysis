import java.util.HashMap;
import java.util.Scanner;
/**
 * This class will run the TweetAnalyzer program and catch input errors from the user.
 * @author sgb
 *
 */
public class UserInterface {

	public static void main(String[] args) {
		System.out.println("Please be patient - reading tweets into memory...");
		TweetAnalyzer tweetAnalyzer = new TweetAnalyzer("all_tweets.txt", "states.csv");

		HashMap<State, Integer> hashMap = new HashMap<>();

		Scanner in = new Scanner(System.in);
		System.out.println("Enter a date using the following format: YYYY-MM-DD. Enter 'q' to exit.");
		String date = new String();

		while (true) {			
			try {
				date = in.nextLine();
				if (date.equalsIgnoreCase("q")) {
					break;
				} else {
					hashMap = tweetAnalyzer.countNumOfTweetsByState(date);
					if (hashMap.size() == 0) {
						System.out.println("There are no tweets on that date in this dataset.");
					} else {
						System.out.println("The following is the count of tweets by state on that date:");
						System.out.println("===========================================================");
						for (State key : hashMap.keySet()) {
							System.out.print(key + " = ");
							System.out.println(hashMap.get(key));
						}
					}
					System.out.println("===========================================================");
					System.out.println("Enter another date or 'q' to exit: ");
				}
			} catch (IllegalArgumentException iae) {
				System.out.println(iae.getMessage());
			}
		}
		in.close();

	}

}
