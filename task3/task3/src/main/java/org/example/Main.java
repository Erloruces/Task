package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        File testsFile = new File(args[0]);
        File valuesFile = new File(args[1]);

        Object objTests;
        Object objValues;
        try {
            objTests = new JSONParser().parse(new FileReader(testsFile));
            objValues = new JSONParser().parse(new FileReader(valuesFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JSONObject jObjTests = (JSONObject) objTests;
        JSONObject jObjValues = (JSONObject) objValues;

        JSONArray jsonArrayTests = (JSONArray) jObjTests.get("tests");
        JSONArray jsonArrayValues = (JSONArray) jObjValues.get("values");

        write(jsonArrayTests, jsonArrayValues);

        jObjTests.put("tests", jsonArrayTests);

        try (PrintWriter out = new PrintWriter(new FileWriter("report.json"))) {
            out.write(jObjTests.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static JSONArray write(JSONArray tests, JSONArray values) {

        Iterator t = tests.iterator();

        while (t.hasNext()) {
            JSONObject test = (JSONObject) t.next();

            JSONArray valArr = (JSONArray) test.get("values");

            if (valArr != null) {
                write(valArr, values);
            }

            Iterator v = values.iterator();
            while (v.hasNext()) {
                JSONObject vtest = (JSONObject) v.next();

                if (Objects.equals(vtest.get("id"), test.get("id"))) {
                    test.put("value", vtest.get("value"));
                    break;
                }
            }
        }
        return tests;
    }
}