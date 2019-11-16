package com.finantech.ms.collector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {

    public static String readFile() {
        StringBuffer fileContent = new StringBuffer();
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(
                    "D:\\Personal\\personal\\Hadoop\\project\\blog-final\\src\\main\\resources\\freemarker\\test.csv"));

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                fileContent.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fileContent.toString();
    }
}
