##### Eyeview Interview Assignment


The goal of the assignment is to find the top x hashtags in an input file.

###### Requirements
* The program will be provided with two parameters, a file name and a number of top hashtags to find. The parameters can be passed into the program as command line arguments using -f and -t. E.g. `java com.eyeview.interview.TopHashtags posts 10`
* The input file can be large (i.e. does not fit in memory)
* Each line in the file will contain 3 fields, delimited with commas: user_id, timestamp, post_text
* Some posts will contain hashtags (e.g. #eyeview)
* Repeated hashtags by the same user id should be counted once
* Some lines may be blank or have bad input 
* Bonus points for unit tests

###### Assignment
Please implement the following methods in `FileProcessor.java`:

1. `readLines(List<String> lines)` (line 15). This method would be called with batches of 1-100 lines
2. `Map<String, Integer> topHashtags(int numOfTop)` (line 20). This method would be called when reading the file has complete. It should return a map with top x hashtags. In each map entry the key would be the hashtag and the value is the number of occurences. The map does not need to be sorted
