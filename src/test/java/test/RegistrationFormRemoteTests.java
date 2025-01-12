package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationFormRemoteTests {

    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
  //      Configuration.browser = "chrome";
        Configuration.timeout = 10000;
   //     Configuration.holdBrowserOpen = true;
//        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

   @AfterEach
   void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @Tag("demoqa")
    @DisplayName("Проверка заполнения всех полей формы и их отображение")
    void fillFormTest() {
        step("Открываем страницу с формой", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('.fc-cta-consent').click()");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });

        step("Заполняем все поля формы", () -> {
            $("#firstName").setValue("Olga");
            $("#lastName").setValue("Savina");
            $("#userEmail").setValue("olga@savina.com");
            $("[for='gender-radio-2']").click();
            $("#userNumber").setValue("7777777777");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption("2023");
            $(".react-datepicker__month-select").selectOption("October");
            $(".react-datepicker__day--031:not(.react-datepicker__day--outside-month)").click();
            $("#subjectsInput").setValue("Maths").pressEnter();
            $("#hobbiesWrapper [for='hobbies-checkbox-1']").click();
            $("#hobbiesWrapper [for='hobbies-checkbox-2']").click();
            $("#hobbiesWrapper [for='hobbies-checkbox-3']").click();
            $("#uploadPicture").uploadFromClasspath("Little-cat.jpg");
            $("#currentAddress").setValue("Luneburg, shtrasse 111");
            $("#react-select-3-input").setValue("NCR").pressEnter();
            $("#react-select-4-input").setValue("Noida").pressEnter();
        });

        step("Отправляем форму регистрации", () -> {

            $("#submit").pressEnter();
        });
        step("Проверка результата регистрации", () -> {

            $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Olga Savina"));
            $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("olga@savina.com"));
            $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
            $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("7777777777"));
            $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("31 October,2023"));
            $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
            $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Sports, Reading, Music"));
            $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("Little-cat.jpg"));
            $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Luneburg, shtrasse 111"));
            $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Noida"));
        });
    }
}