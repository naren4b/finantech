package service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import db.FinanTechDB;
import db.ScripDAO;
import model.ScripInfo;
import model.ScripTradeInfoDetails;
import model.TradeHistory;
import util.FinanTechUtil;

public class FinanTechService {

    private static Logger logger = Logger
            .getLogger(FinanTechDB.class.getName());

    ScripDAO scripDAO = new ScripDAO();

    public void saveScrips(List<Document> scripDocs) {
        logger.log(Level.FINE, "Save Scrip " + scripDocs.size());
        scripDAO.insertMany(scripDocs);
    }

    public ScripInfo getLatestScripDetails(String scripId) {
        logger.log(Level.FINE, "Get Latest Scrip Detail :: " + scripId);
        return FinanTechUtil.getScripInfo(scripDAO.getLatestScrip(scripId));
    }

    public List<ScripInfo> getScripDetails(String scripId) {
        logger.log(Level.FINE, "Get Scrip Detail :: " + scripId);
        List<ScripInfo> scrips = new ArrayList<ScripInfo>();
        FindIterable<Document> scripDocuments = scripDAO.getScrips(scripId);
        for (Document scripDocument : scripDocuments) {
            scrips.add(FinanTechUtil.getScripInfo(scripDocument));
        }
        return scrips;
    }

    public ScripTradeInfoDetails getScripTradeInfoDetails(String scripId) {
        logger.log(Level.FINE, "Get Latest Scrip Detail :: " + scripId);
        ScripTradeInfoDetails scripTradeInfoDetails = new ScripTradeInfoDetails();
        List<ScripInfo> scripDetails = getScripDetails(scripId);
        List<TradeHistory> tradeHistories = new ArrayList<TradeHistory>();
        for (ScripInfo scripInfo : scripDetails) {
            scripTradeInfoDetails.setScripName(scripInfo.getScripName());
            tradeHistories.add(new TradeHistory(scripInfo.getDate(),
                    scripInfo.getLastClose(), scripInfo.getLastVolume()));
        }
        scripTradeInfoDetails.setTradeHistory(tradeHistories);
        return scripTradeInfoDetails;
    }

    public List<ScripInfo> getAllScripDetails() {
        logger.log(Level.FINE, "Get All Scrip Detail.");
        List<ScripInfo> scrips = new ArrayList<ScripInfo>();
        FindIterable<Document> scripDocuments = scripDAO.getAllScrips();
        for (Document scripDocument : scripDocuments) {
            scrips.add(FinanTechUtil.getScripInfo(scripDocument));
        }
        return scrips;
    }

}
