package org.rmtly;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PolicyJSONValidator {
    public static boolean validate(String pathToInputFile) {
        String jsonContent;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(pathToInputFile)));
        } catch (IOException e) {
            System.err.println("An error occurred while opening the input file");
            throw new RuntimeException(e);
        }

        checkJSONFile(jsonContent);

        Pattern pattern = Pattern.compile("\"\\*\"");
        Matcher matcher = pattern.matcher(jsonContent);
        return !matcher.find();
    }

    private static void checkJSONFile(String jsonContent) {
        String schemaPath = "src/main/resources/schema.json";
        try {
            JSONObject jsonObject = new JSONObject(jsonContent);

            // check json schema
            InputStream schemaInputStream = new FileInputStream(schemaPath);
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaInputStream));
            Schema schema = SchemaLoader.load(rawSchema);

            schema.validate(jsonObject);

        } catch (JSONException e) {
            throw new RuntimeException("JSON file is either not found, damaged, or contains invalid syntax");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Schema config file (src/main/resources/schema.json) not found");
        } catch (org.everit.json.schema.ValidationException e) {
            throw new RuntimeException("Incorrect JSON schema");
        }
    }
}
