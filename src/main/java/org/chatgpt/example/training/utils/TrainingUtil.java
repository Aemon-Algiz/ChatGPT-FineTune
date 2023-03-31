package org.chatgpt.example.training.utils;

import com.theokanning.openai.file.File;
import com.theokanning.openai.finetune.FineTuneRequest;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TrainingUtil {

    private static final String TOKEN = "YOUR_TOKEN_HERE";

    public static void fineTune(String trainingFilePath, String validationFilePath) {
        OpenAiService openAiService = new OpenAiService(TOKEN, Duration.of(10, ChronoUnit.MINUTES));

        File uploadedFile = openAiService.uploadFile("fine-tune", trainingFilePath);
        File validationFile = openAiService.uploadFile("fine-tune", validationFilePath);

        FineTuneRequest fineTuneRequest = FineTuneRequest.builder()
                .trainingFile(uploadedFile.getId())
                .validationFile(validationFile.getId())
                .model("text-davinci-002")
                .suffix("jeff-training-model-delux-8574843")
                .build();

        var finalRequest = openAiService.createFineTune(fineTuneRequest);
        Date createdDate = new Date(finalRequest.getCreatedAt());
        System.out.println(finalRequest.getFineTunedModel() + " was created at " + createdDate);
    }

}
