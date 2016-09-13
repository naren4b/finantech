package com.finantech.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.finantech.ejb.FinanTechService;
import com.finantech.inter.bean.ScripInfoBean;
import com.finantech.util.EJBClientUtility;

public class ScripSelectionController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        StringBuffer options = new StringBuffer();

        FinanTechService finanTechService = EJBClientUtility
                .getFinanTechServiceBean();
        List<ScripInfoBean> allScripsAllTradedInfo = finanTechService
                .getAllScripsAllTradedInfo();
        for (ScripInfoBean scripInfoBean : allScripsAllTradedInfo) {
            options.append("<option value=\"" + scripInfoBean.getId()
                    + "\">" + scripInfoBean.getScripName() + "</option>");
        }

        request.setAttribute("options", options.toString());
        return new ModelAndView("scripselection", "options",
                options.toString());

    }

}