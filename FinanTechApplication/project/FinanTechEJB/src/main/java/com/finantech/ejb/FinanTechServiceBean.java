package com.finantech.ejb;

import java.util.List;

import javax.ejb.Stateless;

import com.finantech.inter.bean.ConditionalValueBean;
import com.finantech.inter.bean.ScripInfoBean;
import com.finantech.service.FinanTechServiceImpl;

/**
 * Session Bean implementation class FinanTechServiceEJB
 */
@Stateless(mappedName="FinanTechServiceBean") 
public class FinanTechServiceBean implements FinanTechService {

    FinanTechServiceImpl finanTechService = new FinanTechServiceImpl();
    
    

    /**
     * Default constructor.
     */
    public FinanTechServiceBean() {

    }

    @Override
    public void saveScrips(List<ScripInfoBean> scripInfoBeans) {
        finanTechService.saveScrips(scripInfoBeans);
    }

    @Override
    public void saveScrip(ScripInfoBean scripInfoBean) {
        finanTechService.saveScrip(scripInfoBean);

    }

    @Override
    public ScripInfoBean getScripsLastTradedInfo(String scripId) {
        finanTechService.getScripsLastTradedInfo(scripId);
        return null;
    }

    @Override
    public List<ScripInfoBean> getAllScripsLastTradedInfo() {
        return finanTechService.getAllScripsLastTradedInfo();
    }

    @Override
    public List<ScripInfoBean> getScripsAllTradedInfo(String scripId) {
        return finanTechService.getScripsAllTradedInfo(scripId);
    }

    @Override
    public List<ScripInfoBean> getAllScripsAllTradedInfo() {
        return finanTechService.getAllScripsAllTradedInfo();
    }

    @Override
    public List<ScripInfoBean> filteredScrips(
            ConditionalValueBean condition) {
        return finanTechService.filteredScrips(condition);
    }

}
