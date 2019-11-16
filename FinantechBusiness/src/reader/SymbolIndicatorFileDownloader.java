package reader;

import static util.FinanTechConstant.ICHART_URL;
import static util.FinanTechConstant.SYMBOL_INDICATORS_CSV_DOWNLOAD_PATH;
import static util.FinanTechConstant.SYMBOL_INDICATORS_CSV_FILE_NAME;
import static util.FinanTechUtil.getArchivedFilePath;
import static util.FinanTechUtil.isFileExist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SymbolIndicatorFileDownloader extends FileDownLoader {

    private static Logger logger = Logger
            .getLogger(SymbolIndicatorFileDownloader.class.getName());

    private static final String ACTIVE_MENU_SCREENER_EOD = "active_menu";

    private static final String XPATH_EXPORT_BUTTON = "//*[@id=\"searchButtonsLayer\"]/div/button[1]"; 

    private static String FILE_DOWNLOAD_PATH = SYMBOL_INDICATORS_CSV_DOWNLOAD_PATH
            + SYMBOL_INDICATORS_CSV_FILE_NAME;

    public SymbolIndicatorFileDownloader() {
        super();
    }

    public boolean download() {
        driver = getWebDriver();
        logger.log(Level.FINE, "Data Collection Started.");
        if (!isFileExist()) {
            driver.get(ICHART_URL);
            WebElement findElement = driver
                    .findElement(By.xpath(XPATH_EXPORT_BUTTON));
            findElement.click();
            try {
                waitForFileToBDownloaded();
                driver.close();
                driver.quit();
                archive();
                logger.log(Level.FINE, "Data Collection Finished.");
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE,
                        "Thread waiting for file download has been interupted.",
                        e);
            }
            return true;
        }
        return false;
    }

    @SuppressWarnings("resource")
    private void waitForFileToBDownloaded() throws InterruptedException {
        File f = new File(SYMBOL_INDICATORS_CSV_DOWNLOAD_PATH
                + SYMBOL_INDICATORS_CSV_FILE_NAME);
        try {
            new FileReader(f);
            System.out.println("File Downloaded");
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE,
                    SYMBOL_INDICATORS_CSV_FILE_NAME
                            + " file has not been downloaded. Sleeping for 100ms",
                    e);
            Thread.sleep(100);
            waitForFileToBDownloaded();
        }
    }

    public static void archive() {
        File downloadedFile = new File(FILE_DOWNLOAD_PATH);
        File archivedFile = new File(getArchivedFilePath());
        try {
            Files.copy(downloadedFile.toPath(), archivedFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            downloadedFile.delete();
            logger.log(Level.FINE,
                    SYMBOL_INDICATORS_CSV_FILE_NAME + " archived.");
        } catch (IOException e) {
            logger.log(Level.SEVERE,
                    "IOException while copying to archive the file ", e);
        }

    }

}
