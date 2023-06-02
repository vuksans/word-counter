package com.example.wordcounterapi.controller;

import com.example.wordcounterapi.controller.dto.WordDto;
import org.example.NotAWordException;
import org.example.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/word-counter")
public class WordCounterRestController {
    @Autowired
    public WordCounterRestController(final WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addWord(@RequestBody WordDto wordDto) {
        boolean result = wordCounter.addWords(wordDto.getText());
        return ResponseEntity.status(result ? 200 : 400).build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getWordCount(@RequestParam("word") String word) {
        try {
            return ResponseEntity.ok(wordCounter.getWordCount(word));
        } catch (NotAWordException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    private final WordCounter wordCounter;
}
