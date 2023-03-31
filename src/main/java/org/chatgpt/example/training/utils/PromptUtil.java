package org.chatgpt.example.training.utils;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.chatgpt.example.training.resources.Document;
import org.chatgpt.example.training.resources.TrainingPrompt;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PromptUtil {

    private static final Gson gson = new Gson();

    /**
     * This method extracts the training prompts from a list of documents. It returns a list of TrainingPrompt objects
     * containing the abstracts and their corresponding keywords.
     *
     * @param documents
     * @return
     */
    public static List<TrainingPrompt> getTrainingPrompts(List<Document> documents) {
        return documents.stream()
                .map(Document::getTrainingPrompt)
                .collect(Collectors.toList());
    }

    /**
     * This method generates a temporary file containing the training prompts in JSON format. It takes a list of
     * TrainingPrompt objects as input and returns the absolute path of the generated file.
     *
     * @param trainingPrompts
     * @return
     * @throws IOException
     */
    public static String getTrainingPromptFile(List<TrainingPrompt> trainingPrompts) throws IOException {
        File trainingFile = File.createTempFile("training", ".json");

        String trainingString = trainingPrompts.stream()
                .map(gson::toJson)
                .collect(Collectors.joining("\n"));

        FileUtils.writeStringToFile(trainingFile, trainingString);

        return trainingFile.getAbsolutePath();
    }

}
