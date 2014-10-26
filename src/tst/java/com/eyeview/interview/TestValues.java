package com.eyeview.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class TestValues {

	public static List<String> INPUT_LIST = Arrays
			.asList("2288752253,1402100808,RT @AustinMahone please follow me I'm the #leader! #austin",
					"1635676669,1402100808,heading to the #gym");
	public static String TAG_USER_MAP_NAME = "tagUserMap";
	public static String NUM_HASH_TAG_NAME = "numHashTags";

	public static Map<String, Set<String>> TAG_USER_MAP = Collections.unmodifiableMap(new HashMap<String, Set<String>>() {
		private static final long serialVersionUID = 1L;

		{
		 put("#gym", Collections.singleton("1402100808"));
		 put("#austin", Collections.singleton("1402100808"));
		 put("#leader", Collections.singleton("1402100808"));
		}
	});
	
	public static List<String> INPUT_LIST_WITH_HTML = Arrays
			.asList("2288752253,1402100808,RT @AustinMahone please follow me I'm the #&amp;#160;leader! #austin",
					"1635676669,1402100808,heading to the #gym");
	
	public static Map<String, Set<String>> TAG_USER_MAP_HTML = Collections.unmodifiableMap(new HashMap<String, Set<String>>() {
		private static final long serialVersionUID = 1L;

		{
		 put("#gym", Collections.singleton("1402100808"));
		 put("#austin", Collections.singleton("1402100808"));
		}
	});
	
	public static List<String> INPUT_LIST_WITH_REGEX = Arrays
			.asList("2288752253,1402100808,RT @AustinMahone please follow me I'm the #leader!%()- #austin",
					"1635676669,1402100808,heading to the #gym");
	
	public static Map<String, Set<String>> TAG_USER_MAP_REGEX = Collections.unmodifiableMap(new HashMap<String, Set<String>>() {
		private static final long serialVersionUID = 1L;

		{
		 put("#gym", Collections.singleton("1402100808"));
		 put("#austin", Collections.singleton("1402100808"));
		 put("#leader", Collections.singleton("1402100808"));
		}
	});
	
	public static List<String> INPUT_LIST_WITH_NO_TAGS = Arrays
			.asList("2288752253,1402100808,RT @AustinMahone please follow me I'm the leader!%()- austin",
					"1635676669,1402100808,heading to the gym");
	
	public static Map<String, Integer> HASH_TAG_LIST = Collections.unmodifiableMap(new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
		{
		    put("#gym", 1);
			put("#austin", 1);
		}
	});
	
	public static Map<String, Set<String>> MULTIPLE_TAG_USER_MAP = Collections.unmodifiableMap(new HashMap<String, Set<String>>() {
		private static final long serialVersionUID = 1L;

		{
		 put("#gym", new HashSet<String>(Arrays.asList("1402100808","1402100809", "1402100810")));
		 put("#austin", Collections.singleton("1402100808"));
		 put("#leader", Collections.singleton("1402100808"));
		}
	});
	
	public static Map<String, Integer> SORTED_HASH_TAG_LIST = Collections.unmodifiableMap(new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
		{
		    put("#gym", 3);
			put("#austin", 1);
		}
	});
}