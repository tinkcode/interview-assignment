package com.eyeview.interview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: israel
 * Date: 6/2/14
 * Time: 11:54 AM
 */
public class FileProcessor {

    public void readLines(List<String> lines){
        //Gets a batch of lines (between 1-100)
        System.out.printf("Got %d lines\n", lines.size());
    }

    public Map<String, Integer> topHashtags(int numOfTop){
        //Called after the file has been read
        return new HashMap<String, Integer>();
    }

}
