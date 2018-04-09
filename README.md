# TweetAnalyzer

The TweetAnalyzer class brings together the output from the TweetReader and StateReader classes (which depend on the FileReader class). TweetAnalyzer then allows for a user to do one of two things: either input a date and get count of tweets by state on that date, or input a state and get a count of tweets by date for that state.

The UserInterface class holds the main() method. The user is required to manually enter the file names for the tweet and state files into the TweetAnalyzer constructor. Then, main() will read in the data (~20 sec) and then provide the user with a menu. The user can enter 'q' to quit, or choose from one of the two options outlined above.
