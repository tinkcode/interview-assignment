package com.eyeview.interview;

import com.google.common.base.Function;

import java.io.*;
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

    private Function<List<String>, Void> lineCallback;
    private Function<Integer, Map<String, Integer>> doneCallback;
    private String fileName;
    private int topTrends;
    private Random randomizer = new Random();

    public PostsFileReader(String fileName, int topTrends) {
        this.fileName = fileName;
        this.topTrends = topTrends;
    }

    public PostsFileReader forEachLine(Function<List<String>, Void> lineCallback){
        this.lineCallback = lineCallback;
        return this;
    }

    public PostsFileReader onDoneGetTrends(Function<Integer, Map<String, Integer>> onDoneCallback){
        this.doneCallback = onDoneCallback;
        return this;
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
                    this.lineCallback.apply(lines);
                    lines.clear();
                    batchSize = this.randomBatchSize();
                }
                i++;
            }
            if (lines.size()>0){ //get last lines
                this.lineCallback.apply(lines);
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
        Map<String, Integer> trends = this.doneCallback.apply(this.topTrends);
        System.out.print(trends);
    }

    private int randomBatchSize(){
        return randomizer.nextInt((100 - 1) + 1) + 1;
    }
}
