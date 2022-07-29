
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class CardDeliveryTest {
    @BeforeEach
    void open() {
        Selenide.open("http://localhost:9999/");
    }
    @Test
    void validTest() throws InterruptedException {
        $("[data-test-id='city'] input").setValue("Смоленск");
        String data = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи'].input__control  ").setValue(data);
        $("[data-test-id='name'] input").setValue("Пантелеймон Пантелеймонов-Серверный");
        $("[data-test-id='phone'] input").setValue("+72002002002");
        $("[data-test-id=agreement]").click();
        $("[class='button__text']").click();
        $("[data-test-id=notification]").shouldBe(visible,Duration.ofSeconds(15));
        $("[data-test-id=notification] [class='notification__content']").shouldHave(exactText("Встреча успешно забронирована на " + data));
    }
}
