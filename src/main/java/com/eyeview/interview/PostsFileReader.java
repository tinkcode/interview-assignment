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

    public PostsFileReader(String fileName, int numOfHashtags, FileProcessor fileProcessor) {
        this.fileName = fileName;
        this.numOfHashtags = numOfHashtags;
        this.fileProcessor = fileProcessor;
    }

    public void readFile(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().
                getResourceAsStream("/" + this.fileName)))){
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
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Map<String, Integer> trends = fileProcessor.topHashtags(this.numOfHashtags);
        System.out.print(trends);
    }

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