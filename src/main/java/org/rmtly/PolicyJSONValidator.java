package org.rmtly;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PolicyJSONValidator {
    public static boolean validate(String pathToInputFile) {
        String jsonContent;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(pathToInputFile)));
        } catch (IOException e) {
            System.err.println("An error occurred while opening the input file!");
            throw new RuntimeException(e);
        }

        checkJSONFile(jsonContent);

        Pattern pattern = Pattern.compile("\"*\"");
        Matcher matcher = pattern.matcher(jsonContent);
        return !matcher.find();
    }

    private static void checkJSONFile(String jsonContent) {
        try {
            JSONObject jsonObject = new JSONObject(jsonContent);
        } catch (JSONException e) {
            System.err.println("JSON file is either not found, damaged, or contains invalid syntax");
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        String jsonFilePath = "src/test/resources/test.json";
        boolean isValid = validate(jsonFilePath);
        System.out.println(isValid);
    }
}
