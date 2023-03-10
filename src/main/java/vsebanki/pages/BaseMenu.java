package vsebanki.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class BaseMenu extends BasePage {

    @FindBy(xpath = "//div[@data-test='subsections-item']")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//div[contains(@class, 'submenu-item')]")
    private List<WebElement> listSubMenu;

    @Step("Выбрать из базового меняю вкладку {name}")
    public BaseMenu selectBaseMenuByName(String name) {
        for(WebElement baseMenu : listBaseMenu){
            if(baseMenu.getText().equalsIgnoreCase(name))
                baseMenu.click();
            return this;
        }
//        listBaseMenu.stream()
//                .filter(s -> s.getText().equalsIgnoreCase(name))
//                .forEach(WebElement::click);
        return this;
    }

    @Step("Выбрать из второстепенного меню вкладку {name}")
    public SearchPage selectSubMenuByName (String name) {
        for(WebElement subMenu : listSubMenu){
            if(subMenu.getText().equalsIgnoreCase(name))
                subMenu.click();
            return pageManager.getPage(SearchPage.class);
        }
//        listSubMenu.stream()
//                .filter(s -> s.getText().equalsIgnoreCase(name))
//                .forEach(WebElement::click);
        return pageManager.getPage(SearchPage.class);
    }


}
