package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
//import java.lang.WeakPairMap.Pair.Weak;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
//import dev.failsafe.internal.util.Assert;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
    @Test
    public void testCase01() throws InterruptedException{
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        Thread.sleep(5000);
        WebElement nameField= driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        nameField.sendKeys("Crio Learner");

        WebElement reasonField=driver.findElement(By.xpath("//textarea[@aria-label='Your answer']"));
        long epoch=System.currentTimeMillis();
        //String text="I want to be the best QA Engineer"+epoch;
        reasonField.sendKeys("I want to be the best QA Engineer"+epoch);

        WebElement radioBtn = driver.findElement(By.xpath("(//div[@class='AB7Lab Id5V1'])[1]"));
        radioBtn.click();
        Thread.sleep(2000);

        WebElement checkBox1= driver.findElement(By.xpath("//span[text()='Java']"));
        checkBox1.click();
        Thread.sleep(2000);
        WebElement checkBox2= driver.findElement(By.xpath("//span[text()='Selenium']"));
        checkBox2.click();
        Thread.sleep(2000);
        WebElement checkBox3= driver.findElement(By.xpath("//span[text()='TestNG']"));
        checkBox3.click();
        Thread.sleep(2000);
        System.out.println("All the necessary checkboxes are clicked");

        WebElement selectBtn = driver.findElement(By.xpath("//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb KKjvXb DEh1R']"));
        selectBtn.click();
        Thread.sleep(4000);
        WebElement toSelect = driver.findElement(By.xpath("(//span[text()='Mr'])[2]"));
        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",toSelect);
        toSelect.click();
        Thread.sleep(3000);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -7);
        Date date = cal.getTime();
        String dateStr = sdf.format(date);
        System.out.println(dateStr);
        WebElement dateField = driver.findElement(By.xpath("//input[@type='date']"));
        dateField.sendKeys(dateStr);
        Thread.sleep(2000);

        WebElement hh=driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[3]"));
        hh.sendKeys("07");
        Thread.sleep(2000);

        WebElement min=driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[4]"));
        min.sendKeys("30");
        Thread.sleep(2000);

        WebElement submitBtn=driver.findElement(By.xpath("//span[text()='Submit']"));
        submitBtn.click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("/formResponse"));

        WebElement successText=driver.findElement(By.xpath("//div[@class='vHW8K']"));
        String message=successText.getText();
        System.out.println(message);
       Assert.assertEquals(message, "Thanks for your response, Automation Wizard!");


    }
}