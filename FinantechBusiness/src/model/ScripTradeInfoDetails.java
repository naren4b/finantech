package model;

import java.util.List;

public class ScripTradeInfoDetails {

    String scripName;

    List<TradeHistory> tradeHistory;

    public String getScripName() {
        return scripName;
    }

    public void setScripName(String scripName) {
        this.scripName = scripName;
    }

    public List<TradeHistory> getTradeHistory() {
        return tradeHistory;
    }

    public void setTradeHistory(List<TradeHistory> tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

}
