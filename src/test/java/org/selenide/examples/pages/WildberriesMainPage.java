package org.selenide.examples.pages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class WildberriesMainPage {
    private final String headerXPath = "//div[@class='header__bottom']//div[@id='basketContent']";
    private final SelenideElement addressButton = $x(headerXPath + "//div[1]//a");
    private final SelenideElement authButton = $x(headerXPath + "//div[2]//a");
    private final SelenideElement cartButton = $x(headerXPath + "//div[3]//a");
    private final SelenideElement searchInput = $(byId("searchInput"));
    private final SelenideElement productCard = $x("//article[1]");
    private final SelenideElement openProductPopupButton = $x("//article[1]/div/div[1]/button[@class='product-card__fast-view hide-mobile j-open-product-popup']");

    public WildberriesMainPage() {
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

    public boolean getOpenProductPopupButtonVisibility() {
        return openProductPopupButton.isDisplayed();
    }

    public void hoverProductCard() {
        productCard.shouldBe(visible).hover();
    }

    public String getProductCardId() {
        return productCard.getAttribute("data-nm-id");
    }

    public void clickOnProductCard() {
        productCard.shouldBe(visible).click();
    }

    public String getSearchLineText() {
        return searchInput.shouldBe(visible).getAttribute("value");
    }

    public void searchInSearchLine(String text) {
        searchInput.shouldBe(visible).click();
        searchInput.val(text).pressEnter();
    }

    public void clickOnAddressButton() {
        addressButton.click();
    }

    public void clickOnAuthButton() {
        authButton.click();
    }

    public void clickOnCartButton() {
        cartButton.click();
    }

    public String getAddressButtonHref() {
        return addressButton.getAttribute("href");
    }

    public String getAuthButtonHref() {
        return authButton.getAttribute("href");
    }

    public String getCartButtonHref() {
        return cartButton.getAttribute("href");
    }
}
