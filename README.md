# TweetAnalyzer

The TweetAnalyzer class brings together the output from the TweetReader and StateReader classes (which depend on the FileReader class). TweetAnalyzer then allows for a user to input a date and get count of tweets by state on that date.

The UserInterface class holds the main() method. The user is required to manually enter the file names for the tweet and state files into the TweetAnalyzer constructor. Then, main() will read in the data (~20 sec) and then ask the user to supply a date. The user can enter 'q' to quit, or continue entering dates.
