package org.selenide.examples.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class WildberriesProductPage {
    private final String productName =
            $x("//div[@class='product-page__header']//span").text() + " / " +
                    $x("//div[@class='product-page__header']//h1").text();

    private final SelenideElement addToCartButton = $x("//a[@class='btn-main']");
    private final String headerXPath = "//div[@class='header__bottom']//div[@id='basketContent']";

    private final SelenideElement productPrice = $x("//span[@class='price-block__price']/ins");

    public WildberriesProductPage() {
        waitForElements();
    }

    private void waitForElements() {
        $x(headerXPath + "//div[1]//a//span[@class='navbar-pc__icon navbar-pc__icon--address']")
                .shouldBe(visible);
        $x(headerXPath + "//div[2]//a//span[@class='navbar-pc__icon navbar-pc__icon--profile']")
                .shouldBe(visible);
        $x(headerXPath + "//div[3]//a//span[@class='navbar-pc__icon navbar-pc__icon--basket']")
                .shouldBe(visible);
    }

    public String getProductName() {
        return productName;
    }

    public void clickAddToCartButton() {
        addToCartButton.doubleClick();
    }
}
