package org.chatgpt.example.training;

import org.chatgpt.example.training.utils.DocumentUtil;
import org.chatgpt.example.training.utils.PromptUtil;
import org.chatgpt.example.training.utils.TrainingUtil;

public class App {

    /**
     * This method serves as the entry point of the application. It creates training and validation documents, generates
     * training prompts, and fine-tunes the ChatGPT model using the OpenAI API.
     *
     * @param args the location of the Excel file with abstracts and keywords.
     */
    public static void main( String[] args ) {
        try {
            DocumentUtil.createTrainingAndValidationDocuments(args[0]);

            var trainingSet = PromptUtil.getTrainingPrompts(DocumentUtil.TRAINING_DOCUMENTS);
            var trainingFile = PromptUtil.getTrainingPromptFile(trainingSet);

            var validationSet = PromptUtil.getTrainingPrompts(DocumentUtil.VALIDATION_DOCUMENTS);
            var validationFile = PromptUtil.getTrainingPromptFile(validationSet);

            TrainingUtil.fineTune(trainingFile, validationFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
