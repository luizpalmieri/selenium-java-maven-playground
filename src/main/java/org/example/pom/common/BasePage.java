package org.example.pom.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void type(WebElement element, String text) {
        wait.until(visibilityOf(element));
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("O parâmetro element não pode ser nulo.");
        }
        try {
            wait.until(visibilityOf(element));
            return element.getText();
        } catch (TimeoutException e) {
            throw new RuntimeException("Não foi possível obter o texto do elemento: " + element.toString(), e);
        } catch (NullPointerException e) {
            throw new RuntimeException("O elemento não possui texto: " + element.toString(), e);
        }
    }
}
