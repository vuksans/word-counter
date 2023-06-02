package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class WordCounterItTest {

    private WordCounter wordCounter;

    @BeforeEach
    public void before() {
        wordCounter = new WordCounter();
    }

    @Test
    @DisplayName("should have word counter 3 for work when added 3 times")
    public void testAddWordFlow() throws NotAWordException {
        wordCounter.addWords("work not WORK why woRk");
        int result = wordCounter.getWordCount("WORK");
        assertEquals(3, result);
    }

    @Test
    @DisplayName("should have word counter 10 for work when added 3 times on multiple occurrences")
    public void testAddWordFlowMultiple() throws NotAWordException {
        String toAdd = "work not WORK why woRk";
        for (int i =0; i < 3; i++) {
            wordCounter.addWords(toAdd);
        }
        wordCounter.addWords("  WORK ");
        int result = wordCounter.getWordCount("WoRK");
        assertEquals(10, result);
    }
}