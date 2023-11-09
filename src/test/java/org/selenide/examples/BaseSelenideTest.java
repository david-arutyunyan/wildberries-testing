package org.selenide.examples;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;

public class BaseSelenideTest {
    public void configSetup() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        //Configuration.holdBrowserOpen = true;
    }

    @Before
    public void init() {
        configSetup();
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
