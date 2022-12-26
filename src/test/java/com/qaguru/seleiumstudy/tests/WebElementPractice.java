package com.qaguru.seleiumstudy.tests;
//import org.openqa.selenium.Alert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebElementPractice {


    private final String baseUrl = "http://qaguru.ca/webelementapp.php";
    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // this will wait for the webelement for 10sec before performing any


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void selectGender() {
        WebElement radMale = driver.findElement(By.xpath("//*[@id=\"home-5\"]/form[2]/input[1]"));
        Assert.assertTrue(radMale.isSelected(), "Male radio button is not selected by default");

        WebElement redGender = driver.findElement(By.xpath("//*[@id=\"home-5\"]/form[2]/input[2]"));
        redGender.click();

    }

    @Test
    public void setTextBox() {
        WebElement webElement = driver.findElement(By.name("firstname"));
        webElement.clear();
        webElement.sendKeys("John");
    }

    @Test
    public void listBox() {
        WebElement webElement = driver.findElement(By.name("cars"));
        Select select = new Select(webElement);
        select.selectByVisibleText("Audi");
        List<WebElement> webElements = select.getOptions();
        for (WebElement we : webElements) {
            System.out.println(we.getText());
        }

    }


    @Test
    public void checkBox() throws InterruptedException {

        Thread.sleep(1000);
        WebElement webElement = driver.findElement(By.name("vehicle1"));
//        webElement.click();
        System.out.println(webElement.isSelected());
        webElement.click();
    }

    @Test
    public void calender() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("bday")).sendKeys("2020"+ Keys.TAB+ "1018" );
    }
    @Test
    public void buttonAndAlert() throws InterruptedException {
        Thread.sleep(1000); // this will wait for 1sec
        driver.findElement(By.xpath("//*[@id=\"home-5\"]/button")).click();

//       how to switch to an alert
        Alert alert = driver.switchTo().alert();

//How to get the text associated with an alert,and how to validate the message
        Assert.assertEquals(alert.getText(),"QA GURU!", "Incorrect message");

//        how to click a button which is present on the alert
        alert.accept();
    }

 //Implicit wait is global where as explicit is only for a
    // webelement based on a particular condition.
// so after setting the implicit wait, for every operation this implicit wait will be applicable.

    //    Synchronization technique - Implicit
    @Test
    public void  ImplicitSync() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // this will wait for the webelement for 10sec before performing any

        //  operation on this web element.
        driver.findElement(By.name("bday")).sendKeys("2020"+ Keys.TAB+ "1018" );

    }

    //    Synchronization technique - Explicit
    @Test
    public void  ExplicitSync() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10); //this will
        // not wait for the
        //the whole 10secs but as soon as it finds  the webelement, it will perform the operation
        //on the webelement.explicit wait is applicable only for this webelement.
       WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("bday")));
  webElement.sendKeys("2020"+ Keys.TAB+ "1018" );

    }


}