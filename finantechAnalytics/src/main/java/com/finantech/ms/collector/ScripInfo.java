package com.finantech.ms.collector;

import java.util.Date;
import java.util.List;

import model.ScripTradeInfoDetails;
import model.TradeHistory;

public class ScripInfo extends WebSite {

    public static String getScripInfo(
            ScripTradeInfoDetails scripTradeInfoDetails) {

        StringBuffer sbf = new StringBuffer();

        List<TradeHistory> tradeHistories = scripTradeInfoDetails
                .getTradeHistory();

        StringBuffer tableData = new StringBuffer();
        int index = 0;
        for (TradeHistory tradeHistory : tradeHistories) {
            Date date = tradeHistory.getDate();
            String dateString = util.FinanTechUtil
                    .getFormattedDateString(date, "dd-MMM");

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

}
