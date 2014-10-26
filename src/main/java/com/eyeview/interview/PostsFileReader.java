package com.eyeview.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: israel
 * Date: 6/2/14
 * Time: 12:07 PM
 */
public class PostsFileReader {

    private FileProcessor fileProcessor;
    private String fileName;
    private int numOfHashtags;
    private Random randomizer = new Random();

    /**
     * Initialized the file reader
     * @param fileName the file to be read
     * @param numOfHashtags number of top hashtags to find
     * @param fileProcessor a processor to read lines and extract hashtags
     */
    public PostsFileReader(String fileName, int numOfHashtags, FileProcessor fileProcessor) {
        this.fileName = fileName;
        this.numOfHashtags = numOfHashtags;
        this.fileProcessor = fileProcessor;
    }

    /**
     * Reads the input file and calls {@link FileProcessor#readLines(java.util.List)} while reading
     * lines and {@link FileProcessor#topHashtags(int)} when reading is complete
     */
    public void readFile(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().
                getResourceAsStream("/" + this.fileName)));
        try {
            String line;
            int i = 0;
            int batchSize = this.randomBatchSize();
            List<String> lines = new ArrayList<>();
            while((line = reader.readLine()) != null){
                lines.add(line);
                if (i==batchSize){
                    fileProcessor.readLines(lines);
                    lines.clear();
                    batchSize = this.randomBatchSize();
                    i=0;
                }
                i++;
            }
            if (lines.size()>0){ //get last lines
                fileProcessor.readLines(lines);
            }
            reader.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        
        Map<String, Integer> hashtags = fileProcessor.topHashtags(this.numOfHashtags);
        System.out.print(hashtags);
    }

    /**
     *
     * @return a random int between 1-1000 with probability of 0.2 to be between 1-100 and 0.8 between 800-1000
     */
    private int randomBatchSize(){
        double selector = Math.random();
        int max;
        int min;
        if(selector <= 0.2){ // p(0.2)
            min = 1;
            max = 100;
        }else{ // p(0.8)
            min = 800;
            max = 1000;
        }
        return randomizer.nextInt((max - min) + 1) + min;
    }
}