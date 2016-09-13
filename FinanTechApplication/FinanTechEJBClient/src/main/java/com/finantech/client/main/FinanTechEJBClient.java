package com.finantech.client.main;

import javax.naming.Context;
import javax.naming.NamingException;

import com.finantech.client.util.EJBClientUtility;
import com.finantech.ejb.FinanTechService;

public class FinanTechEJBClient {

    public static void main(String[] args) {
        FinanTechService bean = doLookup();
        System.out.println(bean.getAllScripsAllTradedInfo());
    }

    private static FinanTechService doLookup() {
        Context context = null;
        FinanTechService bean = null;
        try {
            // 1. Obtaining Context
            context = EJBClientUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            String lookupName = getLookupName();
            // 3. Lookup and cast
            bean = (FinanTechService) context.lookup(lookupName);

        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
    }

    private static String getLookupName() {
        /*
         * The app name is the EAR name of the deployed EJB without
         * .ear suffix. Since we haven't deployed the application as a
         * .ear, the app name for us will be an empty string
         */
        String appName = "";

        /*
         * The module name is the JAR name of the deployed EJB without
         * the .jar suffix.
         */
        String moduleName = "finantechejb-1.0-SNAPSHOT";

        /*
         * AS7 allows each deployment to have an (optional) distinct
         * name. This can be an empty string if distinct name is not
         * specified.
         */
        String distinctName = "";

        // The EJB bean implementation class name
        String beanName = "FinanTechServiceBean";

        // Fully qualified remote interface name
        final String interfaceName = "com.finantech.ejb.FinanTechService";

        // Create a look up string name
        String name = "ejb:" + appName + "/" + moduleName + "/"
                + distinctName + "/" + beanName + "!" + interfaceName;
        System.out.println(name);

        return name;
    }

}
