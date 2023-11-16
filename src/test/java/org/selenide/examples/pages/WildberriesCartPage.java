package org.selenide.examples.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class WildberriesCartPage {

    private final SelenideElement toMainPageButton = $x("//a[@class='basket-empty__btn btn-main']");
    private final SelenideElement cartIsEmptyHeader = $x("//h1");
    private final SelenideElement countPlusButton = $x("//button[@class='count__plus plus']");
    private final SelenideElement countMinusButton = $x("//button[@class='count__minus minus']");
    private final SelenideElement addToPostponeButton = $x("//button[@class='btn__postpone j-basket-item-postpone']");
    private final SelenideElement deleteProductButton = $x("//button[@class='btn__del j-basket-item-del']");
    private final SelenideElement orderButton = $x("//button[@class='b-btn-do-order btn-main j-btn-confirm-order']");
    private final SelenideElement cartSectionHeader = $x("//h1[@class='basket-section__header active']");
    private final String headerXPath = "//div[@class='header__bottom']//div[@id='basketContent']";

    private final SelenideElement productImage = $x("//a[@class='img-plug list-item__good-img j-product-popup']");
    private final SelenideElement productName = $x("//a[@class='good-info__title j-product-popup']");


    public WildberriesCartPage() {
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

    public String getCartIsEmptyHeaderText() {
        return cartIsEmptyHeader.text();
    }

    public String getToMainPageButtonHref() {
        return toMainPageButton.getAttribute("href");
    }

    public void clickOnToMainPageButton() {
        toMainPageButton.shouldBe(visible).click();
    }

    public boolean isOrderButtonExist() {
        return orderButton.shouldBe(visible).shouldBe(enabled).isDisplayed();
    }

    public boolean isDeleteProductButtonExist() {
        return deleteProductButton.shouldBe(visible).shouldBe(enabled).isDisplayed();
    }

    public boolean isAddToPostponeButtonExist() {
        return addToPostponeButton.shouldBe(visible).shouldBe(enabled).isDisplayed();
    }

    public String getCartSectionHeaderDataCount() {
        return cartSectionHeader.getAttribute("data-count");
    }

    public void clickOnCountPlusButton() {
        countPlusButton.shouldBe(visible).click();
    }

    public void clickOnCountMinusButton() {
        countMinusButton.shouldBe(visible).click();
    }

    public String getProductImageHref() {
        String href = productImage.shouldBe(visible).getAttribute("href");
        return href.substring(0, href.indexOf("?"));
    }

    public String getProductNameHref() {
        String href = productName.shouldBe(visible).getAttribute("href");
        return href.substring(0, href.indexOf("?"));
    }
}
