package tests;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.TestWatcher;
import ru.netology.pages.MainPage;
import ru.netology.utils.Configuration;
import ru.netology.utils.DatabaseQueriesUtils;
import ru.netology.utils.GeneratorUtils;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTestClass implements TestWatcher {

    public SelenideDriver driver;
    protected MainPage mainPage;
    private final Configuration configuration = ConfigFactory.create(Configuration.class);

    protected final String WEBSITE_URL = configuration.websiteUrl();
    protected final String SPECIAL_CHARACTERS_YEAR = GeneratorUtils.generateSpecialCharacters(2);
    protected final String SPECIAL_CHARACTERS_CVV = GeneratorUtils.generateSpecialCharacters(3);
    protected final String SPECIAL_CHARACTERS_NAME = GeneratorUtils.generateSpecialCharacters(5);
    protected final String SPECIAL_CHARACTERS_CARD_NUMBER = GeneratorUtils.generateSpecialCharacters(16);
    protected final String GOOD_CARD = configuration.goodCard();
    protected final String BAD_CARD = configuration.badCard();
    protected final String APPROVED_STATUS = configuration.approvedStatus();
    protected final String DECLINED_STATUS = configuration.declinedStatus();
    protected final String GOOD_HOLDER = GeneratorUtils.generateGoodHolderName();
    protected final String GOOD_FIRST_NAME = GeneratorUtils.generateFirstName();
    protected final String GOOD_LAST_NAME = GeneratorUtils.generateLastName();
    protected final String GOOD_YEAR = GeneratorUtils.generateYear(3);
    protected final String EARLIER_YEAR = GeneratorUtils.generateYear(-5);
    protected final String GOOD_MONTH = GeneratorUtils.generateMonth(1);
    protected final String CYRILLIC = GeneratorUtils.generateCyrillic();
    protected final String GOOD_CVV = "123";
    protected final String BAD_CVV = "1";
    protected final String LONG_STRING = GeneratorUtils.generateString(65);
    protected final String LONG_FIRST_NAME = LONG_STRING + " " + GOOD_LAST_NAME;
    protected final String LONG_LAST_NAME = GOOD_FIRST_NAME + " " + LONG_STRING;
    protected final String CYRILLIC_FIRST_NAME = CYRILLIC + " " + GOOD_LAST_NAME;
    protected final String CYRILLIC_LAST_NAME = GOOD_FIRST_NAME + " " + CYRILLIC;
    protected final String HYPHEN_DIGITS = GOOD_CVV + "-" + GOOD_CVV;
    protected final String APOSTROPHE_DIGITS = GOOD_CVV + "'";
    protected static int debitRowsCount;
    protected static int creditRowsCount;

    @Step("Проверка статуса оплаты в БД")
    protected static void assertRowsCount(CardTypes cardType, boolean isCreated) {
        switch (cardType) {
            case DEBIT: {
                int count = DatabaseQueriesUtils.getDebitRowsCountFromDB();
                if (!isCreated) {
                    Assertions.assertEquals(debitRowsCount, count, "Была создана запись в БД");
                } else Assertions.assertTrue(count > debitRowsCount, "Запись в БД не создана");
            } break;
            case CREDIT: {
                int count = DatabaseQueriesUtils.getCreditRowsCountFromDB();
                if (!isCreated) {
                    Assertions.assertEquals(creditRowsCount, count, "Была создана запись в БД");
                } else Assertions.assertTrue(count > creditRowsCount, "Запись в БД не создана");
            } break;
            default: {
            }
        }

    }

    @Step("Проверка статуса оплаты в БД")
    protected static void assertStatusFromDB(CardTypes cardType, String expectedStatus) {
        switch (cardType) {
            case DEBIT: {
                assertRowsCount(CardTypes.DEBIT, true);
                String actualStatus = DatabaseQueriesUtils.getLastDebitStatusFromDB();
                Assertions.assertEquals(expectedStatus, actualStatus, "Статус в БД не совпадает");
            } break;
            case CREDIT: {
                assertRowsCount(CardTypes.CREDIT, true);
                String actualStatus = DatabaseQueriesUtils.getLastCreditStatusFromDB();
                Assertions.assertEquals(expectedStatus, actualStatus, "Статус в БД не совпадает");
            } break;
            default: {
            }
        }
    }

    @BeforeEach
    public void setup() {
        final SelenideConfig config = new SelenideConfig();
        config
                .baseUrl(WEBSITE_URL)
                .browser("Chrome");
        driver = new SelenideDriver(config);
        debitRowsCount = DatabaseQueriesUtils.getDebitRowsCountFromDB();
        creditRowsCount = DatabaseQueriesUtils.getCreditRowsCountFromDB();
        driver.open(WEBSITE_URL);
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void teardown() throws IOException {
        screenshot();
        closeWebDriver();
    }

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @Attachment(type = "image/png")
    public static byte[] screenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return screenshot == null ? null : Files.toByteArray(screenshot);
    }

    protected enum CardTypes {

        DEBIT,
        CREDIT;

    }

}
