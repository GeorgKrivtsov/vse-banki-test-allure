package vsebanki;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vsebanki.pages.BaseMenu;
import vsebanki.pages.BasePage;

public class VseBankiTest extends BaseTests{

    @Test
    @DisplayName("Проверка сортировки по запросам")
    public void testScenario(){

        pageManager.getPage(BaseMenu.class)
                .selectBaseMenuByName("Вклады")
                .selectSubMenuByName("Вклады")
                .clickOnFilterButton()
                .inputAmountSum("1 000 000")
                .checkInputSum("1 000 000")
                .selectTimeOfContribution("6 месяцев")
                .checkTimeOfContribution("6 месяцев")
                .selectTypeOfContribution("Обычные вклады")
                .checkTypeOfContribution("Обычные вклады")
                .selectBanks("Открытие")
                .checkSelectBank("Открытие")
                .selectBanks("Тинькофф")
                .checkSelectBank("Тинькофф")
                .selectBanks("ВТБ")
                .checkSelectBank("ВТБ")
                .selectBanks("Газпромбанк")
                .checkSelectBank("Газпромбанк")
                .selectBanks("Сбербанк")
                .checkSelectBank("Сбербанк")
                .selectCheckBox("Со снятием")
                .selectCheckBox("С пополнением")
                .selectCheckBox("С капитализацией")
                .showFilterResult()
                .getListOfDeposit()
                .checkOffer("Тинькофф Банк", "5,63", "182", "27740");





    }


}
