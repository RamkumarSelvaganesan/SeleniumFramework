package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	public static String captureScreenshot(WebDriver driver, String testName) {
	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String screenshotDir = System.getProperty("user.dir") + "/screenshots";
	    String screenshotPath = screenshotDir + "/" + testName + "_" + timestamp + ".png";
	    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    try {
	        new File(screenshotDir).mkdirs(); // ensure folder exists
	        FileUtils.copyFile(src, new File(screenshotPath));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return screenshotPath;
	}

}
