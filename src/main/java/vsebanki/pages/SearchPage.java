package vsebanki.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vsebanki.product.Bank;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//button[@class = 'Button___sc-mcd2wg-2 gjpWjG']") //Переписать путь "//div[@direction = 'row' and contains(@class,'FlexboxGrid')]//button[@class = 'Button___sc-mcd2wg-2 gjpWjG']" ... //button[@class = 'Button___sc-mcd2wg-2 gjpWjG']
    private WebElement filterButton;
    @FindBy(xpath = "//div[contains(@class, 'SearchResultsItem')]") //div[@data-test='text' and contains(text(), 'вкладов подобрано')]/../..//div[contains(@class, 'SearchResultsItem')]
    private List<WebElement> listOfBanksElement;
    @FindBy(xpath = "//div[@data-test='text' and contains(text(), 'вкладов подобрано')]")
    private WebElement searchResultCount;

    @Step("Нажать на кнопку открытия формы для фильтрации")
    public FilterForm clickOnFilterButton(){
        waitUtilElementToBeClickable(filterButton).click();
        return pageManager.getPage(FilterForm.class);
    }

    @Step("Получить список объектов из предложенных депозитов")
    public SearchPage getListOfDeposit(){
        dataManager.getListOfDeposit(listOfBanksElement);
        return this;
    }


    @Step("Проверить в предложенных депозитах вклад от банка {nameOfBank} со ставкой {bet}, сроком {period} и необходимым доходом {profit}")
    public SearchPage checkOffer(String nameOfBank, String bet, String period, String profit) {
        waitStabilityPage(5000, 250);
        for(Bank deposit : dataManager.getProductList()){
            if(deposit.getName().contains(nameOfBank)){
                Assertions.assertAll("checkBank",
                        () -> Assertions.assertEquals(deposit.getBet(),bet, "Процентная ставка не совпадает"),
                        () -> Assertions.assertEquals(deposit.getPeriod(), period, "Срок вклада не совпадает"),
                        () -> Assertions.assertEquals(deposit.getProfit(), profit, "Необходимый доход не совпадает")
                );
                return this;
            }
        }
        Assertions.fail("Предложение по вкладу не найдено");
        return this;
    }

    protected void waitStabilityPage (int maxWaitMillis, int pollDelimiter) {
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            String prevState = driverManager.getDriver().getPageSource();
            wait(pollDelimiter);
            if(prevState.equals(driverManager.getDriver().getPageSource()));
        }
    }

    public void wait(int mlSec) {
        try {
            Thread.sleep(mlSec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
