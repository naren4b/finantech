package com.finantech.ms.data;

import java.io.Serializable;

public class MutualFundVO<T> implements Comparable<T>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    String id;

    String name;

    String category;

    String rating;

    String ytdReturn;

    String totalExpenseRatio;

    String LastClose;

    public MutualFundVO() {
    }

    public MutualFundVO(String[] attribute) {
        setId(attribute[0]);
        setName(attribute[1]);
        setCategory(attribute[2]);
        setRating(attribute[3]);
        setYtdReturn(attribute[4]);
        setTotalExpenseRatio(attribute[5]);
        setLastClose(attribute[6]);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYtdReturn() {
        return ytdReturn;
    }

    public void setYtdReturn(String ytdReturn) {
        this.ytdReturn = ytdReturn;
    }

    public String getTotalExpenseRatio() {
        return totalExpenseRatio;
    }

    public void setTotalExpenseRatio(String totalExpenseRatio) {
        this.totalExpenseRatio = totalExpenseRatio;
    }

    public String getLastClose() {
        return LastClose;
    }

    public void setLastClose(String lastClose) {
        LastClose = lastClose;
    }

    public int compareTo(T o) {
        Double thisYtd = Double.parseDouble(getYtdReturn());
        Double givenYtd = Double
                .parseDouble(((MutualFundVO) o).getYtdReturn());
        return thisYtd.compareTo(givenYtd);
    }

}
