package com.eyeview.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
        BufferedReader reader = null;
        InputStream in;
        try{
            in = getClass().getResourceAsStream("/"+this.fileName);
            reader = new BufferedReader(new InputStreamReader(in));
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
                }
                i++;
            }
            if (lines.size()>0){ //get last lines
                fileProcessor.readLines(lines);
            }
        }catch (IOException e){
            e.printStackTrace();
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        Map<String, Integer> trends = fileProcessor.topHashtags(this.numOfHashtags);
        System.out.print(trends);
    }

    private int randomBatchSize(){
        return randomizer.nextInt((100 - 1) + 1) + 1;
    }
}
