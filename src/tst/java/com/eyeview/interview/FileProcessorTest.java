package com.eyeview.interview;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FileProcessorTest {

	FileProcessor instance = new FileProcessor();

	@Test
	public void happyPathTest() throws IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchFieldException,
			UnsupportedEncodingException {

		instance.readLines(TestValues.INPUT_LIST);

		Class<?> fileProcessorClass = instance.getClass();

		Field tagUserMapField = fileProcessorClass
				.getDeclaredField("tagUserMap");
		tagUserMapField.setAccessible(true);

		assertEquals("The expected Tag to User map is obtained",
				TestValues.TAG_USER_MAP, tagUserMapField.get(instance));

	}

	@Test
	public void htmlCodesTest() throws IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchFieldException,
			UnsupportedEncodingException {
		instance.readLines(TestValues.INPUT_LIST_WITH_HTML);

		Class<?> fileProcessorClass = instance.getClass();

		Field tagUserMapField = fileProcessorClass
				.getDeclaredField("tagUserMap");
		tagUserMapField.setAccessible(true);

		assertEquals("The expected Tag to User map is obtained",
				TestValues.TAG_USER_MAP_HTML, tagUserMapField.get(instance));
	}

	@Test
	public void emptyListTest() throws UnsupportedEncodingException,
			SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		instance.readLines(Collections.<String> emptyList());

		Class<?> fileProcessorClass = instance.getClass();

		Field tagUserMapField = fileProcessorClass
				.getDeclaredField("tagUserMap");
		tagUserMapField.setAccessible(true);

		assertEquals("The expected Tag to User map is obtained",
				Collections.emptyMap(), tagUserMapField.get(instance));
	}

	@Test
	public void noTagsTest() throws UnsupportedEncodingException,
			SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		instance.readLines(TestValues.INPUT_LIST_WITH_NO_TAGS);

		Class<?> fileProcessorClass = instance.getClass();

		Field tagUserMapField = fileProcessorClass
				.getDeclaredField("tagUserMap");
		tagUserMapField.setAccessible(true);

		assertEquals("The expected Tag to User map is obtained",
				Collections.emptyMap(), tagUserMapField.get(instance));
	}

	@Test
	public void happyPathTagsTest() throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException, UnsupportedEncodingException {

		Class<?> fileProcessorClass = instance.getClass();

		Field tagUserMapField = fileProcessorClass
				.getDeclaredField("tagUserMap");
		tagUserMapField.setAccessible(true);
		tagUserMapField.set(instance, TestValues.TAG_USER_MAP);

		instance.topHashtags(2);

		Field numHashTags = fileProcessorClass.getDeclaredField("numHashTags");
		numHashTags.setAccessible(true);

		assertEquals("The expected Tag to Frequency map is obtained",
				TestValues.HASH_TAG_LIST, numHashTags.get(instance));
	}

	@Test
	public void emptyTagsTest() throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException,
			UnsupportedEncodingException {

		Class<?> fileProcessorClass = instance.getClass();

		Field tagUserMapField = fileProcessorClass
				.getDeclaredField("tagUserMap");
		tagUserMapField.setAccessible(true);
		tagUserMapField.set(instance, Collections.emptyMap());

		instance.topHashtags(2);

		Field numHashTags = fileProcessorClass.getDeclaredField("numHashTags");
		numHashTags.setAccessible(true);

		assertEquals("The expected Tag to Frequency map is obtained",
				Collections.emptyMap(), numHashTags.get(instance));
	}

	@Test
	public void sortedTagsTest() throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException, UnsupportedEncodingException {

		Class<?> fileProcessorClass = instance.getClass();

		Field tagUserMapField = fileProcessorClass
				.getDeclaredField("tagUserMap");
		tagUserMapField.setAccessible(true);
		tagUserMapField.set(instance, TestValues.MULTIPLE_TAG_USER_MAP);

		instance.topHashtags(2);

		Field numHashTags = fileProcessorClass.getDeclaredField("numHashTags");
		numHashTags.setAccessible(true);

		assertEquals("The expected Tag to Frequency map is obtained",
				TestValues.SORTED_HASH_TAG_LIST, numHashTags.get(instance));
	}
}
