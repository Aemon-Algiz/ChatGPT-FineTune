package org.chatgpt.example.training.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.chatgpt.example.training.resources.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DocumentUtil {

    public static final List<Document> TRAINING_DOCUMENTS = new ArrayList<>();
    public static final List<Document> VALIDATION_DOCUMENTS = new ArrayList<>();

    /**
     * This method reads the input Excel file, creates Document objects for each row, and splits the data into training
     * and validation sets. The training and validation documents are stored in the TRAINING_DOCUMENTS and
     * VALIDATION_DOCUMENTS lists, respectively
     *
     * @param excelFile
     */
    public static void createTrainingAndValidationDocuments(String excelFile) {
        List<Document> documents = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    Cell abstractCell = row.getCell(1); // Column B
                    Cell keywordsCell = row.getCell(33); // Column AH

                    if (abstractCell != null && keywordsCell != null &&
                            !abstractCell.getStringCellValue().trim().isEmpty() &&
                            !keywordsCell.getStringCellValue().trim().isEmpty()) {

                        String abstractText = abstractCell.getStringCellValue();
                        String keywordsStr = keywordsCell.getStringCellValue();
                        List<String> keywords = Arrays.asList(keywordsStr.split("\\|"));

                        Document document = new Document(abstractText, keywords);

                        documents.add(document);
                    }
                }
            }
            // Shuffle and split the valid documents into training and validation sets
            Collections.shuffle(documents);
            int splitIndex = (int) (documents.size() * 0.75);
            TRAINING_DOCUMENTS.addAll(documents.subList(0, splitIndex));
            VALIDATION_DOCUMENTS.addAll(documents.subList(splitIndex, documents.size()));

            // Use the training and validation sets as needed
            System.out.println("Training Set: " + TRAINING_DOCUMENTS);
            System.out.println("Validation Set: " + VALIDATION_DOCUMENTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
