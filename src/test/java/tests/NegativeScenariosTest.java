package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static tests.BaseTestClass.CardTypes.CREDIT;
import static tests.BaseTestClass.CardTypes.DEBIT;

@DisplayName("Негативные тесты")
class NegativeScenariosTest extends BaseTestClass {

    @Test
    @Description("Негативный сценарий оплаты дебетовой картой со статусом DECLINED")
    void debitCardDeclinedTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(BAD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .applyButtonWheelIsVisible()
                .errorMessageIsVisible();
        assertStatusFromDB(DEBIT, DECLINED_STATUS);
    }

    @Test
    @Description("Негативный сценарий оплаты кредитной картой со статусом DECLINED")
    void creditCardDeclinedTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(BAD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .applyButtonWheelIsVisible()
                .errorMessageIsVisible();
        assertStatusFromDB(CREDIT, DECLINED_STATUS);
    }

    @Test
    @Description("Указан невалидный номер дебетовой карты")
    void invalidSpecDebitCardTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(SPECIAL_CHARACTERS_CARD_NUMBER)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Указан невалидный номер кредитной карты")
    void invalidSpecCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(SPECIAL_CHARACTERS_CARD_NUMBER)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Дебетовая карта с истекшим сроком действия")
    void expiredDebitCardTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(EARLIER_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Кредитная карта с истекшим сроком действия")
    void expiredCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(EARLIER_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Дебетовая карты с невалидным значением в поле месяц")
    void invalidMonthDebitCardTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth("13")
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardMonthErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Кредитная карты с невалидным значением в поле месяц")
    void invalidMonthCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth("13")
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardMonthErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Введение невалидного года дебетовой карты")
    void invalidYearDebitCardTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_FIRST_NAME)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Введение невалидного года кредитной карты")
    void invalidYearLettersCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardCVV(GOOD_CVV)
                .enterCardYear(GOOD_FIRST_NAME)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Неверное значение в поле Владелец(фамилия) (кирилица), дебетовой картой")
    void invalidLastNameDebitCardTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(CYRILLIC_LAST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Неверное значение в поле Владелец(имя), дебетовой картой")
    void invalidFirstNameDebitCardTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(CYRILLIC_FIRST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Граничное значение в поле Владелец( имя) дебетовая карта")
    void limitFirstNameDebitCardTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(LONG_FIRST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Граничное значение в поле Владелец( фамилия) дебетовая карта")
    void limitLastNameDebitCardTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(LONG_LAST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Неверное значение в поле Владелец(фамилия), кредитная карта")
    void invalidLastNameCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(CYRILLIC_LAST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Неверное значение в поле Владелец(имя), кредитная карта")
    void invalidFirstNameCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(CYRILLIC_FIRST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Граничное значение в поле Владелец( имя) кредитная карта")
    void limitFirstNameCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(LONG_FIRST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Граничное значение в поле Владелец( фамилия) кредитная карта")
    void limitLastNameCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(LONG_LAST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Некорректное значение в поле CVC/CVV (кириллица), дебетовой карты")
    void invalidCVVDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(CYRILLIC)
                .clickApplyButton()
                .cardCVVErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Некорректное значение в поле CVC/CVV (кириллица), кредитной карты")
    void invalidCVVCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(CYRILLIC)
                .clickApplyButton()
                .cardCVVErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Пустая форма заявки")
    void emptyFormTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .cardYearErrorIsVisible()
                .cardCVVErrorIsVisible()
                .cardMonthErrorIsVisible()
                .cardNumberErrorIsVisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Незаполненное поле Владелец дебетовой карты")
    void emptyCardholderDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Незаполненное поле Номер карты дебетовой карты")
    void emptyCardNumberDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Незаполненное поле год дебетовой карты")
    void emptyYearDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Незаполненное поле Месяц дебетовой карты")
    void emptyMonthDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardMonthErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Незаполненное поле Владелец кредитной карты")
    void emptyCardholderCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Незаполненное поле Номер карты кредитной карты")
    void emptyCardNumberCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Незаполненное поле CVC кредитной карты")
    void emptyCVVCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .clickApplyButton()
                .cardCVVErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Незаполненное поле Год кредитной карт - зайти на страницу покупки")
    void emptyYearCreditCardFromBuyPageTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Незаполненное поле Месяц кредитной карты")
    void emptyMonthCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardMonthErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Указан невалидный номер дебетовой карты (буквенные значения)")
    void invalidCardNumberDebitCardLettersTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(GOOD_FIRST_NAME)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Указан невалидный номер, кредитной карты (буквенные значения)")
    void invalidCardNumberCreditCardDigitsTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_FIRST_NAME)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Указан невалидный номер, кредитной карты (цифры с дефисом)")
    void invalidCardNumberCreditCardDigitsHyphenTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(HYPHEN_DIGITS)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Указан невалидный номер дебетовой карты (цифры с дефисом)")
    void invalidCardNumberDebitCardDigitsHyphenTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(HYPHEN_DIGITS)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Указан невалидный номер дебетовой карты (цифры с апострофом)")
    void invalidCardNumberDebitCardDigitsApostropheTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(APOSTROPHE_DIGITS)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Указан невалидный номер кредитной карты (цифры с апострофом)")
    void invalidCardNumberCreditCardDigitsApostropheTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(APOSTROPHE_DIGITS)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardNumberErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Введение невалидного года дебетовой карты (спецсимволы)")
    void invalidYearSpecDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(SPECIAL_CHARACTERS_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Введение невалидного года кредитной карты (спецсимволы)")
    void invalidYearSpecCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(SPECIAL_CHARACTERS_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Введение невалидного года дебетовой карты (цифры с дефисом)")
    void invalidYearDigitsHyphenDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(HYPHEN_DIGITS)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Введение невалидного года кредитной карты (цифры с дефисом)")
    void invalidYearDigitsHyphenCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(HYPHEN_DIGITS)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Введение невалидного года дебетовой карты (цифры с апострофом)")
    void invalidYearDigitsApostropheDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(APOSTROPHE_DIGITS)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Введение невалидного года кредитной карты (цифры с апострофом)")
    void invalidYearDigitsApostropheCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(APOSTROPHE_DIGITS)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardYearErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Неверное значение в поле Владелец(фамилия), дебетовой картой (спецсимволы)")
    void invalidLastNameSpecDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(SPECIAL_CHARACTERS_NAME + " " + GOOD_LAST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Неверное значение в поле Владелец(имя), дебетовой картой (спецсимволы)")
    void invalidFirstNameSpecDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_FIRST_NAME + " " + SPECIAL_CHARACTERS_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardCVVErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Неверное значение в поле Владелец(фамилия), дебетовой картой (цифры)")
    void invalidLastNameDigitsDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_FIRST_NAME + " " + GOOD_CVV)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .buttonLoaderIsInvisible()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Неверное значение в поле Владелец(имя), дебетовой картой (цифры)")
    void invalidFirstNameDigitsDebitCardFromBuyPageTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_FIRST_NAME + " " + GOOD_CVV)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Неверное значение в поле Владелец(фамилия), кредитная карта (спецсимволы)")
    void invalidFirstNameSpecCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_FIRST_NAME + " " + SPECIAL_CHARACTERS_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Неверное значение в поле Владелец(имя), кредитная карта (спецсимволы)")
    void invalidFirstNameSpecCreditCardFromCreditBuyPageTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(SPECIAL_CHARACTERS_NAME + " " + GOOD_LAST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Неверное значение в поле Владелец(фамилия), кредитная карта (цифры)")
    void invalidLastNameDigitsDebitCardFromCreditBuyPageTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_FIRST_NAME + " " + GOOD_CVV)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
    }

    @Test
    @Description("Неверное значение в поле Владелец(имя), кредитная карта (цифры)")
    void invalidFirstNameCreditCardFromCreditBuyPageTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_CVV + " " + GOOD_LAST_NAME)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .cardHolderErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Некорректное значение в поле CVC/CVV, дебетовой карты (спецсимволы)")
    void invalidCVVSpecDebitCardTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(SPECIAL_CHARACTERS_CVV)
                .clickApplyButton()
                .cardCVVErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Некорректное значение в поле CVC/CVV, кредитной карты (спецсимволы)")
    void invalidCVVSpecCreditCardFromCreditBuyPageTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(SPECIAL_CHARACTERS_CVV)
                .clickApplyButton()
                .cardCVVErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

    @Test
    @Description("Некорректное значение в поле CVC/CVV, дебетовой карты (цифры)")
    void invalidCVVDebitCardFromCreditBuyPageTest() {
        mainPage
                .clickBuyButton()
                .clickApplyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(BAD_CVV)
                .clickApplyButton()
                .cardCVVErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(DEBIT, false);
    }

    @Test
    @Description("Некорректное значение в поле CVC/CVV, кредитной карты (цифры)")
    void invalidCVVCreditCardFromCreditBuyPageTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(BAD_CVV)
                .clickApplyButton()
                .cardCVVErrorIsVisible()
                .buttonLoaderIsInvisible();
        assertRowsCount(CREDIT, false);
    }

}
