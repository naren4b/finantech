package db;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.descending;
import static db.FinanTechDB.getFinanTechDBInstance;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

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

}
