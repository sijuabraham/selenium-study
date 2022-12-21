package com.qaguru.seleiumstudy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {

    private final String baseUrl = "http://www.homeandstuff.com/";

   @Test
    public void testHomePageTitle() throws InterruptedException {
        System.out.println("Test for home page title");
        WebDriverManager.chromedriver().setup();
        WebDriver driver =new ChromeDriver();
        driver.get(baseUrl);
        String expTitle = "Furniture, Kitchen, Dining Room, Entertainment, Bedroom Sets, Outdoor, Fireplaces";
        String actTitle =driver.getTitle();
        Assert.assertEquals(actTitle,expTitle,"Incorrect title");

        Thread.sleep(1000);

        driver.quit(); // this is used to release the entire instance of the web driver
//        driver.close() is used to close the window
    }
    @Test
    public void testSearchingForProduct() throws InterruptedException {
        System.out.println("Test for home page title");
        WebDriverManager.chromedriver().setup();
        WebDriver driver =new ChromeDriver();
        driver.get(baseUrl);

        WebElement txtSearch = driver.findElement(By.name("search_field"));
        txtSearch.clear();
        txtSearch.sendKeys("table");

        WebElement btnSearch = driver.findElement(By.xpath("//*[@id=\"search\"]/form/div/span/input"));
        btnSearch.click();

        WebElement lnkResult = driver.findElement(By.linkText("Urban Small Dining Table Set 3-Piece Marble White"));
        driver.quit(); // this is used to release the entire instance of the web driver
//        driver.close() is used to close the window
    }
}
