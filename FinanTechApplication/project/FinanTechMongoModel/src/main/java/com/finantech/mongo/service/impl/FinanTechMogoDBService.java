package com.finantech.mongo.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.finantech.inter.model.ConditionalValueEntity;
import com.finantech.inter.model.ScripInfoEntity;
import com.finantech.inter.service.FinanTechModelService;
import com.finantech.mongo.service.db.ScripDAO;
import com.finantech.mongo.util.DataManager;
import com.finantech.mongo.util.FinanTechUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

public class FinanTechMogoDBService implements FinanTechModelService {

    private static Logger logger = Logger
            .getLogger(FinanTechMogoDBService.class.getName());

    ScripDAO scripDAO = new ScripDAO();

    DataManager dataManager = new DataManager();

    public void saveScrips(List<ScripInfoEntity> scrips) {
        List<Document> scripDocs = null;
        try {
            scripDocs = dataManager.prepare(scrips);
            scripDAO.insertMany(scripDocs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveScrip(ScripInfoEntity scrip) {
        List<Document> scripDocs = null;
        try {
            scripDocs = dataManager.prepare(Arrays.asList(scrip));
            scripDAO.insertOne(scripDocs.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ScripInfoEntity getScripsLastTradedInfo(String scripId) {
        return FinanTechUtil.getScripInfo(scripDAO.getLatestScrip(scripId));
    }

    public List<ScripInfoEntity> getAllScripsLastTradedInfo() {
        return FinanTechUtil
                .getScrips(scripDAO.getAllScripsLastTradedInfo());
    }

    public List<ScripInfoEntity> getScripsAllTradedInfo(String scripId) {
        FindIterable<Document> scripDocuments = scripDAO.getScrips(scripId);
        return FinanTechUtil.getScrips(scripDocuments);
    }

    public List<ScripInfoEntity> getAllScripsAllTradedInfo() {
        return FinanTechUtil
                .getScrips(scripDAO.getAllScripsAllTradedInfo());
    }

    public List<ScripInfoEntity> filteredScrips(
            List<ConditionalValueEntity> conditions) {

        logger.log(Level.FINE,
                "Get Filtered Scrips " + conditions.toString());
        Bson[] bsonFilters = new Bson[conditions.size()];
        int i = 0;
        for (ConditionalValueEntity condition : conditions) {
            bsonFilters[i] = FinanTechUtil.getCondtion(condition);
            i++;
        }
        FindIterable<Document> filteredScrips = scripDAO
                .filteredScrips(Filters.and(bsonFilters));

        return FinanTechUtil.getScrips(filteredScrips);
    }
}
