package com.eyeview.interview;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

import org.jsoup.Jsoup;

import java.util.TreeMap;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: israel
 * Date: 6/2/14
 * Time: 11:54 AM
 */
public class FileProcessor {

    /**
     * Called with a batch of lines (between 1-1000 lines in each batch)
     *
     * @param  lines a {@link java.util.List} of lines from the file
     *
     */
    private Map<String, Set<String>> tagUserMap = new HashMap<>();
    private Map<String, Integer> numHashTags;
    
    public void readLines(final List<String> lines) throws UnsupportedEncodingException{
        System.out.printf("Got %d lines\n", lines.size());
        for (String line : lines) {
        	if (!line.contains("#")) {continue;}
            String[] entities = line.split(",");
            if (entities.length < 3) {continue;}
            String userId = entities[1];
            
            String[] tags = formatText(entities[2]).split(" ");
            
            Set<String> users = new HashSet<>();
            
            for (String tag : tags) {
            	if (!tag.startsWith("#")) {continue;}
            	if (tag.length() == 1 ) {continue;}
            	if (tagUserMap.containsKey(tag)) {
            	    users = tagUserMap.get(tag);
            	}
            	users.add(userId);
            	tagUserMap.put(tag, users);
            }
        }
    }
    
    private String formatText(final String input) throws UnsupportedEncodingException {
    	String text = Jsoup.parse(Jsoup.parse(input).text()).text();
        String cleanText = new String(text.getBytes("ASCII"));
    	return cleanText.toLowerCase().replaceAll("[!;:\\/\\\\.\\*\\}\\{?\\'\",-_+=()%\\$\\^]", " ");
    }
    
    private class Size implements Comparable<Size> {
    	
    	private int size;
    	
    	public Size(final int size) {
    		this.size = size;
    	}
    	
    	public int getSize() {return size;}
    	
		@Override
		public int compareTo(Size size) {
			if (this.size > size.size) {return -1;}
			else if (this.size < size.size) { return 1;}
			return 1;
		}
    }

    /**
     * Returns a map of top x hashtags
     *
     * @param  numOfTop   the number of top hashtags to find
     *
     * @return a {@link java.util.Map} of top x hashtags, each key is an hashtag and its value is the number of occurrences
     */
    public Map<String, Integer> topHashtags(int numOfTop){
    	numHashTags = new HashMap<>(numOfTop);
    	
    	Map<Size, String> sorterMap = new TreeMap<>();
    	
    	for (Map.Entry<String, Set<String>> entry : tagUserMap.entrySet()) {
    		Size size = new Size(entry.getValue().size());
    		sorterMap.put(size, entry.getKey());
    	}
    	
    	int counter = 0;
    	
    	for (Map.Entry<Size, String> entry : sorterMap.entrySet()) {
    		if (counter >= numOfTop) {break;}
    		numHashTags.put(entry.getValue(), entry.getKey().getSize());
    		counter++;
    	}
    	
        return numHashTags;
    }

}
