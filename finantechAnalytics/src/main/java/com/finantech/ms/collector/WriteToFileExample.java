package com.finantech.ms.collector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFileExample {

    public static void main(String[] args) {
        writeToFile("Hello");
    }

    public static void writeToFile(String content) {
        try {

            File file = new File(
                    "D:\\Personal\\personal\\Hadoop\\project\\blog-final\\src\\main\\resources\\freemarker\\test.csv");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}