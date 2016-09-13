package com.finantech.mongo.util;

import static com.finantech.mongo.util.FinanTechConstant.ALL_COLUMN_NAMES;
import static com.finantech.mongo.util.FinanTechConstant.SYMBOL_INDICATORS_CSV_FILE_NAME;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.ne;
import static com.mongodb.client.model.Filters.nin;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.finantech.inter.model.ConditionalValueEntity;
import com.finantech.inter.model.ScripInfoEntity;
import com.mongodb.client.FindIterable;

public class FinanTechUtil {

    private static Logger logger = Logger
            .getLogger(FinanTechUtil.class.getName());

    private static JSONParser parser = new JSONParser();

    public static Date getFormattedDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd z");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {

        }
        return new Date();
    }

    public static String getFormattedDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd z");
        return sdf.format(date);
    }

    public static String getFormattedDateString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static JSONObject getJsonObject(Document doc)
            throws org.json.simple.parser.ParseException {
        JSONObject jsonObject = null;
        jsonObject = (JSONObject) parser.parse(doc.toJson());
        return jsonObject;
    }

    public static ScripInfoEntity getScripInfo(Document aScrip) {
        ScripInfoEntity scripInfo = new ScripInfoEntity();
        JSONObject jsonObject;
        try {
            jsonObject = getJsonObject(aScrip);
            scripInfo.setId((String) jsonObject.get("_id"));
            scripInfo.setScripName((String) jsonObject.get("p_symbol"));
            scripInfo.setLastVolume(
                    getDouble(jsonObject.get("last_volume")));
            scripInfo.setDate((getFormattedDate(
                    (String) (jsonObject.get("p_date")))));
            scripInfo.setLastClose(getDouble(jsonObject.get("last_close")));
            ScripColumns.initialize(ALL_COLUMN_NAMES);
            for (int i = 0; i < ALL_COLUMN_NAMES.length; i++) {
                scripInfo.setValue(ALL_COLUMN_NAMES[i],
                        applyAfterConverting(ALL_COLUMN_NAMES[i],
                                jsonObject.get(ALL_COLUMN_NAMES[i])));
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Parsing Exception for " + aScrip);
        }
        return scripInfo;
    }

    private static Object applyAfterConverting(String columnName,
            Object object) {
        if (object != null) {
            if (ScripColumns.getColumnType(columnName)
                    .equals(Double.class)) {
                return getDouble(object);
            } else if (ScripColumns.getColumnType(columnName)
                    .equals(Date.class)) {
                getFormattedDate((String) (object));
            } else {
                return object.toString();
            }
        }
        return null;
    }

    private static Double getDouble(Object object) {
        try {
            if (object == null) { return Double.parseDouble("0.0"); }
            return (Double) object;
        } catch (Exception e) {
            return Double.parseDouble("0.0");
        }
    }

    public static String getArchivedFilePath() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
        Date todaysDate = new Date(System.currentTimeMillis());
        String formattedDate = formatter.format(todaysDate);
        String filePath = com.finantech.mongo.util.FinanTechConstant.ARCH_FILE_FOLDER__PATH
                + SYMBOL_INDICATORS_CSV_FILE_NAME.replace(".csv", "_")
                + formattedDate + ".csv";
        logger.log(Level.INFO, "Archived File Path " + filePath);
        return filePath;
    }

    public static boolean isFileExist() {
        File archivedFile = new File(getArchivedFilePath());
        return archivedFile.exists();
    }

    public static List<ScripInfoEntity> getScrips(
            FindIterable<Document> scripDocuments) {
        List<ScripInfoEntity> scrips = new ArrayList<ScripInfoEntity>();
        for (Document scripDocument : scripDocuments) {
            scrips.add(FinanTechUtil.getScripInfo(scripDocument));
        }
        return scrips;
    }

    public static Bson getCondtion(ConditionalValueEntity condition) {
        String operator = condition.getOperator();
        String key = condition.getKey();
        Object value = condition.getValue();
        if (operator.equals("gt")) {
            return gt(key, value);
        } else if (operator.equals("gte")) {
            return gte(key, value);
        } else if (operator.equals("lt")) {
            return lt(key, value);
        } else if (operator.equals("lte")) {
            return lte(key, value);
        } else if (operator.equals("ne")) {
            return ne(key, value);
        } else if (operator.equals("in")) {
            return in(key, value);
        } else if (operator.equals("nin")) {
            return nin(key, value);
        } else {
            return eq(key, value);
        }

    }

}
