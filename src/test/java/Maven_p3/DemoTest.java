package Maven_p3;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class DemoTest {
	ChromeDriver driver;
	 ExtentReports report;
	@BeforeMethod
	public void begin() {

		driver=new ChromeDriver();
		driver.manage().window().maximize();
		String path = System.getProperty("user.dir")+"\\reports\\web.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("shopping website report");
		reporter.config().setDocumentTitle("OMAYO BLOG");
		report=new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("created by", "MAM");
		report.setSystemInfo("operating system", "windows");
}
	
	@Test
	public void mid_1() {
		ExtentTest etest = report.createTest("test_1");
		driver.get("https://omayo.blogspot.com/");
		etest.info("website opened");
		driver.findElement(By.linkText("Open a popup window")).click();
		Set<String> win = driver.getWindowHandles();
		Iterator<String> itr = win.iterator();
		String parent = itr.next();
		String child = itr.next();
		driver.switchTo().window(child);
		String name = driver.findElement(By.xpath("//h3[text()=\"New Window\"]")).getText();	
		etest.info("Text content identified");
        Assert.assertEquals(name, "New Window");    
//	String name = driver.findElement(By.xpath("//div[@class=\"example\"]")).getText();
		System.out.println("successful");
		etest.info("TEST RUNNED SUCCESSFULLY");
	}
	@AfterMethod
	public void end() {
		driver.quit();
	}
}