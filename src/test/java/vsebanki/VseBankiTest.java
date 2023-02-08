package vsebanki;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import vsebanki.datatest.Data;
import vsebanki.datatest.PackageData;
import vsebanki.pages.BaseMenu;
import vsebanki.pages.BasePage;

public class VseBankiTest extends BaseTests{

    static Data[] dataPack() {
        PackageData packageData = new PackageData();
        return new Data[]{
               packageData.getPack2(),packageData.getPack1(),
        };
    }

    @ParameterizedTest
    @MethodSource("dataPack")
    @DisplayName("Проверка сортировки по запросам")
    public void testScenario(Data data){

        pageManager.getPage(BaseMenu.class)
                .selectBaseMenuByName(data.getBaseMenuName())
                .selectSubMenuByName(data.getSubMenuName())
                .clickOnFilterButton()
                .inputAmountSum(data.getDepositAmount())
                .checkInputSum(data.getDepositAmount())
                .selectTimeOfContribution(data.getTime())
                .checkTimeOfContribution(data.getTime())
                .selectTypeOfContribution(data.getDepositType())
                .checkTypeOfContribution(data.getDepositType())
                .selectBanks(data.getBanksList())
                .checkBanks(data.getBanksList())
                .selectCheckBoxes(data.getListParameters())
                .checkedSelectCheckBoxes(data.getListParameters())
                .checkCountDepositsInButton(data.getCheckCountDeposit())
                .showFilterResult()
                .getListOfDeposit()
                .checkOffer(data.getCheckBunk(), data.getCheckBetBank(), data.getCheckPeriodOfDeposit(), data.getCheckIncomeBank());

//        pageManager.getPage(BaseMenu.class)
//                .selectBaseMenuByName("Вклады")
//                .selectSubMenuByName("Вклады")
//                .clickOnFilterButton()
//                .inputAmountSum("1 000 000")
//                .checkInputSum("1 000 000")
//                .selectTimeOfContribution("6 месяцев")
//                .checkTimeOfContribution("6 месяцев")
//                .selectTypeOfContribution("Обычные вклады")
//                .checkTypeOfContribution("Обычные вклады")
//                .selectBanks("Открытие")
//                .checkSelectBank("Открытие")
//                .selectBanks("Тинькофф")
//                .checkSelectBank("Тинькофф")
//                .selectBanks("ВТБ")
//                .checkSelectBank("ВТБ")
//                .selectBanks("Газпромбанк")
//                .checkSelectBank("Газпромбанк")
//                .selectBanks("Сбербанк")
//                .checkSelectBank("Сбербанк")
//                .selectCheckBox("Со снятием")
//                .selectCheckBox("С пополнением")
//                .selectCheckBox("С капитализацией")
//                .checkSelectedCheckBox("Со снятием")
//                .checkSelectedCheckBox("С пополнением")
//                .checkSelectedCheckBox("С капитализацией")
//                .checkCountDepositsInButton("13")
//                .showFilterResult()
//                .getListOfDeposit()
//                .checkOffer("Тинькофф Банк", "5,63", "182", "27740");





    }


}
