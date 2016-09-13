package com.finantech.util;

import java.util.Date;

public class FinanTechConstant {

    public static final String WEB_DRIVER_PATH_KEY = "webdriver.chrome.driver";

    public static final String WEB_DRIVER_PATH_VALUE = "D:\\software\\chromedriver_win32\\chromedriver.exe";

    public static String[] ALL_COLUMN_NAMES = { "p_symbol", "last_close",
            "last_volume", "avg_volume", "pct_change_1_day",
            "pct_change_1_week", "pct_change_1_month",
            "pct_change_3_months", "pct_change_1_year", "sma_5", "ema_5",
            "sma_13", "ema_13", "sma_20", "ema_20", "sma_34", "ema_34",
            "sma_50", "ema_50", "sma_89", "ema_89", "sma_200", "ema_200",
            "bband_upper", "bband_lower", "bband_mid", "macd",
            "macd_signal", "macd_hist", "adx", "dmi_plus", "dmi_minus",
            "rsi", "stoch_k", "stoch_d", "cci", "psar", "true_range", "atr",
            "williams_r", "trix", "stochrsi_k", "stochrsi_d", "momentum",
            "candle", "p_date" };

    public static Class[] ALL_COLUMN_TYPES = { String.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, Double.class, Double.class,
            Double.class, Double.class, String.class, Date.class };

    public static String[] REQD_HIS_COLUMN_NAMES = { "p_date",
            "last_close", };

    public static final String SYMBOL_INDICATORS_CSV_FILE_NAME = "symbol_indicators.csv";

    public static final String SYMBOL_INDICATORS_CSV_DOWNLOAD_PATH = "D:\\userdata\\npanda\\Downloads\\";

    public static final String ARCH_FILE_FOLDER__PATH = "D:\\Personal\\study\\finantech\\root\\resources\\";

    public static final String ICHART_URL = "http://www.icharts.in/screener-eod.html";

}
