package org.chatgpt.example.training.resources;

import java.util.List;
import java.util.stream.Collectors;

public class Document {

    private final String abstractText;
    private final List<String> keywords;

    public Document(String abstractText, List<String> keywords) {
        this.abstractText = abstractText;
        this.keywords = keywords;
    }

    public TrainingPrompt getTrainingPrompt() {
        String completion = keywords.stream()
                .map(String::trim)
                .collect(Collectors.joining("\n"));

        return new TrainingPrompt(abstractText, completion);
    }
}