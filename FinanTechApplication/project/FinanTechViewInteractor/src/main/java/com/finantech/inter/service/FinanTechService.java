package com.finantech.inter.service;

import java.util.HashMap;
import java.util.List;

import com.finantech.inter.bean.ConditionalValueBean;
import com.finantech.inter.bean.ScripInfoBean;

public interface FinanTechService {

    public void saveScrips(List<HashMap<String, Object>> scrips);

    public void saveScrip(HashMap<String, Object> scrip);

    public ScripInfoBean getScripsLastTradedInfo(String scripId);

    public List<ScripInfoBean> getAllScripsLastTradedInfo();

    public List<ScripInfoBean> getScripsAllTradedInfo(String scripId);

    public List<ScripInfoBean> getAllScripsAllTradedInfo();

    public List<ScripInfoBean> filteredScrips(ConditionalValueBean condition);
}
