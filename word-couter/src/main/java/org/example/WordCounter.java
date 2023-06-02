package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordCounter {

    public boolean addWords(final String text) {
        if (text == null) {
            return false;
        }
        final String trimmedText = text.trim();
        if (!trimmedText.matches(wordsRegex)) {
            return false;
        }
        Arrays.asList(trimmedText.toLowerCase().split(" +")).forEach(word -> {
            String translation = translator.translate(word);
            wordsMap.compute(translation, (key, val) -> val == null ? 1 : val + 1);
        });
        return true;
    }

        public int getWordCount(final String word) throws NotAWordException {
        if (word == null) {
            throw new NotAWordException();
        }
        final String trimmedText = word.trim();
        if (!trimmedText.matches(singleWordRegex)) {
            throw new NotAWordException(trimmedText);
        }
        return wordsMap.getOrDefault(translator.translate(trimmedText.toLowerCase()), 0);
    }

    private static final Translator translator = new TranslatorImpl();
    private final Map<String, Integer> wordsMap = new ConcurrentHashMap<>();
    private static final String wordsRegex = "[a-zA-Z][ a-zA-Z]+";
    private static final String singleWordRegex = "^[A-Za-z]+$";
}
