package model;

import static util.FinanTechUtil.getFormattedDateString;

import java.util.Date;

public class ScripInfo {

    String id;

    String scripName;

    Date date;

    Double lastClose;

    Double lastVolume;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getLastVolume() {
        return lastVolume;
    }

    public void setLastVolume(double lastVolume) {
        this.lastVolume = lastVolume;
    }

    public ScripInfo() {
    }

    public String getScripName() {
        return scripName;
    }

    public void setScripName(String scripName) {
        this.scripName = scripName;
    }

    @Override
    public String toString() {
        return "Name:\t" + scripName + " \nId:\t" + id + "\nDate:\t"
                + getFormattedDateString(date,"dd-MMM") + "\nLast Close:\t" + lastClose
                + "\nLast Volume:\t" + lastVolume + "\n";
    }

}
