package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

 //   final static String HOST_URL = "http://localhost:4444/wd/hub";

    @BeforeEach
    void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://netprint.ru/";
        Configuration.pageLoadStrategy = "eager";
      //  Configuration.holdBrowserOpen = true;
        Configuration.timeout = 10000; // default 4000
        Configuration.browser = "chrome";
        open("https://netprint.ru/");
        Selenide.refresh();
    }


    @AfterEach
    @Step("Закрываем браузер")
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Step("Авторизация по токену")
    static void authorizationByToken() {
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("Token", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJwcm9qZWN0X2d1aWQiOiI1ZWJlOWFkNDlkMjM0ODY1NTk3ZDRhMTkyOGVhNTliZSIsInVzZXJfZ3VpZCI6IjM1OTYyMWZhMTY2MjQ5ZDhhZWI4ODU1NjUzODRlOWE2IiwiY2xpZW50X2lkIjoiMzU5NjIxZmExNjYyNDlkOGFlYjg4NTU2NTM4NGU5YTYiLCJleHAiOjE3MjYzMDkzNzl9.h1ORownJ55PNAO0dVjKiFuSiIhjClx_16lYX9pLxCBYqusz3Na3CvYKJ-ssH9B-7fBGv7XwNGd92OMb3dvqnyaIb-rPJQUckGDonKY6LrxsnkAyW8UawUOw7pIYwNyNwajiqK6cCYbWNHjn1LFzDsCfxKeTbLytIDj9UCOp0_bS6H5hTxkv3xxSbJQpr48YGypU33n29FtvhRQYkD2wBCJmhWb8kc-b92i6ExqjOFBkhW6JZe4p6zrzau1-Y8mw7voIIfbzwJeP7TqjPRCvqwSFIMWcFr-nx0ThRjYY32bE3xqrK5tVHczwUfaoDMccpCfb6o9pnrOO1UuPE7vdDkw", ".netprint.ru", "/", null));
        Selenide.refresh();


    }

}
