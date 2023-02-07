package vsebanki.product;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager dataManager = null;

    private DataManager() {

    }

    public static DataManager getDataManager() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    private List<Bank> listOfBank = new ArrayList<>();

    public List<Bank> getProductList() {
        return listOfBank;
    }

    public Bank getBankByName(String name){
        for(Bank bank : listOfBank)
            if(bank.getName().equalsIgnoreCase(name)){
                return bank;
            }
        Assertions.fail("Банка с названием: \"" + name + "\" нет в списке результатов");
        return null;
    }

    public void getListOfDeposit(List<WebElement> listOfBanksElement){
        for (WebElement bank : listOfBanksElement){
            WebElement nameOfBank = bank.findElement(By.xpath(".//img[@data-test='bank-logo']/.."));
            WebElement betOfBank = bank.findElement(By.xpath(".//div[contains(text()[2],'%')]"));
            WebElement periodOfBank = bank.findElement(By.xpath(".//div[contains(text()[2],'дн.')]"));
            WebElement profitForBank = bank.findElement(By.xpath(".//div[contains(text(),'₽')]"));

            getProductList().add(Bank.builder()
                    .name(nameOfBank.getText())
                    .bet(betOfBank.getText().replaceAll("[^0-9,]", ""))
                    .period(periodOfBank.getText().replaceAll("[^0-9,]", ""))
                    .profit(profitForBank.getText().replaceAll("[^0-9,]", ""))
                    .build());
        }
    }


    public void removeListOfDeposit(){
        listOfBank.clear();
    }

    public void checkBankInSearchList(String name, String bet, String period, String profit){
        Bank bank = getBankByName(name);
        Assertions.assertAll("checkBank",
                () -> Assertions.assertEquals(bank.getBet(),bet),
                () -> Assertions.assertEquals(bank.getPeriod(), period),
                () -> Assertions.assertEquals(bank.getProfit(), profit)
        );
    }

}
