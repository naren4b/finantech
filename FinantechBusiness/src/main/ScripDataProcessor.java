package main;

import static util.FinanTechConstant.SYMBOL_INDICATORS_CSV_FILE_NAME;
import static util.FinanTechUtil.isFileExist;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.json.simple.parser.ParseException;

import filter.DataManager;
import reader.DataCollector;
import reader.SymbolIndicatorFileDownloader;
import service.FinanTechService;

public class ScripDataProcessor {

    private static FinanTechService fints;

    private static SymbolIndicatorFileDownloader symbolIndicatorFileDownloader;

    private static DataCollector dataCollector;

    private static DataManager dataManager;

    public static void main(String[] args)
            throws IOException, InterruptedException, ParseException {
        if (!isFileExist()) {
            initialize(args);
            updateTheDataToDB();
            System.out.println("All scrips are updated.");
        } else {
            System.out.println("No Records to update");
        }
    }

    private static void initialize(String[] args) {
        fints = new FinanTechService();
        symbolIndicatorFileDownloader = new SymbolIndicatorFileDownloader();
        dataCollector = new DataCollector();
        dataManager = new DataManager();

    }

    private static void updateTheDataToDB() throws FileNotFoundException,
            IOException, InterruptedException {
        symbolIndicatorFileDownloader.download();
        Map<String, Map<String, Object>> scripDataMap = dataCollector
                .collectData();
        List<Document> scripDocs = dataManager.prepare(scripDataMap);

        fints.saveScrips(scripDocs);

    }

    private static void updateTheOldDataToDB() throws FileNotFoundException,
            IOException, InterruptedException {
        String[] ext = { "2016_08_28", "2016_08_29", "2016_08_30",
                "2016_08_31", "2016_09_01" };
        String paths = util.FinanTechConstant.ARCH_FILE_FOLDER__PATH
                + SYMBOL_INDICATORS_CSV_FILE_NAME.replace(".csv", "_");

        for (int i = 0; i < ext.length; i++) {
            String fileName = paths + ext[i] + ".csv";
            Map<String, Map<String, Object>> scripDataMap = dataCollector
                    .collectData();
            List<Document> scripDocs = dataManager.prepare(scripDataMap);

            fints.saveScrips(scripDocs);
        }

    }

}
