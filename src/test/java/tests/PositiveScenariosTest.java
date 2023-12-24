package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Позитивные тесты")
class PositiveScenariosTest extends BaseTestClass {

    @Test
    @Description("Позитивный сценарий оплаты дебетовой картой со статусом APPROVED")
    void positiveBuyDebitCardTest() {
        mainPage
                .clickBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .applyButtonWheelIsVisible()
                .successMessageIsVisible();
        assertStatusFromDB(CardTypes.DEBIT, APPROVED_STATUS);
    }

    @Test
    @Description("Позитивный сценарий оплаты кредитной картой со статусом APPROVED")
    void positiveBuyCreditCardTest() {
        mainPage
                .clickCreditBuyButton()
                .enterCardNumber(GOOD_CARD)
                .enterCardHolder(GOOD_HOLDER)
                .enterCardMonth(GOOD_MONTH)
                .enterCardYear(GOOD_YEAR)
                .enterCardCVV(GOOD_CVV)
                .clickApplyButton()
                .applyButtonWheelIsVisible()
                .successMessageIsVisible();
        assertStatusFromDB(CardTypes.CREDIT, APPROVED_STATUS);
    }

}
