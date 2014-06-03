package com.eyeview.interview;

import com.google.common.base.Function;
import org.apache.commons.cli.*;

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

    public void processFile(String file, int topTrends){
        PostsFileReader reader = new PostsFileReader(file, topTrends)
                .forEachLine(new Function<List<String>, Void>() {
                    @Override
                    public Void apply(List<String> input) {
                        //Gets a batch of lines (between 1-100)
                        System.out.printf("Got %d lines\n", input.size());
                        return null;
                    }
                })
                .onDoneGetTrends(new Function<Integer, Map<String, Integer>>() {
                    @Override
                    public Map<String, Integer> apply(Integer topNumber) {
                        //Called after the file has been read
                        return new HashMap<String, Integer>();
                    }
                });
        reader.readFile();
    }

    public static void main(String[] args) {
        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("f", "file", true, "file to process");
        options.addOption("t", "top", true, "number of top hashtags to find");

        try {
            CommandLine cmd = parser.parse( options, args);
            if (!cmd.hasOption("f") || !cmd.hasOption("t")){
                throw new IllegalArgumentException("Please pass in -f {file_name} and -t {trends_number}");
            }
            String fileName = cmd.getOptionValue("f");
            int topTrends = Integer.parseInt(cmd.getOptionValue("t"));
            new FileProcessor().processFile(fileName, topTrends);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
