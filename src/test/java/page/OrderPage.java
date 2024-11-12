package page;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class OrderPage {

    public static void chooseLoginIcon() {
        $("span#login_name").hover();
        Selenide.refresh();
    }

    public static void chooseMyFinancesMenu() {
        $(byText("Мои финансы")).click();
    }

    public static void chooseMyAlbumsMenu() {
        $(byText("Мои альбомы")).click();
    }

    public static void fillFinanceAccount(String sum) {
        Selenide.refresh();
        $("div.payment-item").click();
        $("input.form-control").setValue(sum);
        $("div.ph-btn").click();
    }

    public static void createAlbum() {
        $("span.top_block_ico", 9).click();
        $("a.user-menu-item-block", 1).click();
        $(byText("Создать альбом")).click();
        $("input#album_name").setValue("Картины");
        $("div.ph-popup-btn--js").click();
    }
}
