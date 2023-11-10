package org.selenide.examples;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseSelenideTest {
    public void configSetup() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;
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
