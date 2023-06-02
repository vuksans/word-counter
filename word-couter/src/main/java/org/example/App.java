package org.example;
import java.util.Scanner;

public class App
{


    public static void main( String[] args ) {
        String command;
        WordCounter wordCounter = new WordCounter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to word counter, press one to enter your words, or 2 to display the count of a specific, press any other key to exit");
            command = scanner.nextLine();
            if (command.equals("1")) {
                boolean res = false;
                while (!res) {
                    System.out.println("Enter your values");
                    String value = scanner.nextLine();
                    res = wordCounter.addWords(value);
                }
            } else if (command.equals("2")){
                System.out.println("Enter a word you would like to be counted");
                String value = scanner.nextLine();
                try {
                    System.out.println(wordCounter.getWordCount(value));
                } catch (final NotAWordException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                 return;
            }
        }
    }
}
