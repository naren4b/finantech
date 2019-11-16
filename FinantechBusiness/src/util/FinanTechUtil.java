package util;

import static util.FinanTechConstant.SYMBOL_INDICATORS_CSV_FILE_NAME;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.ScripInfo;

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

    public static ScripInfo getScripInfo(Document aScrip) {
        ScripInfo scripInfo = new ScripInfo();
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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Parsing Exception for " + aScrip);
        }
        return scripInfo;
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
        String filePath = util.FinanTechConstant.ARCH_FILE_FOLDER__PATH
                + SYMBOL_INDICATORS_CSV_FILE_NAME.replace(".csv", "_")
                + formattedDate + ".csv";
        logger.log(Level.INFO, "Archived File Path " + filePath);
        return filePath;
    }

    public static boolean isFileExist() {
        File archivedFile = new File(getArchivedFilePath());
        return archivedFile.exists();
    }
}
