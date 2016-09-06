package com.cdk.finaltest.util;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CsvUtility {
    public static List<String[]> readLines(String filePath)throws IOException {

        CSVReader reader = new CSVReader(new FileReader(filePath), ',');
        List<String[]> detailsList = new ArrayList<>();
        String[] record = null;
        reader.readNext();
        while((record = reader.readNext()) != null){
            detailsList.add(record);
        }
        reader.close();
        return detailsList;
    }
    private static void close(Object... objects) {

        for (Object object : objects) {
            if (null != object) {
                try {
                    if (object instanceof CSVReader) {
                        ((CSVReader) object).close();
                    }else {
                        //throw new Exception("Invalid Argument !!!");
                    }
                } catch (IOException e) {
                    //throw new EcomException(e.toString());
                }
            }
        }
    }
}
