package org.selenide.examples;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.selenide.examples.pages.WildberriesCartPage;
import org.selenide.examples.pages.WildberriesMainPage;
import org.selenide.examples.pages.WildberriesProductPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

public class WildberriesCartPageTest extends BaseSelenideTest {

    protected final static String BASE_URL = "https://www.wildberries.ru/lk/basket";
    private final static String EXPECTED_TO_MAIN_PAGE_BUTTON_HREF = "https://www.wildberries.ru/";
    private static final String SOME_PRODUCT_URL = "https://www.wildberries.ru/catalog/19252625/detail.aspx";
    private final String EXPECTED_CART_IS_EMPTY_TEXT = "В корзине пока пусто";

    WildberriesCartPage cartPage;
    WildberriesMainPage mainPage;
    WildberriesProductPage productPage;

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    @BeforeEach
    public void openMainPage() {
        cartPage = new WildberriesCartPage();
    }

    private void createMainPage() {
        mainPage = new WildberriesMainPage();
    }

    private void createProductPage() {
        productPage = new WildberriesProductPage();
    }

    private void addProductToCart() throws InterruptedException {
        open(SOME_PRODUCT_URL);

        createProductPage();

        Thread.sleep(1000);
        Selenide.actions().sendKeys(Keys.SPACE).perform();
        Selenide.actions().sendKeys(Keys.SPACE).perform();
        Selenide.actions().sendKeys(Keys.SPACE).perform();

        productPage.clickAddToCartButton();
    }

    private String getWebdriverUrl() {
        return webdriver().driver().getCurrentFrameUrl();
    }

    @Test
    public void checkEmptyCart() {
        Assertions.assertEquals(EXPECTED_CART_IS_EMPTY_TEXT, cartPage.getCartIsEmptyHeaderText());

        Assertions.assertEquals(EXPECTED_TO_MAIN_PAGE_BUTTON_HREF, cartPage.getToMainPageButtonHref());

        cartPage.clickOnToMainPageButton();

        Assertions.assertEquals(EXPECTED_TO_MAIN_PAGE_BUTTON_HREF, getWebdriverUrl());
    }

    @Test
    public void checkAreMainButtonsExist() throws InterruptedException {
        addProductToCart();

        Assertions.assertTrue(cartPage.isOrderButtonExist());

        Assertions.assertTrue(cartPage.isDeleteProductButtonExist());

        Assertions.assertTrue(cartPage.isAddToPostponeButtonExist());
    }

    @Test
    public void checkPlusAndMinusButtons() throws InterruptedException {
        addProductToCart();

        Assertions.assertEquals("1", cartPage.getCartSectionHeaderDataCount());

        cartPage.clickOnCountPlusButton();

        Assertions.assertEquals("2", cartPage.getCartSectionHeaderDataCount());

        cartPage.clickOnCountMinusButton();

        Assertions.assertEquals("1", cartPage.getCartSectionHeaderDataCount());
    }

    @Test
    public void checkProductHrefs() throws InterruptedException {
        addProductToCart();

        Assertions.assertEquals(SOME_PRODUCT_URL, cartPage.getProductImageHref());
        Assertions.assertEquals(SOME_PRODUCT_URL, cartPage.getProductNameHref());
    }
}
