package org.chatgpt.example.training.resources;

public class TrainingPrompt {

    private final String prompt;
    private final String completion;

    public TrainingPrompt(String prompt, String completion) {
        this.prompt = prompt;
        this.completion = completion;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getCompletion() {
        return completion;
    }

}
