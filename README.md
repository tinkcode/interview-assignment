##### Eyeview Interview Assignment


The goal of the assignment is to find the top x hashtags in an input file.

###### Requirements
* The program will be provided with two parameters, a file name and a number of top hashtags to find. The parameters can be passed into the program as command line arguments e.g. `java com.eyeview.interview.TopHashtags posts 10`
* The input file can be large (i.e. does not fit in memory)
* Each line in the file will contain 3 fields, delimited with commas: user_id, timestamp, post_text
* Some posts will contain hashtags (e.g. `#eyeview`)
* Repeated hashtags by the same user id should be counted once
* Hashtags should be case insensitive
* Blank hashtags (# without a word) should not be counted
* Some lines may be blank or have bad input 
* Text may contain non-ascii characters
* Bonus points for unit tests

###### Assignment
Please implement the following methods in `FileProcessor.java`:

1. `readLines(List<String> lines)` (line 15). This method would be called with batches of 1-1000 lines while reading the file
2. `Map<String, Integer> topHashtags(int numOfTop)` (line 20). This method would be called when reading the file is complete. The method should return a map with top x hashtags. In each map entry, the key should be the hashtag and the value should be the number of occurences. The map does not need to be sorted

###### Example
If the input files contains the following lines:
```
2433743148,1402100808,The #Gym #Leader is Byron
2288752253,1402100808,RT @AustinMahone please follow me I'm the #leader! #austin
1635676669,1402100808,heading to the #gym
```
Calling `FileProcessor.topHashtags(2)` should return `{'#gym':2, '#leader':2}`
