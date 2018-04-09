import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
/**
 * This class will run the TweetAnalyzer program and catch input errors from the user.
 * @author sgb
 *
 */
public class UserInterface {
	
	/**
	 * prints the menu of options
	 */
	public static void printMenu() {
		System.out.println("Enter '1' to look at the count of tweets by state on a given date.");
		System.out.println("Enter '2' to look at the count of tweets by date in a given state.");
		System.out.println("Enter 'q' to exit.");
	}
	/**
	 * runs option 1 - the countNumOfTweetsByState() method of TweetAnalyzer
	 * @param tweetAnalyzer a TweetAnalyzer object
	 * @param in a Scanner object for keyboard input
	 */
	public static void menuOption1(TweetAnalyzer tweetAnalyzer, Scanner in) {
		HashMap<State, Integer> output = new HashMap<>();
		while (true) {
			try {
				System.out.println("Enter a date using the following format: YYYY-MM-DD");
				String date = in.nextLine();
				output = tweetAnalyzer.countNumOfTweetsByState(date);
				if (output.size() == 0) {
					System.out.println("There are no tweets on that date in this dataset.");
				} else {
					System.out.println("The following is the count of tweets by state on " + date + ":");
					System.out.println("===========================================================");
					for (State key : output.keySet()) {
						System.out.print(key + " = ");
						System.out.println(output.get(key));
					}
				}
				break;
			} catch (IllegalArgumentException iae) {
				System.out.println(iae.getMessage());
			}
		}
	}
	/**
	 * runs option 2 - the countNumOfTweetsByDate() method of TweetAnalyzer
	 * @param tweetAnalyzer a TweetAnalyzer object
	 * @param in a Scanner object for keyboard input
	 */
	public static void menuOption2(TweetAnalyzer tweetAnalyzer, Scanner in) {
		TreeMap<String, Integer> output = new TreeMap<>();
		while (true) {
			try {
				System.out.println("Enter the name of a state (use 'District of Columbia' for Washington D.C.).");
				String state = in.nextLine();
				output = tweetAnalyzer.countNumOfTweetsByDate(state);
				if (output.size() == 0) {
					System.out.println("There are no tweets in that state in this dataset.");
				} else {
					System.out.println("The following is the count of tweets by date in " + state + ":");
					System.out.println("===========================================================");
					for (String key : output.keySet()) {
						System.out.print(key + " = ");
						System.out.println(output.get(key));
					}
				}
				break;
			} catch (IllegalArgumentException iae) {
				System.out.println(iae.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Please be patient - reading tweets into memory...");
		TweetAnalyzer tweetAnalyzer = new TweetAnalyzer("tweetTest.txt", "states.csv");

		Scanner in = new Scanner(System.in);
		
		printMenu();
		
		String menuInput = new String();

		while (true) {			
			try {
				menuInput = in.nextLine();
				if (menuInput.equalsIgnoreCase("q")) {
					break;
				} else if (menuInput.equalsIgnoreCase("1")) {
					menuOption1(tweetAnalyzer, in);
					System.out.println("===========================================================");
					printMenu();
				} else if (menuInput.equalsIgnoreCase("2")) {
					menuOption2(tweetAnalyzer, in);
					System.out.println("===========================================================");
					printMenu();
				} else {
					System.out.println("Invalid input.");
					printMenu();
				}
			} catch (IllegalArgumentException iae) {
				System.out.println(iae.getMessage());
			}
		}
		in.close();
	}

}
