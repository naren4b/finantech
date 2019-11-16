package reader;

import static util.FinanTechUtil.getArchivedFilePath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.CSVReader;

import util.ScripColumns;

public class DataCollector {

    private static Logger logger = Logger
            .getLogger(DataCollector.class.getName());

    public Map<String, Map<String, Object>> collectData() {
        List<String[]> scrips = getScripInfo();
        String[] columnNames = getAllColumns(scrips);
        ScripColumns.initialize(columnNames);
        return getScripDataMap(scrips, columnNames);
    }

    private Map<String, Map<String, Object>> getScripDataMap(
            List<String[]> scrips, String[] columnNames) {
        logger.log(Level.FINE,
                "GetScripDataMap scrips size" + scrips.size());
        Map<String, Map<String, Object>> scripDataMap = new HashMap<String, Map<String, Object>>();
        boolean isHeader = true;
        for (String[] scrip : scrips) {
            logger.log(Level.FINE, "Scrip :: " + scrip.toString());
            if (isHeader) {
                isHeader = false;
                continue;
            }
            int index = 0;
            HashMap<String, Object> scripInfoMap = new HashMap<String, Object>();
            for (String columnName : columnNames) {
                scripInfoMap.put(columnName, scrip[index]);
                index++;
            }
            scripDataMap.put(scrip[0], scripInfoMap);
        }
        return scripDataMap;
    }

    private String[] getAllColumns(List<String[]> scrips) {
        String[] columnNames = scrips.get(0);
        return columnNames;
    }

    private List<String[]> getScripInfo() {
        CSVReader reader;
        List<String[]> scrips = new ArrayList<String[]>();
        try {
            reader = getCSVReader();
            scrips = reader.readAll();
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found ", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException while parsing ", e);
        }
        return scrips;
    }

    private CSVReader getCSVReader() throws FileNotFoundException {
        return new CSVReader(getFileReader());
    }

    private FileReader getFileReader() throws FileNotFoundException {
        logger.log(Level.FINE, "Read File ");
        File f = new File(getArchivedFilePath());
        logger.log(Level.FINE, "Path " + f.getAbsolutePath());
        return new FileReader(f);
    }

}
