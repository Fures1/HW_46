package core.facebook;

import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class IE {
	
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		String driverPath = "./resources/webdrivers/pc/IEDriverServer.exe";

		String url = "http://facebook.com/";
		String email_address = "abc@gmail.cc";
		String password = "*******************";
		
		if (!System.getProperty("os.name").contains("Windows")) {
			throw new IllegalArgumentException("Internet Explorer is available only on Windows");
		}

		DesiredCapabilities IEDesiredCapabilities = DesiredCapabilities.internetExplorer();
		IEDesiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		IEDesiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
		IEDesiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		IEDesiredCapabilities.setJavascriptEnabled(true);
		IEDesiredCapabilities.setCapability("enablePersistentHover", false);

		System.setProperty("webdriver.ie.driver", driverPath);
		driver = new InternetExplorerDriver(IEDesiredCapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(url);

		Thread.sleep(1000); // Pause in milleseconds (1000 – 1 sec)

		String title = driver.getTitle();
		String copyright = driver.findElement(By.xpath("//*[@id=\'pageFooter\']/div[3]/div/span")).getText();

		driver.findElement(By.id("email")).sendKeys(email_address);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("u_0_2")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='u_0_a']/div[1]/div[1]/div/a/span")).click();

		Thread.sleep(1000);
		String friends = driver.findElement(By.xpath("//div[2]/ul/li[3]/a/span[1]")).getText();

		Thread.sleep(1000);
		driver.findElement(By.id("userNavigationLabel")).click();
		driver.findElement(By.xpath("//li[22]/a/span/span")).click();

		Thread.sleep(1000);
		driver.quit();

		System.out.println("Browser is: Firefox");
		System.out.println("Title of the page: " + title);
		System.out.println("Copyright: " + copyright);
		System.out.println("You have " + friends + " friends");
	}
}