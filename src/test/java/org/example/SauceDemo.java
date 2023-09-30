package org.example;

//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceDemo {

    private WebDriver driver;
    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }
    @Test
    public void LoginValidCredentials(){

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.navigate().refresh();

        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");

        //locate login button
        WebElement looginButton = driver.findElement(By.id("login-button"));
        //click on login button
        looginButton.click();

        //locate app logo element
        WebElement appLogo = driver.findElement(By.className("app_logo"));
        //assert appLogo is displayed

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(appLogo.isDisplayed());
    }

    @Test
    public void LoginInvalidCredentials(){
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.navigate().refresh();

        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce123");

        //locate login button
        WebElement looginButton = driver.findElement(By.id("login-button"));
        //click on login button
        looginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container.error > h3"));
        Assertions.assertTrue(errorMessage.isDisplayed());
        System.out.println(errorMessage.isDisplayed());

        String errorMessageText = "Username and password do not match";
        Assertions.assertTrue(errorMessage.getText().contains(errorMessageText));
        System.out.println(errorMessage.getText().contains(errorMessageText));
    }
}
