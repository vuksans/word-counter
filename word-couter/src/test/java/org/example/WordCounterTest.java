package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class WordCounterTest {

    private WordCounter wordCounter;

    @BeforeEach
    public void before() {
        wordCounter = new WordCounter();
    }

    @Nested
    @DisplayName("addWord")
    class AddWord {

        @Test
        @DisplayName("should return true when one alphabetic word is added")
        public void testAddWordSuccess() {
            boolean result = wordCounter.addWords("work");
            assertTrue(result);
        }

        @Test
        @DisplayName("should return true when one multiple alphabetic words are added")
        public void testAddWordAddMultipleSuccess() {
            boolean result = wordCounter.addWords("work WORK working");
            assertTrue(result);
        }

        @Test
        @DisplayName("should return true when one multiple alphabetic words are added with spaces at start, end and between")
        public void testAddWordAddMultipleAndSpacesSuccess() {
            boolean result = wordCounter.addWords( " work WORK  working ");
            assertTrue(result);
        }

        @Test
        @DisplayName("should return false word contain non alphabetic characters")
        public void testAddWordWithNonAlphabeticFail() {
            boolean result = wordCounter.addWords( "work......");
            assertFalse(result);
        }

        @Test
        @DisplayName("should return false words contain non alphabetic characters")
        public void testAddWordsWithNonAlphabeticFail() {
            boolean result = wordCounter.addWords( "work work$ Work!ing");
            assertFalse(result);
        }

        @Test
        @DisplayName("should return false passed value is null")
        public void testAddWordsWithNullFail() {
            boolean result = wordCounter.addWords( null);
            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("getWordCount")
    class GetWordCount {

        @Test
        @DisplayName("should get zero for number of words in a words is entered")
        public void getWordCountSuccess() throws NotAWordException {
            final int result = wordCounter.getWordCount("word");
            assertEquals(0, result);
        }

        @Test
        @DisplayName("should get zero for number of words in a words is entered with spaces at start and end")
        public void getWordCountWithTrailingSpacesSuccess() throws NotAWordException {
            final int result = wordCounter.getWordCount(" word ");
            assertEquals(0, result);
        }

        @Test
        @DisplayName("throw NotAWordException when string has invalid characters")
        public void getWordCountFail() throws NotAWordException {
            assertThrows(NotAWordException.class, () -> {
                wordCounter.getWordCount("word!");
            });
        }

        @Test
        @DisplayName("throw NotAWordException when string has multiple words")
        public void getWordsCountFail() throws NotAWordException {
            assertThrows(NotAWordException.class, () -> {
                wordCounter.getWordCount("two words");
            });
        }

        @Test
        @DisplayName("throw NotAWordException when string is null")
        public void getWordsCountFailStringNull() throws NotAWordException {
            assertThrows(NotAWordException.class, () -> {
                wordCounter.getWordCount(null);
            });
        }
    }

}
