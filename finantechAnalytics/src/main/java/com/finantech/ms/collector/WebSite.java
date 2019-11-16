package com.finantech.ms.collector;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WebSite {

    WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\software\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    WebElement getWebElementById(WebDriver driver, String elementId) {
        return driver.findElement(By.id(elementId));
    }

    Map<String, String> getOptionsValueMap(WebElement webElementById) {
        Map<String, String> inputValueMap = new HashMap<String, String>();
        Select carrierList = new Select(webElementById);
        List<WebElement> options = carrierList.getOptions();
        for (WebElement option : options) {
            inputValueMap.put(option.getAttribute("value"),
                    option.getText());
        }
        return inputValueMap;
    }

    Map<String, List<Map<String, String>>> processTableData(
            Map<Long, List<String>> tableData) {
        Map<String, List<Map<String, String>>> passengersData = new HashMap<String, List<Map<String, String>>>();
        List<String> header = tableData.get(new Long(1));
        for (Entry<Long, List<String>> record : tableData.entrySet()) {
            List<String> values = record.getValue();
            String year = values.get(0);
            String month = values.get(1);
            if (NumberUtils.isNumber(year) && NumberUtils.isNumber(month)) {
                String key = year + "_" + month;
                List<Map<String, String>> list = passengersData.get(key);
                if (list == null) {
                    list = new ArrayList<Map<String, String>>();
                    passengersData.put(key, list);
                }
                passengersData.get(key).add(
                        getEachRecord(record.getKey(), header, values));
            }

        }
        return passengersData;
    }

    Map<String, String> getEachRecord(Long lineNo, List<String> header,
            List<String> values) {
        Map<String, String> record = new HashMap<String, String>();
        if (header.size() != values.size()) {
            throw new IllegalArgumentException(
                    "Header Length is not matching with the values at line no"
                            + lineNo);
        } else {
            for (int i = 0; i < values.size(); i++) {
                record.put(header.get(i), values.get(i));
            }
        }
        return record;
    }

}
