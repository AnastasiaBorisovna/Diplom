package ru.netology.pages;

import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import static com.codeborne.selenide.Condition.*;

public class MainPage {

    protected SelenideDriver driver;

    public MainPage(SelenideDriver driver) {
        this.driver = driver;
    }

    private static final By BUY_BUTTON = new By.ByXPath("//span[text()='Купить']");
    private static final By CREDIT_BUY_BUTTON = new By.ByXPath("//span[text()='Купить в кредит']");
    private static final By CARD_NUMBER_BLOCK = new By.ByXPath("//input[@placeholder='0000 0000 0000 0000']/ancestor::span[@class='input__inner']");
    private static final By CARD_NUMBER_ERROR = new ByChained(CARD_NUMBER_BLOCK, new By.ByXPath(".//span[@class='input__sub']"));
    private static final By CARD_NUMBER_INPUT = new ByChained(CARD_NUMBER_BLOCK, new By.ByXPath(".//*[@class='input__control']"));
    private static final By MONTH_BLOCK = new By.ByXPath("//input[@placeholder='08']/ancestor::span[@class='input__inner']");
    private static final By MONTH_ERROR = new ByChained(MONTH_BLOCK, new By.ByXPath(".//span[@class='input__sub']"));
    private static final By MONTH_INPUT = new ByChained(MONTH_BLOCK, new By.ByXPath(".//*[@class='input__control']"));
    private static final By YEAR_BLOCK = new By.ByXPath("//input[@placeholder='22']/ancestor::span[@class='input__inner']");
    private static final By YEAR_ERROR = new ByChained(YEAR_BLOCK, new By.ByXPath(".//span[@class='input__sub']"));
    private static final By YEAR_INPUT = new ByChained(YEAR_BLOCK, new By.ByXPath(".//*[@class='input__control']"));
    private static final By CARDHOLDER_BLOCK = new By.ByXPath("//input[not(@placeholder)]/ancestor::span[@class='input__inner']");
    private static final By CARDHOLDER_ERROR = new ByChained(CARDHOLDER_BLOCK, new By.ByXPath(".//span[@class='input__sub']"));
    private static final By CARDHOLDER_INPUT = new ByChained(CARDHOLDER_BLOCK, new By.ByXPath(".//*[@class='input__control']"));
    private static final By CVV_BLOCK = new By.ByXPath("//input[@placeholder='999']/ancestor::span[@class='input__inner']");
    private static final By CVV_ERROR = new ByChained(CVV_BLOCK, new By.ByXPath(".//span[@class='input__sub']"));
    private static final By CVV_INPUT = new ByChained(CVV_BLOCK, new By.ByXPath(".//*[@class='input__control']"));
    private static final By APPLY_BUTTON_BLOCK = new By.ByXPath("//div[contains(@class, 'form')][last()]");
    private static final By APPLY_BUTTON = new ByChained(APPLY_BUTTON_BLOCK, new By.ByXPath(".//button"));
    private static final By APPLY_BUTTON_WHEEL = new ByChained(APPLY_BUTTON_BLOCK, new By.ByXPath(".//span[contains(@class, 'spin')]"));
    private static final By ERROR_MESSAGE = new By.ByXPath("//div[contains(@class, 'error')]");
    private static final By SUCCESS_MESSAGE = new By.ByXPath("//div[contains(@class, 'ok')]");


    @Step("Нажать на кнопку 'Купить'")
    public MainPage clickBuyButton() {
        this.driver.$(BUY_BUTTON)
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Нажать на кнопку 'Купить в кредит'")
    public MainPage clickCreditBuyButton() {
        this.driver.$(CREDIT_BUY_BUTTON)
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Ввести номер карты '{cardNumber}'")
    public MainPage enterCardNumber(String cardNumber) {
        this.driver.$(CARD_NUMBER_INPUT)
                .shouldBe(visible)
                .append(cardNumber);
        return this;
    }

    @Step("Ввести месяц '{cardMonth}'")
    public MainPage enterCardMonth(String cardMonth) {
        this.driver.$(MONTH_INPUT)
                .shouldBe(visible)
                .append(cardMonth);
        return this;
    }

    @Step("Ввести год '{cardYear}'")
    public MainPage enterCardYear(String cardYear) {
        this.driver.$(YEAR_INPUT)
                .shouldBe(visible)
                .append(cardYear);
        return this;
    }

    @Step("Ввести имя '{cardHolder}'")
    public MainPage enterCardHolder(String cardHolder) {
        this.driver.$(CARDHOLDER_INPUT)
                .shouldBe(visible)
                .append(cardHolder);
        return this;
    }

    @Step("Ввести CVV '{cardCVV}'")
    public MainPage enterCardCVV(String cardCVV) {
        this.driver.$(CVV_INPUT)
                .shouldBe(visible)
                .type(cardCVV);
        return this;
    }

    @Step("Проверка видимости ошибки номера карты")
    public MainPage cardNumberErrorIsVisible() {
        this.driver.$(CARD_NUMBER_ERROR)
                .shouldBe(visible);
        return this;
    }

    @Step("Проверка видимости ошибки месяца")
    public MainPage cardMonthErrorIsVisible() {
        this.driver.$(MONTH_ERROR)
                .shouldBe(visible);
        return this;
    }

    @Step("Проверка видимости ошибки года")
    public MainPage cardYearErrorIsVisible() {
        this.driver.$(YEAR_ERROR)
                .shouldBe(visible);
        return this;
    }

    @Step("Проверка видимости ошибки имени")
    public MainPage cardHolderErrorIsVisible() {
        this.driver.$(CARDHOLDER_ERROR)
                .shouldBe(visible);
        return this;
    }

    @Step("Проверка видимости ошибки CVV")
    public MainPage cardCVVErrorIsVisible() {
        this.driver.$(CVV_ERROR)
                .shouldBe(visible);
        return this;
    }

    @Step("Нажатие на кнопку создания заказа")
    public MainPage clickApplyButton() {
        this.driver.$(APPLY_BUTTON)
                .shouldBe(visible, enabled)
                .click();
        return this;
    }

    @Step("Проверка того, что колесо загрузки показано")
    public MainPage applyButtonWheelIsVisible() {
        this.driver.$(APPLY_BUTTON_WHEEL)
                .shouldBe(visible);
        return this;
    }

    @Step("Проверка того, что колесо загрузки скрыто")
    public MainPage buttonLoaderIsInvisible() {
        this.driver.$(APPLY_BUTTON_WHEEL)
                .shouldBe(hidden);
        return this;
    }

    @Step("Проверка видимости сообщения об ошибке заказа")
    public MainPage errorMessageIsVisible() {
        this.driver.$(APPLY_BUTTON_WHEEL)
                .shouldBe(disappear);
        buttonLoaderIsInvisible();
        this.driver.$(ERROR_MESSAGE)
                .shouldBe(visible);
        return this;
    }

    @Step("Проверка видимости сообщения об успешности заказа")
    public MainPage successMessageIsVisible() {
        this.driver.$(APPLY_BUTTON_WHEEL)
                .shouldBe(disappear);
        this.driver.$(SUCCESS_MESSAGE)
                .shouldBe(visible);
        return this;
    }

}
