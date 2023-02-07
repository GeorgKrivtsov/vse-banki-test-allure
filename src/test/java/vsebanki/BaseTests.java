package vsebanki;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import vsebanki.managers.DriverManager;
import vsebanki.managers.InitManager;
import vsebanki.managers.PageManager;
import vsebanki.managers.TestPropManager;
import vsebanki.product.DataManager;
import vsebanki.utils.PropsConst;

public class BaseTests {

    private DriverManager driverManager = DriverManager.getInstance();
    private TestPropManager propManager = TestPropManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected DataManager dataManager = DataManager.getDataManager();

    @BeforeAll
    public static void beforeAll(){
        InitManager.initFramework();
    }

    @BeforeEach
    public void before() {
        //Переходим на сайт
        driverManager.getDriver().get(propManager.getProperty(PropsConst.BASE_URL));
    }


    @AfterEach
    public void after() {
        InitManager.quitFramework();
        dataManager.removeListOfDeposit();
        pageManager.clearPages();

    }


}
