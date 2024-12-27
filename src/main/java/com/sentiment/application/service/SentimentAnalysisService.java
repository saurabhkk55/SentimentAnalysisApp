package com.sentiment.application.service;

import edu.stanford.nlp.pipeline.*;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SentimentAnalysisService {

    private StanfordCoreNLP pipeline;

    public SentimentAnalysisService(StanfordCoreNLP pipeline) {
        this.pipeline = pipeline;
    }

    public String getSentiment(String text) {
        // Annotate the text with sentiment analysis
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        // Sentiment for the entire document (just the first sentence for simplicity)
        String sentiment = document.sentences().get(0).sentiment();
        return sentiment;
    }
}
