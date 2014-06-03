package com.eyeview.interview;

/**
 * Created with IntelliJ IDEA.
 * User: israel
 * Date: 6/3/14
 * Time: 1:23 PM
 */
public class TopHashtags {
    public static void main(String[] args) {

        if (args.length<2){
            throw new IllegalArgumentException("Please pass in {file_name} and {number_of_hashtags}");
        }

        String fileName = args[0];

        int numOfHashtags;
        try{
            numOfHashtags = Integer.parseInt(args[1]);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Please pass a number of top hashtags to find");
        }
        PostsFileReader reader = new PostsFileReader(fileName, numOfHashtags, new FileProcessor());
        reader.readFile();

    }
}
