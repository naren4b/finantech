package model;

import java.util.Date;

public class TradeHistory {

    Date date;

    Double lastClose;

    Double lastVolume;

    public TradeHistory(Date date, Double lastClose, Double lastVolume) {
        super();
        this.date = date;
        this.lastClose = lastClose;
        this.lastVolume = lastVolume;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getLastClose() {
        return lastClose;
    }

    public void setLastClose(Double lastClose) {
        this.lastClose = lastClose;
    }

    public Double getLastVolume() {
        return lastVolume;
    }

    public void setLastVolume(Double lastVolume) {
        this.lastVolume = lastVolume;
    }

    @Override
    public String toString() {
        return util.FinanTechUtil.getFormattedDateString(date, "dd-MMM")
                + ",  " + lastClose + ",  " + lastVolume;
    }
}
