package org.example.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pom.InventoryPage;
import org.example.pom.LoginPage;
import org.example.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class InventoryTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        String storeLabelText = inventoryPage.getStoreLabelText();
        System.out.println("Product Label Text: " + storeLabelText);
        Assert.assertEquals(storeLabelText, "Swag Labs");

        List<String> productNames = inventoryPage.getProductNames();
        List<String> productPrices = inventoryPage.getProductPrices();

        // Verifica se a lista de produtos não está vazia
        Assert.assertFalse(productNames.isEmpty());
        Assert.assertFalse(productPrices.isEmpty());

        // Exemplo de uso dos dados obtidos (pode ser modificado conforme a necessidade)
        for (int i = 0; i < productNames.size(); i++) {
            System.out.println("Produto: " + productNames.get(i) + ", Preço: " + productPrices.get(i));
        }
    }
}
