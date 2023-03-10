package vsebanki.pages;

import io.qameta.allure.Step;
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
    @FindBy(xpath = "//div[@direction='vert']//label[contains(text(),'Банки')]//../input")
    private WebElement inputBank;
    @FindBy(xpath = "//div[contains(@class, 'Modal')]//div[@data-test='dropdown']//li")
    private List<WebElement> listOfBanks;
    @FindBy(xpath = "//div[contains(@class, 'Modal')]//label[contains(@class, 'Checkbox')]")
    private List<WebElement> listCheckBox;
    @FindBy(xpath = "//div[contains(@class, 'Modal')]/button") ////div[contains(@class, 'Modal')]//div[contains(text(), 'Показать')]/../../..
    private WebElement buttonShowResult;


    @Step("Ввести желаемую сумму вклада {count}")
    public FilterForm inputAmountSum(String count){
        amountSum.sendKeys(count);
        return  this;
    }

    @Step("Проверить введенную сумму вклада с фактической")
    public FilterForm checkInputSum(String count){
        Assertions.assertEquals(amountSum.getAttribute("value"), count, "Значение поля \"сумма\" не соответствект введенному значению");
        return  this;
    }

    @Step("Выбрать срок вклада {time}")
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
    @Step("Проверить срок вклада")
    public FilterForm checkTimeOfContribution(String time) {
        WebElement timeText = timeContribution.findElement(By.xpath(".//span[contains(@class, 'DropDownText')]"));
        Assertions.assertEquals(timeText.getText(), time, "Выбранный срок вклада не соответствует заданному");
        return this;
    }
    @Step("Выбрать тип вклада \"{name}\" ")
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

    @Step("Проверка выбранного типа вклада")
    public FilterForm checkTypeOfContribution(String name) {
        WebElement typeText = typeOfContribution.findElement(By.xpath(".//span[contains(@class, 'DropDownText')]"));
        Assertions.assertEquals(typeText.getText(), name, "Выбранный тип вклада не соответствует заданному");  //Переписать проверку с возможностью проверять без учета регистра
        return this;
    }

    @Step("Проверка что заданный банк \"{name}\" выбран")
    public FilterForm checkSelectBank(String name) {
        inputBank.click();
        for (WebElement bank : listOfBanks) {
            if(bank.getText().contains(name)){
                WebElement checkBox = bank.findElement(By.xpath(".//input"));
                Assertions.assertTrue(Boolean.parseBoolean(checkBox.getAttribute("checked")), "Введенный банк не выбран");
                inputBank.click();
                return this;
            }
        }

        Assertions.fail("Банка с название " + name + " в списке не найдено");
        return this;
    }

    @Step("Выбор чекбокса по названию \"{name}\"")
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

    @Step("Выбор параметров {parameters}")
    public FilterForm selectCheckBoxes(List<String> parameters){
        for (String parameter : parameters) {
            selectCheckBox(parameter);
        }
        return this;
    }

    public FilterForm checkSelectedCheckBox(String name) {
        for (WebElement item : listCheckBox) {
            if(item.getText().contains(name)) {
                WebElement checkBox = item.findElement(By.xpath("./input[@type='checkbox']"));
                Assertions.assertEquals(Boolean.parseBoolean(checkBox.getAttribute("checked")), true, "Чекбокс с названием \"" + name + "\" не отмечен");
                return this;
            }
        }
        return this;
    }

    @Step("Выбор параметров {parameters}")
    public FilterForm checkedSelectCheckBoxes(List<String> parameters){
        for (String parameter : parameters) {
            checkSelectedCheckBox(parameter);
        }
        return this;
    }


    @Step("Проверка количества вкладов {countDeposits}")
    public FilterForm checkCountDepositsInButton(String countDeposits) {
        waitStabilityPage(5000, 200);
        Assertions.assertEquals(countDeposits, buttonShowResult.getText()
                        .replaceAll("[^0-9]", "")
                , "Количество вкладов не равно " + countDeposits);
        return this;
    }


    @Step("Нажать кнопку \"Показать\"")
    public SearchPage showFilterResult(){
        buttonShowResult.click();
        return pageManager.getPage(SearchPage.class);
    }


    @Step("Выбор банка {nameOfBank}")
    public FilterForm selectBank(String nameOfBank) {
        inputBank.click();
        inputBank.sendKeys(nameOfBank);
        for (WebElement selectParameter : listOfBanks) {
            if (waitUntilElementToBeVisible(selectParameter).getText().contains(nameOfBank)) {
                selectParameter.click();
                inputBank.click();
                return this;
            }
        }

        Assertions.fail("Не найден банк " + nameOfBank);
        return this;
    }

    @Step("Выбор банков {banks}")
    public FilterForm selectBanks(List<String> banks){
        for (String bank : banks) {
            selectBank(bank);
        }
        return this;
    }

//    private FilterForm checkSelectBank(String nameOfBank) {
//        inputBank.click();
//        for (WebElement item : listOfBanks) {
//            if (waitUntilElementToBeVisible(item).getText().contains(nameOfBank)) {
//                waitStabilityPage(5000, 200);
//                WebElement checkBox = item.findElement(By.xpath("./input[@type='checkbox']"));
//                Assertions.assertEquals(Boolean.parseBoolean(checkBox.getAttribute("checked")), true, "Чекбокс с названием \"" + nameOfBank + "\" не отмечен");
//                inputBank.click();
//                return this;
//            }
//        }
//        Assertions.fail("Не найден банк " + nameOfBank);
//        return this;
//    }

    @Step("Проверка выбора банков {banks}")
    public FilterForm checkBanks(List<String> banks){
        for (String bank : banks) {
            checkSelectBank(bank);
        }
        return this;
    }

}
