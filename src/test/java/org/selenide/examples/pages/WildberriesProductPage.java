package org.selenide.examples.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class WildberriesProductPage {
    private final String productName =
            $x("//div[@class='product-page__header']//span").text() + " / " +
                    $x("//div[@class='product-page__header']//h1").text();

    private final SelenideElement addToCartButton = $x("//button[@class='btn-main']");

    public WildberriesProductPage() {
        waitForElements();
    }

    private void waitForElements() {

    }

    public String getProductName() {
        return productName;
    }

    public void clickAddToCartButton() {
        addToCartButton.shouldBe(visible).click();
    }
}
