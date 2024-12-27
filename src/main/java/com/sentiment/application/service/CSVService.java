package com.sentiment.application.service;

import com.sentiment.application.dto.Feedback;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CSVService {

    private final SentimentAnalysisService sentimentAnalysisService;

    public CSVService(SentimentAnalysisService sentimentAnalysisService) {
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    public List<Feedback> readCSVAndAnalyzeSentiment(String filePath) throws IOException {
        List<Feedback> feedbacks = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("name", "feedback", "sentiment")
                    .withSkipHeaderRecord(true)
                    .parse(reader);

            for (CSVRecord record : records) {
                Feedback feedback = new Feedback();
                String name = record.get("name");
                String userFeedback = record.get("feedback");

                // Analyze sentiment for the feedback
                String sentiment = sentimentAnalysisService.getSentiment(userFeedback);

                feedback.setName(name);
                feedback.setFeedback(userFeedback);
                feedback.setSentiment(sentiment);

                feedbacks.add(feedback);
            }
        } catch (FileNotFoundException ex) {
            log.error("Specified file path or file name is incorrect: {}, {}", filePath, ex.getMessage());
            throw new FileNotFoundException("Specified file path or file name is incorrect: " + filePath + ex.getMessage());
        } catch (IOException ex) {
            log.error("Exception occurred while reading the file: {}, {}", filePath, ex.getMessage());
            throw new IOException("Exception occurred while reading the file: " + filePath + ex.getMessage());
        } catch (RuntimeException ex) {
            log.error("Exception occurred during file processing: {}, {}", filePath, ex.getMessage());
            throw new RuntimeException("Exception occurred during file processing: " + filePath + ex.getMessage());
        }
        return feedbacks;
    }
}


