package com.sentiment.application.controller;

import com.sentiment.application.dto.Feedback;
import com.sentiment.application.service.CSVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FeedbackController {
    private final CSVService csvService;

    public FeedbackController(CSVService csvService) {
        this.csvService = csvService;
    }

    // http://localhost:8080/feedback-sentiments?filePath=UserFeedbacks.csv
    @GetMapping("/feedback-sentiments")
    public ResponseEntity<List<Feedback>> getFeedbackSentiments(@RequestParam String filePath) throws IOException {
        List<Feedback> feedbacks = csvService.readCSVAndAnalyzeSentiment(filePath);
        return ResponseEntity.ok(feedbacks);
    }
}

