package com.finantech.service;

import static com.finantech.util.FinanTechUtil.getScripInfoEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.finantech.ejb.FinanTechService;
import com.finantech.inter.bean.ConditionalValueBean;
import com.finantech.inter.bean.ScripInfoBean;
import com.finantech.inter.model.ConditionalValueEntity;
import com.finantech.inter.model.ScripInfoEntity;
import com.finantech.inter.service.FinanTechModelService;
import com.finantech.mongo.service.impl.FinanTechMogoDBService;
import com.finantech.util.FinanTechUtil;

public class FinanTechServiceImpl implements FinanTechService {

    FinanTechModelService model = new FinanTechMogoDBService();

    public void saveScrips(List<ScripInfoBean> scripInfoBeans) {
        List<ScripInfoEntity> scrips = new ArrayList<ScripInfoEntity>();
        for (ScripInfoBean scripInfoBean : scripInfoBeans) {

            scrips.add(getScripInfoEntity(scripInfoBean));
        }
        model.saveScrips(scrips);
    }

    public void saveScrip(ScripInfoBean scripInfoBean) {
        model.saveScrips(Arrays.asList(getScripInfoEntity(scripInfoBean)));
    }

    public ScripInfoBean getScripsLastTradedInfo(String scripId) {
        ScripInfoEntity scripInfoEntity = model
                .getScripsLastTradedInfo(scripId);
        return FinanTechUtil.getScripInfoBean(scripInfoEntity);
    }

    public List<ScripInfoBean> getAllScripsLastTradedInfo() {
        List<ScripInfoEntity> scripInfoEntities = model
                .getAllScripsLastTradedInfo();
        return FinanTechUtil.getScripInfoBeans(scripInfoEntities);

    }

    public List<ScripInfoBean> getScripsAllTradedInfo(String scripId) {
        List<ScripInfoEntity> scripInfoEntities = model
                .getScripsAllTradedInfo(scripId);
        return FinanTechUtil.getScripInfoBeans(scripInfoEntities);

    }

    public List<ScripInfoBean> getAllScripsAllTradedInfo() {
        List<ScripInfoEntity> scripInfoEntities = model
                .getAllScripsAllTradedInfo();
        return FinanTechUtil.getScripInfoBeans(scripInfoEntities);

    }

    public List<ScripInfoBean> filteredScrips(
            List<ConditionalValueBean> conditionBeans) {
        List<ConditionalValueEntity> conditionalValueEntities = new ArrayList<ConditionalValueEntity>();
        for (ConditionalValueBean conditionalValueBean : conditionBeans) {
            // use conditionalValueBean
            String key = null;
            String operator = null;
            Object value = null;
            ConditionalValueEntity conditionalValueEntity = new ConditionalValueEntity(
                    key, operator, value);
            conditionalValueEntities.add(conditionalValueEntity);
        }
        List<ScripInfoEntity> scripInfoEntities = model
                .filteredScrips(conditionalValueEntities);
        return FinanTechUtil.getScripInfoBeans(scripInfoEntities);

    }

    @Override
    public List<ScripInfoBean> filteredScrips(
            ConditionalValueBean conditionalValueBean) {
        ConditionalValueEntity conditionalValueEntity = FinanTechUtil
                .getConditionalValueEntity(conditionalValueBean);
        List<ScripInfoEntity> scripInfoEntities = model
                .filteredScrips(Arrays.asList(conditionalValueEntity));
        return FinanTechUtil.getScripInfoBeans(scripInfoEntities);

    }

}
