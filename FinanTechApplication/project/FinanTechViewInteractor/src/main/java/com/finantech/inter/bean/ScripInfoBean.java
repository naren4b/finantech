package com.finantech.inter.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScripInfoBean {

    String id;

    String scripName;

    Date date;

    Double lastClose;

    Double lastVolume;

    Map<String, Object> values = new HashMap<String, Object>();

    public Map<String, Object> getValues() {
        return values;
    }

    public Object getValue(String key) {
        return values.get(key);
    }

    public void setValue(String key, Object value) {
        values.put(key, value);
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public void setLastVolume(Double lastVolume) {
        this.lastVolume = lastVolume;
    }

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

    public ScripInfoBean() {
    }

    public String getScripName() {
        return scripName;
    }

    public void setScripName(String scripName) {
        this.scripName = scripName;
    }

    @Override
    public String toString() {
        return "ScripInfoEntity [id=" + id + ", scripName=" + scripName
                + ", date=" + date + ", lastClose=" + lastClose
                + ", lastVolume=" + lastVolume + ", values=" + values + "]";
    }

    public String detailToString() {
        StringBuffer sbf = new StringBuffer();
        for (Object value : values.values()) {
            sbf.append(", " + value);
        }
        return sbf.toString().replaceFirst(",", "");
    }

}
