package com.finantech.inter.bean;

import java.util.List;

public class ScripTradeInfoDetailsBean {

    String scripName;

    List<TradeHistoryBean> tradeHistory;

    public String getScripName() {
        return scripName;
    }

    public void setScripName(String scripName) {
        this.scripName = scripName;
    }

    public List<TradeHistoryBean> getTradeHistory() {
        return tradeHistory;
    }

    public void setTradeHistory(List<TradeHistoryBean> tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

}
