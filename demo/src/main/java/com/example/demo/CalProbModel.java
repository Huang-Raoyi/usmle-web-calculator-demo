package com.example.demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalProbModel {
    ArrayList<Record> records = new ArrayList<>();
    final static String PATH = "/Users/huangraoyi/IdeaProjects/distributedSystemsISM/usmle-probability-calculator/demo";


    public void save() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String timestamp = dtf.format(now);

        String filename = PATH + "/output_" + timestamp + ".csv";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Record record : records) {
                bw.write(record.toString());
                bw.newLine();
                System.out.println(record.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(Record record) {
        records.add(record);
    }
}
