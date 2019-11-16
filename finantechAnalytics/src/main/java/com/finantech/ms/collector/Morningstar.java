package com.finantech.ms.collector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.finantech.ms.data.MutualFundVO;

public class Morningstar extends WebSite {

    public static String collectData() throws IOException {
        Morningstar morningstar = new Morningstar();
        return morningstar.mutualFundQuickRank();

    }

    WebDriver driver = getWebDriver();

    private static final String MORNINGSTAR_URL = "http://morningstar.in/default.aspx";

    public String mutualFundQuickRank() throws IOException {
        driver.get(MORNINGSTAR_URL);
        getWebElementById(driver, "mnuTools").click();
        driver.findElement(By.linkText("Fund Quickrank")).click();

        List<String> tabs2 = new ArrayList<String>(
                driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));

        driver.switchTo().defaultContent();
        driver.switchTo().frame("ctl00_ContentPlaceHolder1_frameTools");
        getWebElementById(driver, "colStarRatingM255").click();

        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript(
                    "javascript:__doPostBack('ctl00$ContentPlaceHolder1$aFundQuickrankControl$gridResult','Sort$StarRatingM255');");
        } else {
            throw new IllegalStateException(
                    "This driver does not support JavaScript!");
        }

        WebElement gridResult = driver.findElement(By.xpath(
                "//*[@id='ctl00_ContentPlaceHolder1_aFundQuickrankControl_gridResult']/tbody"));
        List<WebElement> trs = gridResult.findElements(By.xpath("./*"));
        List<MutualFundVO> mutualFunds = getMutualFunds(trs);

        String htmlFile = getHTMLFile(mutualFunds);
        WriteToFileExample.writeToFile(htmlFile);

        return htmlFile.toString();

    }

    private List<MutualFundVO> getMutualFunds(List<WebElement> trs) {

        List<MutualFundVO> fundVOs = new ArrayList<MutualFundVO>();
        String[] attribute = new String[8];
        for (WebElement tr : trs) {
            if (tr.getAttribute("id").equals("")) {
                continue;
            }
            attribute[0] = tr.getAttribute("id");

            List<WebElement> tds = tr.findElements(By.xpath("./*"));
            int j = 1;
            for (WebElement td : tds) {
                String classname = td.getAttribute("class");
                String txt = td.getText().replace("\n", "");

                if ("msDataText gridStarRating".equals(classname)
                        && !"Not Rated".equals(txt)) {
                    List<WebElement> values = td
                            .findElements(By.xpath("./*"));
                    for (WebElement value : values) {
                        txt = value.getAttribute("src")
                                .replace("http://lt.morningstar.com/img/",
                                        "")
                                .replace("stars.gif", "");
                    }
                }
                if (tds.size() == j) {
                    break;
                }
                if (txt.equals("")) {
                    continue;
                }
                attribute[j] = txt.replace(",", "");
                j++;
            }
            MutualFundVO fundVO = new MutualFundVO(attribute);
            fundVOs.add(fundVO);
        }

        return fundVOs;
    }

    private String getCSVFile(List<MutualFundVO> fundVOs) {
        return "";
    }

    private String getHTMLFile(List<MutualFundVO> fundVOs) {
        StringBuffer tableData = new StringBuffer("<Table border=1>");
        tableData.append("<tr>" + "<td>Id</td>" + "<td>Name</td>"
                + "<td>Morningstar® Category</td>"
                + "<td>MorningstarRating™</td>" + "<td>YTDReturn%</td>"
                + "<td>TotalExpenseRatio%</td>" + "<td>LastClose</td>"
                + "</tr>");
        String maxYTDid = getTheMaxYTD(fundVOs);
        for (MutualFundVO mutualFundVO : fundVOs) {
            if (maxYTDid.equals(mutualFundVO.getId())) {
                tableData.append("<tr bgcolor='#ff0066'>");
            } else {
                tableData.append("<tr>");
            }
            tableData.append("<td>" + mutualFundVO.getId() + "</td><td>"
                    + mutualFundVO.getName() + "</td><td>"
                    + mutualFundVO.getCategory() + "</td><td>"
                    + mutualFundVO.getRating() + "</td><td>"
                    + mutualFundVO.getYtdReturn() + "</td><td>"
                    + mutualFundVO.getTotalExpenseRatio() + "</td><td>"
                    + mutualFundVO.getLastClose() + "</td></tr>");
        }

        return tableData.append("</Table>").toString();

    }

    private String getTheMaxYTD(List<MutualFundVO> fundVOs) {

        MutualFundVO max = Collections.max(fundVOs);

        return max.getId();

    }

}
