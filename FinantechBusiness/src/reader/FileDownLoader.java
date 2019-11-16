package reader;

import static util.FinanTechConstant.WEB_DRIVER_PATH_VALUE;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import util.FinanTechConstant;

public abstract class FileDownLoader {

    private static Logger logger = Logger
            .getLogger(FileDownLoader.class.getName());

    WebDriver driver = null;

    WebDriver getWebDriver() {
        logger.log(Level.FINE, "Web Driver Requested.");
        System.setProperty(FinanTechConstant.WEB_DRIVER_PATH_KEY,
                WEB_DRIVER_PATH_VALUE);
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    WebElement getWebElementById(WebDriver driver, String elementId) {
        logger.log(Level.FINE, "Get Web Element By Id::" + elementId);
        return driver.findElement(By.id(elementId));
    }

    Map<String, String> getOptionsValueMap(WebElement webElementById) {
        logger.log(Level.FINE,
                "Get Options Value Map Element By Id::" + webElementById);
        Map<String, String> inputValueMap = new HashMap<String, String>();
        Select carrierList = new Select(webElementById);
        List<WebElement> options = carrierList.getOptions();
        for (WebElement option : options) {
            inputValueMap.put(option.getAttribute("value"),
                    option.getText());
        }
        return inputValueMap;
    }

    public abstract boolean download();

}
