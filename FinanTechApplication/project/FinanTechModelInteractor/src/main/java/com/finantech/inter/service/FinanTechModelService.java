package com.finantech.inter.service;

import java.util.List;

import com.finantech.inter.model.ConditionalValueEntity;
import com.finantech.inter.model.ScripInfoEntity;

public interface FinanTechModelService {

    public void saveScrips(List<ScripInfoEntity> scrips);

    public void saveScrip(ScripInfoEntity scrip);

    public ScripInfoEntity getScripsLastTradedInfo(String scripId);

    public List<ScripInfoEntity> getAllScripsLastTradedInfo();

    public List<ScripInfoEntity> getScripsAllTradedInfo(String scripId);

    public List<ScripInfoEntity> getAllScripsAllTradedInfo();

    public List<ScripInfoEntity> filteredScrips(
            List<ConditionalValueEntity> condition);
}
