package org.example;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject objectTest = (JSONObject) parser.parse(getJsonFile("data/tests.json"));
            JSONObject objectValues = (JSONObject)  parser.parse(getJsonFile("data/values.json"));
            JSONArray valuesArray = getValuesArray(objectValues);
            JSONArray testsArray = (JSONArray) objectTest.get("tests");
//            setValue(testsArray, valuesArray);
            checkArray(testsArray, valuesArray);
            System.out.println(testsArray);
            JSONObject report = new JSONObject();
            report.put("tests", testsArray);
            PrintWriter writer = new PrintWriter("data/report.json");
//            writer.write(String.valueOf(report));
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            Object jsonObject = mapper.readValue(String.valueOf(objectTest), Object.class);
            String prettyJson = mapper.writeValueAsString(jsonObject);
            writer.write(prettyJson);


            writer.flush();
            writer.close();



        } catch (ParseException | JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static JSONArray getValuesArray (JSONObject object) {
        return (JSONArray) object.get("values");
    }
    public static String getJsonFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
    public static void setValue (JSONArray tests, JSONArray values) {
        tests.forEach(o -> {
            JSONObject object = (JSONObject) o;
            for (Object val : values) {
                JSONObject value = (JSONObject) val;
                if (value.get("id").equals(object.get("id"))) {
                    object.put("value", value.get("value"));
                    break;
                }
            }
            if (object.containsKey("values") ) {
                JSONArray objectValuesArray = (JSONArray) object.get("values");
                setValue(objectValuesArray, values);
            }

        });
    }
    public static void checkArray(JSONArray array, JSONArray values) {
        array.forEach(o -> {
            JSONObject object = (JSONObject) o;
            setValue(object, values);
            if (object.containsKey("values") ) {
                JSONArray objectValuesArray = (JSONArray) object.get("values");
                checkArray(objectValuesArray, values);
            }
        });
    }
    public  static void setValue (JSONObject object, JSONArray values) {
        for (Object val : values) {
            JSONObject value = (JSONObject) val;
            if (value.get("id").equals(object.get("id"))) {
                object.put("value", value.get("value"));
                break;
            }
        }
    }
}
