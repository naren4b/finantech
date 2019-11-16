package com.fint;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import model.ScripTradeInfoDetails;
import model.TradeHistory;

public class ScripInfo {

    String[] lables;

    Double[] data;

    public String getScripInfo(
            ScripTradeInfoDetails scripTradeInfoDetails) {

        StringBuffer sbf = new StringBuffer();
        List<TradeHistory> tradeHistories = scripTradeInfoDetails
                .getTradeHistory();

        StringBuffer tableData = new StringBuffer();
        int index = 0;
        lables = new String[tradeHistories.size()];
        data = new Double[tradeHistories.size()];
        for (TradeHistory tradeHistory : tradeHistories) {
            Date date = tradeHistory.getDate();
            String dateString = util.FinanTechUtil
                    .getFormattedDateString(date, "dd-MMM");
            lables[index] = dateString;
            data[index] = tradeHistory.getLastClose();
            tableData.append("<tr class=\"expense\"><td>" + (++index)
                    + "</td><td class=\"description\">" + dateString
                    + "</td><td class=\"positive net_balance\">"
                    + tradeHistory.getLastClose()
                    + "</td><td class=\"positive net_balance\">"
                    + tradeHistory.getLastVolume() + " </td></tr>");
        }
        return sbf.append(tableData.append("</tbody></Table></div>"))
                .toString();

    }

    public String getScripLabels() {
        StringBuffer sbf = new StringBuffer("[");
        for (int i = 0; i < lables.length; i++) {
            sbf.append(", \"" + lables[i] + "\"");
        }
        sbf.append("]");
        return sbf.toString().replaceFirst(",", "");
    }

    public String getdataValues() {

        StringBuffer sbf = new StringBuffer("[");
        for (int i = 0; i < data.length; i++) {
            sbf.append("," + data[i]);
        }
        sbf.append("]");
        return sbf.toString().replaceFirst(",", "");
    }

    public Double getScaleMax() {
        return data[0] + 10;
    }

    public Double getScaleMin() {
        return data[0] - 10;
    }

}
