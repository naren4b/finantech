package com.finantech.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.parser.JSONParser;

import com.finantech.inter.bean.ConditionalValueBean;
import com.finantech.inter.bean.ScripInfoBean;
import com.finantech.inter.model.ConditionalValueEntity;
import com.finantech.inter.model.ScripInfoEntity;

public class FinanTechUtil {

    private static Logger logger = Logger
            .getLogger(FinanTechUtil.class.getName());

    private static JSONParser parser = new JSONParser();

    public static Date getFormattedDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd z");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {

        }
        return new Date();
    }

    public static String getFormattedDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd z");
        return sdf.format(date);
    }

    public static String getFormattedDateString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static ScripInfoBean getScripInfoBean(
            ScripInfoEntity scripInfoEntity) {
        ScripInfoBean scripInfoBean = new ScripInfoBean();
        scripInfoBean.setDate(scripInfoEntity.getDate());
        scripInfoBean.setId(scripInfoEntity.getId());
        scripInfoBean.setLastClose(scripInfoEntity.getLastClose());
        scripInfoBean.setLastVolume(scripInfoEntity.getLastVolume());
        scripInfoBean.setScripName(scripInfoEntity.getScripName());
        scripInfoBean.setValues(scripInfoEntity.getValues());

        return scripInfoBean;
    }

    public static ScripInfoEntity getScripInfoEntity(
            ScripInfoBean scripInfoBean) {
        ScripInfoEntity scripInfoEntity = new ScripInfoEntity();
        scripInfoEntity.setDate(scripInfoBean.getDate());
        scripInfoEntity.setId(scripInfoBean.getId());
        scripInfoEntity.setLastClose(scripInfoBean.getLastClose());
        scripInfoEntity.setLastVolume(scripInfoBean.getLastVolume());
        scripInfoEntity.setScripName(scripInfoBean.getScripName());
        scripInfoEntity.setValues(scripInfoBean.getValues());

        return scripInfoEntity;
    }

    public static List<ScripInfoBean> getScripInfoBeans(
            List<ScripInfoEntity> scripInfoEntities) {

        List<ScripInfoBean> scripInfoBeans = new ArrayList<ScripInfoBean>();

        for (ScripInfoEntity scripInfoEntity : scripInfoEntities) {
            ScripInfoBean scripInfoBean = getScripInfoBean(scripInfoEntity);
            scripInfoBeans.add(scripInfoBean);
        }

        return scripInfoBeans;

    }

    public static ConditionalValueEntity getConditionalValueEntity(
            ConditionalValueBean conditionalValueBean) {
        String key = conditionalValueBean.getKey();
        String operator = conditionalValueBean.getOperator();
        Object value = conditionalValueBean.getValue();
        ConditionalValueEntity conditionalValueEntity = new ConditionalValueEntity(
                key, operator, value);
        return conditionalValueEntity;
    }

}
