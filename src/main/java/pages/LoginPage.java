package pages;

import common.selenium.PageObject;
import common.selenium.UIBasePage;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage extends UIBasePage {

    private String loginPage = "loginPage" ;

    PageObject userNamePO = new PageObject(loginPage,"username") ;
    PageObject passwordPO = new PageObject(loginPage,"password") ;
    PageObject loginBtnPO = new PageObject(loginPage,"loginBtn") ;

    public LoginPage(RemoteWebDriver driver) {
        super(driver);
    }


    public LoginPage login(String userName,String passWord) throws Exception {
        inputText(userNamePO,userName);
        inputText(passwordPO,passWord);
        click(loginBtnPO);
        return this;
    }
}
