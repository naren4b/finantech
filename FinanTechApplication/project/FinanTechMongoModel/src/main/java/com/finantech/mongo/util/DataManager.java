package com.finantech.mongo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.finantech.inter.model.ScripInfoEntity;

public class DataManager {

    private static Logger logger = Logger
            .getLogger(DataManager.class.getName());

    public List<Document> prepare(List<ScripInfoEntity> inputScrips)
            throws IOException {
        logger.log(Level.FINE, "prepare data " + inputScrips.size());
        List<Document> scrips = new ArrayList<Document>();
        for (ScripInfoEntity scripInfoEntity : inputScrips) {
            Document scripDoc = new Document();
            scripDoc.append("_id",
                    (String) scripInfoEntity.getValue("p_symbol") + "_"
                            + (String) scripInfoEntity.getValue("p_date"));
            for (String columnName : FinanTechConstant.ALL_COLUMN_NAMES) {
                appendAfterconvert(scripDoc, columnName,
                        scripInfoEntity.getValue(columnName));
            }
            scrips.add(scripDoc);
        }
        return scrips;
    }

    private void appendAfterconvert(Document scripDetails,
            String columnName, Object object) {
        logger.log(Level.FINE, "appendAfterconvert data columnName:"
                + columnName + " : " + object);
        if (object != null) {
            String value = object.toString();
            try {
                if (ScripColumns.getColumnType(columnName)
                        .equals(Double.class)) {
                    scripDetails.append(columnName, new Double(value));
                } else if (ScripColumns.getColumnType(columnName)
                        .equals(Date.class)) {
                    scripDetails.append(columnName,
                            FinanTechUtil.getFormattedDateString(
                                    FinanTechUtil.getFormattedDate(
                                            value + " IST")));
                } else {
                    scripDetails.append(columnName, value);
                }
            } catch (NumberFormatException ne) {
                logger.log(Level.FINE, "Exception data columnName:"
                        + columnName + " : " + object, ne);
                scripDetails.append(columnName, new Double("0.0"));
            } catch (Exception e) {
                logger.log(Level.FINE, "Exception data columnName:"
                        + columnName + " : " + object, e);
                scripDetails.append(columnName, value);
            }
        } else {
            scripDetails.append(columnName, "");
        }

    }
}
