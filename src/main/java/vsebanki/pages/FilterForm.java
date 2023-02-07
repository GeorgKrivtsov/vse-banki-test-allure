package vsebanki.pages;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilterForm extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'Modal')]//input[@name='amount']")
    private WebElement amountSum;
    @FindBy(xpath = "//div[contains(@class, 'Modal')]//label[contains(text(), 'Срок')]/..")
    private WebElement timeContribution;
    @FindBy(xpath = "//div[contains(@class, 'Modal')]//label[contains(text(), 'Тип вклада')]/..")
    private WebElement typeOfContribution;
    @FindBy(xpath = "//div[contains(@class, 'Modal')]//label[@data-testid and contains(text(), 'Банки')]/..")
    private WebElement selectBank;
    @FindBy(xpath = "//div[contains(@class, 'Modal')]//div[@data-test='dropdown']//li")
    private List<WebElement> listOfBanks;
    @FindBy(xpath = "//div[contains(@class, 'Modal')]//label[contains(@class, 'Checkbox')]")
    private List<WebElement> listCheckBox;
    @FindBy(xpath = "//div[contains(@class, 'Modal')]//div[contains(text(), 'Показать')]/../../..")
    private WebElement buttonShowResult;


    public FilterForm inputAmountSum(String count){
        amountSum.sendKeys(count);
        return  this;
    }

    public FilterForm checkInputSum(String count){
        Assertions.assertEquals(amountSum.getAttribute("value"), count, "Значение поля \"сумма\" не соответствект введенному значению");
        return  this;
    }

    public FilterForm selectTimeOfContribution(String time) {
        timeContribution.click();
        List<WebElement> listOfPeriod = timeContribution.findElements(By.xpath(".//li"));
        for(WebElement period : listOfPeriod) {
            if(period.getText().equalsIgnoreCase(time)) {
                period.click();
                return this;
            }
        }
        Assertions.fail("Срок " + time + " не найден в выпадающем списке");
        return this;
    }

    public FilterForm checkTimeOfContribution(String time) {
        WebElement timeText = timeContribution.findElement(By.xpath(".//span[contains(@class, 'DropDownText')]"));
        Assertions.assertEquals(timeText.getText(), time, "Выбранный срок вклада не соответствует заданному");
        return this;
    }

    public FilterForm selectTypeOfContribution(String name) {
        typeOfContribution.click();
        List<WebElement> listOfTypes = typeOfContribution.findElements(By.xpath(".//li"));
        for(WebElement type : listOfTypes) {
            if(type.getText().equalsIgnoreCase(name)) {
                type.click();
                return this;
            }
        }
        Assertions.fail("Тип \"" + name + "\" не найден в выпадающем списке");
        return this;
    }

    public FilterForm checkTypeOfContribution(String name) {
        WebElement typeText = typeOfContribution.findElement(By.xpath(".//span[contains(@class, 'DropDownText')]"));
        Assertions.assertEquals(typeText.getText(), name, "Выбранный тип вклада не соответствует заданному");  //Переписать проверку с возможностью проверять без учета регистра
        return this;
    }

    public FilterForm selectBanks(String name) {
        selectBank.click();
        for (WebElement bank : listOfBanks) {
            if(bank.getText().contains(name)) {
                WebElement checkBoxOfBank = bank.findElement(By.xpath(".//span[contains(@data-testid, 'checkbox-icon')]"));
                checkBoxOfBank.click();
                selectBank.click();
                return this;
            }
        }
        Assertions.fail("В списке банков нет банка " + name);
        return this;
    }

    public FilterForm checkSelectBank(String name) {

        selectBank.click();
        for (WebElement bank : listOfBanks) {
            if(bank.getText().contains(name)){
                WebElement checkBox = bank.findElement(By.xpath(".//input"));
                Assertions.assertTrue(Boolean.parseBoolean(checkBox.getAttribute("checked")), "Введенный банк не выбран");
                selectBank.click();
                return this;
            }
        }

        Assertions.fail("Банка с название " + name + " в списке не найдено");
        return this;
    }

    public FilterForm selectCheckBox(String name){
        for (WebElement item : listCheckBox) {
            if(item.getText().contains(name)) {
                WebElement checkBox = item.findElement(By.xpath("./span[@data-testid]"));
                checkBox.click();
                return this;
            }
        }
        return this;
    }

    public SearchPage showFilterResult(){
        buttonShowResult.click();
        return pageManager.getPage(SearchPage.class);
    }

}
