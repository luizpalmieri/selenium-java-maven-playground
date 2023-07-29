package org.example.e2e;

import org.example.pom.HomePage;
import org.example.pom.LoginPage;
import org.example.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.navigateTo("https://www.saucedemo.com/");
    }

    @Test
    public void testLogin() throws InterruptedException {
        loginPage.login("standard_user", "secret_sauce");
        HomePage homePage = new HomePage(driver);
        String storeLabelText = homePage.getStoreLabelText();
        System.out.println("Product Label Text: " + storeLabelText);
        org.testng.Assert.assertEquals(storeLabelText, "Swag Labs");

    }

    @AfterClass
    public void teardown(){
        WebDriverFactory.quitDriver();
    }
}