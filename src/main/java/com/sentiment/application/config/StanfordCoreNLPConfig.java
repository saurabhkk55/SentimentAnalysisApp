package com.sentiment.application.config;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

@Configuration
public class StanfordCoreNLPConfig {

    @Bean
    public StanfordCoreNLP stanfordCoreNLP() {
        // Set up the pipeline properties for sentiment analysis
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse,sentiment");
        return new StanfordCoreNLP(props);
    }
}

