package com.finantech.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.finantech.inter.bean.ConditionalValueBean;
import com.finantech.inter.bean.ScripInfoBean;

@Remote
public interface FinanTechService {

    public void saveScrips(List<ScripInfoBean> scripInfoBeans);

    public void saveScrip(ScripInfoBean scripInfoBean );

    public ScripInfoBean getScripsLastTradedInfo(String scripId);

    public List<ScripInfoBean> getAllScripsLastTradedInfo();

    public List<ScripInfoBean> getScripsAllTradedInfo(String scripId);

    public List<ScripInfoBean> getAllScripsAllTradedInfo();

    public List<ScripInfoBean> filteredScrips(
            ConditionalValueBean condition);

}
