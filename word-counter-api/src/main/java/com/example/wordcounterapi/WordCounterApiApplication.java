package com.example.wordcounterapi;

import org.example.WordCounter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WordCounterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordCounterApiApplication.class, args);
	}


	@Bean
	public WordCounter wordCounter() {
		return new WordCounter();
	}

}
