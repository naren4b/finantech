package com.finantech.inter.model;

import java.util.List;

public class ScripTradeInfoDetailsEntity {

    String scripName;

    List<TradeHistoryEntity> tradeHistory;

    public String getScripName() {
        return scripName;
    }

    public void setScripName(String scripName) {
        this.scripName = scripName;
    }

    public List<TradeHistoryEntity> getTradeHistory() {
        return tradeHistory;
    }

    public void setTradeHistory(List<TradeHistoryEntity> tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

}
