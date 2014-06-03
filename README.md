##### Eyeview Interview Assignment


The goal of the assignment is to find the top x hashtags in an input file.

###### Requirements
* The program will be provided with two parameters, a file name and a number of top hashtags to find. The parameters can be passed into the program as command line arguments using -f and -t. E.g. `java com.eyeview.interview.FileProcessor -f posts -t 10`
* The input file can be large (i.e. does not fit in memory)
* Each line in the file will contain 3 fields, delimited with commas: user_id, timestamp, post_text
* Some posts will contain hashtags (e.g. #eyeview)
* Repeated hashtags by the same user id should be counted once
* Some lines may be blank or have bad input 
* Bonus points for unit tests

###### Assignment
Please implement the following functions in `FileProcessor.java`:

1. `apply` in `forEachLine` callback function (line 22). This function would be called with batches of 1-100 lines
2. `apply` in `onDoneGetTrends` callback function (line 30). This function would be called after the file has be read with the number of top hashtags to fetch. It should return a `Map<String, Integer>` in which the key is the hashtag and the value the number of occurances.
