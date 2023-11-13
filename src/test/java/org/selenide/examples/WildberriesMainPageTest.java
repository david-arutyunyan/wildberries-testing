package org.selenide.examples;

import com.codeborne.selenide.WebElementsCondition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.selenide.examples.pages.WildberriesMainPage;
import org.selenide.examples.pages.WildberriesProductPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;

public class WildberriesMainPageTest extends BaseSelenideTest {
    private final static String BASE_URL = "https://www.wildberries.ru";
    private final static String ACTUAL_HEADER_ADDRESS_BUTTON_URL = "https://www.wildberries.ru/services/besplatnaya-dostavka?desktop=1#terms-delivery";
    private final static String ACTUAL_HEADER_AUTH_BUTTON_URL = "https://www.wildberries.ru/security/login?returnUrl=https%3A%2F%2Fwww.wildberries.ru%2F";
    private final static String ACTUAL_HEADER_CART_BUTTON_URL = "https://www.wildberries.ru/lk/basket";
    private final static String DIGITS_FOR_SEARCH_LINE = "1234567890";
    private final static String UPPERCASE_LETTERS_FOR_SEARCH_LINE = "WATER";
    private final static String LOWERCASE_LETTERS_FOR_SEARCH_LINE = "food";

    WildberriesMainPage mainPage;
    WildberriesProductPage productPage;

    @Before
    public void openMainPage() {
        open(BASE_URL);
        mainPage = new WildberriesMainPage();
    }

    private void createProductPage() {
        productPage = new WildberriesProductPage();
    }

    @Test
    public void checkHeaderAddressButton() {
        Assert.assertEquals(mainPage.getAddressButtonHref(), ACTUAL_HEADER_ADDRESS_BUTTON_URL);

        mainPage.clickOnAddressButton();

        webdriver().shouldHave(url(ACTUAL_HEADER_ADDRESS_BUTTON_URL));
    }

    @Test
    public void checkHeaderAuthButton() {
        Assert.assertEquals(mainPage.getAuthButtonHref(), ACTUAL_HEADER_AUTH_BUTTON_URL);

        mainPage.clickOnAuthButton();

        webdriver().shouldHave(url(ACTUAL_HEADER_AUTH_BUTTON_URL));
    }

    @Test
    public void checkHeaderCartButton() {
        Assert.assertEquals(mainPage.getCartButtonHref(), ACTUAL_HEADER_CART_BUTTON_URL);

        mainPage.clickOnCartButton();

        webdriver().shouldHave(url(ACTUAL_HEADER_CART_BUTTON_URL));
    }

    @Test
    public void checkDigitsAllowedForSearchLine() {
        mainPage.searchInSearchLine(DIGITS_FOR_SEARCH_LINE);

        Assert.assertEquals(mainPage.getSearchLineText(), DIGITS_FOR_SEARCH_LINE);
    }

    @Test
    public void checkLowercaseLettersAllowedForSearchLine() {
        mainPage.searchInSearchLine(LOWERCASE_LETTERS_FOR_SEARCH_LINE);

        Assert.assertEquals(mainPage.getSearchLineText(), LOWERCASE_LETTERS_FOR_SEARCH_LINE);
    }

    @Test
    public void checkUppercaseLettersAllowedForSearchLine() {
        mainPage.searchInSearchLine(UPPERCASE_LETTERS_FOR_SEARCH_LINE);

        Assert.assertEquals(mainPage.getSearchLineText(), UPPERCASE_LETTERS_FOR_SEARCH_LINE);
    }

    @Test
    public void checkCorrectFollowOnProductCardLink() {
        mainPage.clickOnProductCard();
        createProductPage();

        webdriver().shouldHave(url("https://www.wildberries.ru/catalog/" + mainPage.getProductCardId() + "/detail.aspx"));
        Assert.assertEquals(productPage.getProductName(), mainPage.getFirstProductName());
    }

    @Test
    public void checkProductPopupButton() {
        Assert.assertFalse(mainPage.getOpenProductPopupButtonVisibility());

        mainPage.hoverProductCard();

        Assert.assertTrue(mainPage.getOpenProductPopupButtonVisibility());

        mainPage.getProductPopup().shouldNotBe(visible);

        mainPage.clickOnOpenProductPopupButton();

        mainPage.getProductPopup().shouldBe(visible);
    }

    @Test
    public void checkCategoriesButton() {
        mainPage.getSideCategoriesMenu().shouldNotBe(visible);

        mainPage.clickOnSideCategoriesMenuButton();

        mainPage.getSideCategoriesMenu().shouldBe(visible);

        mainPage.getCategories().shouldHave(sizeGreaterThanOrEqual(26));
    }

}
