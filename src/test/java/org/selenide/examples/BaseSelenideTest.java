package org.selenide.examples;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class BaseSelenideTest {
    private final static String BASE_URL = "https://www.wildberries.ru";

    public void configSetup() {
        //Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;
        Configuration.holdBrowserOpen = false;
    }

    @BeforeEach
    public void init() {
        configSetup();
        open(getBaseUrl());
    }

    protected String getBaseUrl() {
        return BASE_URL;
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
