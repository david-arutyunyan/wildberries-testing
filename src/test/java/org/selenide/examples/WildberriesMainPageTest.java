package org.selenide.examples;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.selenide.examples.pages.WildberriesMainPage;
import org.selenide.examples.pages.WildberriesProductPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WildberriesMainPageTest extends BaseSelenideTest {
    protected final static String BASE_URL = "https://www.wildberries.ru";
    private final static String EXPECTED_HEADER_ADDRESS_BUTTON_HREF = "https://www.wildberries.ru/services/besplatnaya-dostavka?desktop=1#terms-delivery";
    private final static String EXPECTED_HEADER_AUTH_BUTTON_HREF = "https://www.wildberries.ru/security/login?returnUrl=https%3A%2F%2Fwww.wildberries.ru%2F";
    private final static String EXPECTED_HEADER_CART_BUTTON_HREF = "https://www.wildberries.ru/lk/basket";
    private final static String DIGITS_FOR_SEARCH_LINE = "1234567890";
    private final static String UPPERCASE_LETTERS_FOR_SEARCH_LINE = "WATER";
    private final static String LOWERCASE_LETTERS_FOR_SEARCH_LINE = "food";

    WildberriesMainPage mainPage;
    WildberriesProductPage productPage;

    @Before
    public void openMainPage() {
        mainPage = new WildberriesMainPage();
    }

    private void createProductPage() {
        productPage = new WildberriesProductPage();
    }

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    private String getWebdriverUrl() {
        return webdriver().driver().getCurrentFrameUrl();
    }

    @Test
    public void checkHeaderAddressButton() {
        Assert.assertEquals(EXPECTED_HEADER_ADDRESS_BUTTON_HREF, mainPage.getAddressButtonHref());

        mainPage.clickOnAddressButton();

        Assert.assertEquals(EXPECTED_HEADER_ADDRESS_BUTTON_HREF, getWebdriverUrl());
    }

    @Test
    public void checkHeaderAuthButton() {
        Assert.assertEquals(EXPECTED_HEADER_AUTH_BUTTON_HREF, mainPage.getAuthButtonHref());

        mainPage.clickOnAuthButton();

        Assert.assertEquals(EXPECTED_HEADER_AUTH_BUTTON_HREF, getWebdriverUrl());
    }

    @Test
    public void checkHeaderCartButton() {
        Assert.assertEquals(EXPECTED_HEADER_CART_BUTTON_HREF, mainPage.getCartButtonHref());

        mainPage.clickOnCartButton();

        Assert.assertEquals(EXPECTED_HEADER_CART_BUTTON_HREF, getWebdriverUrl());
    }

    @Test
    public void checkDigitsAllowedForSearchLine() {
        mainPage.searchInSearchLine(DIGITS_FOR_SEARCH_LINE);

        Assert.assertEquals(DIGITS_FOR_SEARCH_LINE, mainPage.getSearchLineText());
    }

    @Test
    public void checkLowercaseLettersAllowedForSearchLine() {
        mainPage.searchInSearchLine(LOWERCASE_LETTERS_FOR_SEARCH_LINE);

        Assert.assertEquals(LOWERCASE_LETTERS_FOR_SEARCH_LINE, mainPage.getSearchLineText());
    }

    @Test
    public void checkUppercaseLettersAllowedForSearchLine() {
        mainPage.searchInSearchLine(UPPERCASE_LETTERS_FOR_SEARCH_LINE);

        Assert.assertEquals(UPPERCASE_LETTERS_FOR_SEARCH_LINE, mainPage.getSearchLineText());
    }

    @Test
    public void checkCorrectFollowOnProductCardLink() {
        mainPage.clickOnProductCard();
        createProductPage();

        Assert.assertEquals("https://www.wildberries.ru/catalog/" + mainPage.getProductCardId() + "/detail.aspx", getWebdriverUrl());

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
