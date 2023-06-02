package org.example;

public class NotAWordException extends Exception {

    public NotAWordException() {
        super("Value not supported");
    }

    public NotAWordException(final String word) {
        super(String.format("String %s not supported, please enter a valid value", word));
    }
}
