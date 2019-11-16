package com.finantech.ms.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class FinantechUtil {

    public static Map<Long, List<String>> getTableData(String tableData)
            throws IOException {

        Map<Long, List<String>> records = new HashMap<Long, List<String>>();
        Reader reader = new StringReader(tableData);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.TDF);
        List<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            records.put(csvRecord.getRecordNumber(),
                    convertToStringArray(csvRecord.iterator().next()));
        }
        csvParser.close();
        return records;

    }

    private static List<String> convertToStringArray(String record) {
        String[] values = record.split(",");
        return Arrays.asList(values);
    }
}
