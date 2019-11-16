package main;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import model.ScripInfo;
import model.ScripTradeInfoDetails;
import model.TradeHistory;
import service.FinanTechService;

public class TestScripDataProcessor {

    private static FinanTechService fints;

    public static void main(String[] args)
            throws IOException, InterruptedException, ParseException {
        initialize(args);
        test();
    }

    private static void test() {
        String scripId = "EICHERMOT";
        testGetScripDetail(scripId);
        testGetScripDetails(scripId);
        testGetScripTradeInfoDetails(scripId);
    }

    private static void testGetScripTradeInfoDetails(String scripId) {

        ScripTradeInfoDetails scripTradeInfoDetails = fints
                .getScripTradeInfoDetails(scripId);
        int rowNumber = 0;
        System.out
                .println("Name : " + scripTradeInfoDetails.getScripName());
        List<TradeHistory> tradeHistories = scripTradeInfoDetails
                .getTradeHistory();
        System.out.println("Sl Date Last Close Last Volume");
        for (TradeHistory tradeHistory : tradeHistories) {
            System.out.println(++rowNumber + ". " + tradeHistory);
        }

    }

    private static void testGetScripDetails(String scripId) {
        List<ScripInfo> scripDetails = fints.getScripDetails(scripId);
        int rowNumber = 0;
        for (ScripInfo scripInfo : scripDetails) {
            System.out.println(++rowNumber + ". " + scripInfo);
        }

    }

    private static void testGetScripDetail(String scripId) {
        System.out.println(fints.getLatestScripDetails(scripId));

    }

    private static void initialize(String[] args) {
        fints = new FinanTechService();

    }

}
