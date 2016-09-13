package com.finantech.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.finantech.ejb.FinanTechService;
import com.finantech.exception.FinanTechViewException;

public class EJBClientUtility {

    private static Context initialContext;

    private static FinanTechService bean = null;

    private static Logger logger = Logger
            .getLogger(EJBClientUtility.class.getName());

    private static final String FINANTECHSERVICE_BEAN = "FinanTechServiceBean";

    public static Context getInitialContext() throws NamingException {
        if (initialContext == null) {
            Properties properties = new Properties();
            initialContext = new InitialContext(properties);
        }
        return initialContext;
    }

    public static FinanTechService getFinanTechServiceBean()
            throws FinanTechViewException {
        Context context = null;
        if (bean == null) {
            try {
                context = getInitialContext();
                bean = (FinanTechService) context
                        .lookup(FINANTECHSERVICE_BEAN);
            } catch (NamingException e) {
                logger.log(Level.SEVERE,
                        FINANTECHSERVICE_BEAN + " not found", e);
                throw new FinanTechViewException("404",
                        FINANTECHSERVICE_BEAN + " not found");
            }
        }
        return bean;
    }
}