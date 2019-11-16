package db;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class FinanTechDB {

    private static Logger logger = Logger
            .getLogger(FinanTechDB.class.getName());

    private static final String mongoURIString = "mongodb://localhost";

    private static MongoDatabase finanTechDB;

    private static FinanTechDB db = new FinanTechDB();

    public static MongoDatabase getFinanTechDBInstance() {
        return finanTechDB;
    }

    private FinanTechDB() {
        final MongoClient mongoClient = new MongoClient(
                new MongoClientURI(mongoURIString));
        finanTechDB = mongoClient.getDatabase("FinanTechDB");
        logger.log(Level.FINE, "FinanTechDB is connected.");
    }

}
