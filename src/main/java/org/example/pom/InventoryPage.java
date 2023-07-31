package org.example.pom;

import org.example.pom.common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {
    @FindBy(className = "app_logo")
    private WebElement productLabel;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement product : productNames) {
            names.add(product.getText());
        }
        return names;
    }

    public List<String> getProductPrices() {
        List<String> prices = new ArrayList<>();
        for (WebElement price : productPrices) {
            prices.add(price.getText());
        }
        return prices;
    }

    public String getStoreLabelText() {
        return getText(productLabel);
    }

}
