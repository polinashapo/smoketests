package page;

import com.codeborne.selenide.Selenide;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static page.ComponentPage.upload;

public class UploadPhotoPage {

    public class UploadPhotosPage {

        static File girl = new File("src/test/resources/photos/girl.jpeg");
        static File venera = new File("src/test/resources/photos/Venera.jpeg");
        static File autoportret = new File("src/test/resources/photos/autoportret.jpeg");
        public static void uploadPhotoToAlbum() {

            $("input#upload_files_button").uploadFile(girl, venera, autoportret);
//        $("input#upload_files_button").uploadFile(venera);
//        $("input#upload_files_button").uploadFile(autoportret);
            upload();
            Selenide.refresh();
        }

        public static void uploadPhotoInExpress() {

            $("input.porch-upload-input").uploadFile(venera);
            upload();
            $("li.sly-tabs-list-item", 9).click();
            $("div.porch-album-tile-cover").click();
            $(byText("Заполнить все")).click();
            upload();
            $("i.material-icons", 10).click();

        }

        public static void checkboxPhotoInAlbum() {
            $("div.photo_item_element").click();

        }



    }

}
