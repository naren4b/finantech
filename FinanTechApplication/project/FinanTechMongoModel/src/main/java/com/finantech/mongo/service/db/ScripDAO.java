package com.finantech.mongo.service.db;

import static com.finantech.mongo.service.db.FinanTechDB.getFinanTechDBInstance;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.finantech.inter.model.ConditionalValueEntity;
import com.finantech.mongo.util.FinanTechUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class ScripDAO {

    private static Logger logger = Logger
            .getLogger(ScripDAO.class.getName());

    private final MongoCollection<Document> scripCollection;

    public ScripDAO() {
        scripCollection = getFinanTechDBInstance()
                .getCollection("ScripCollection");
    }

    public void insertOne(Document scrip) {
        logger.log(Level.FINE, "Insert new Scrip :: " + scrip.toJson());
        scripCollection.insertOne(scrip);

    }

    public void insertMany(List<Document> scrips) {
        logger.log(Level.FINE, "Insert Scrips :: " + scrips.size());
        scripCollection.insertMany(scrips);

    }

    public Document getLatestScrip(String scrip_id) {
        logger.log(Level.FINE, "Get Latest Scrip :: " + scrip_id);
        return getScrips(scrip_id).first();

    }

    public FindIterable<Document> getScrips(String scrip_id) {
        logger.log(Level.FINE, "Get Scrips :: " + scrip_id);
        return scripCollection.find(eq("p_symbol", scrip_id))
                .sort(descending("p_date"));

    }

    public FindIterable<Document> getAllScrips() {
        logger.log(Level.FINE, "Get All Scrips");
        return scripCollection.find().sort(descending("p_date"));

    }

    public FindIterable<Document> filteredScrips(Bson condtion) {
        logger.log(Level.FINE, "Get All Scrips");
        return scripCollection.find(condtion).sort(descending("p_date"));

    }

    public FindIterable<Document> getAllScripsAllTradedInfo() {
        return scripCollection.find().sort(descending("p_date"));
    }

    public FindIterable<Document> getAllScripsLastTradedInfo() {
        ConditionalValueEntity conditionalValueEntity = new ConditionalValueEntity(
                "p_date", "eq",
                FinanTechUtil.getFormattedDateString(
                        new Date(System.currentTimeMillis()
                                - (24 * 60 * 60 * 1000))));// TODO
        Bson condition = FinanTechUtil.getCondtion(conditionalValueEntity);
        return scripCollection.find(condition).sort(descending("p_date"));
    }

}
