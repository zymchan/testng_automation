package common.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.List;

public class UIBasePage{

    public RemoteWebDriver driver;

    public UIBasePage(RemoteWebDriver driver) {
        this.driver = driver;
    }


    /*浏览器动作*/

    public void openUrl(String url){
        driver.get(url);
        Reporter.info("刷新页面");
    }

    public void refreshPage(){
        driver.navigate().refresh();
        Reporter.info("刷新页面");
    }

    public void naviTo(String url){
        driver.navigate().to(url);
        Reporter.info(String.format("跳转到url：%s", url));
    }

    public void naviBack(){
        driver.navigate().back();
        Reporter.info("回退一页");
    }

    public void naviForward(){
        driver.navigate().forward();
        Reporter.info("前进一页");
    }



    public void executeJs(String jsScript){
        driver.executeScript(jsScript);
        Reporter.info(String.format("执行Js script: 【%s】",jsScript));
    }

    public String getText(PageObject pageObject) throws Exception {
        WebElement element = getPageElement(pageObject);
        String text = element.getText();
        Reporter.info(String.format("获取页面【%s】的控件【%s】的文本：【%s】",
                pageObject.getPageName(), pageObject.getObjectName(),text));
        return text;

    }

    public void elementTextshouldBe(PageObject pageObject,String expValue) throws Exception {
        WebElement element = getPageElement(pageObject);
        String text = element.getText();
        String detail = String.format("期望页面【%s】的控件【%s】的文本：【%s】",
                pageObject.getPageName(), pageObject.getObjectName(), text);
        if(text.equals(expValue)){
            Reporter.info(detail);
        }else{
            Reporter.fail(detail);
        }
    }

    public void elementTextshouldContains(PageObject pageObject) throws Exception {
        WebElement element = getPageElement(pageObject);
        String text = element.getText();
        Reporter.info(String.format("获取页面【%s】的控件【%s】的文本：【%s】",
                pageObject.getPageName(), pageObject.getObjectName(),text));
    }


    public void click(PageObject pageObject,String desc) throws Exception {
        WebElement element = getPageElement(pageObject);
        desc = desc.equals("")?String.format("点击页面【%s】的控件【%s】",
                pageObject.getPageName(),pageObject.getObjectName()):desc;
        try {
            element.click();
            Reporter.info(desc);
        }catch (Exception e){
            Reporter.fail("操作失败！:"+ desc);
            throw new Exception(e);
        }
    }

    public void click(PageObject pageObject) throws Exception {
        click(pageObject,"");
    }


    public void inputText(PageObject pageObject,String inputValue,String desc) throws Exception {
        WebElement element = getPageElement(pageObject);
        desc = desc.equals("") ? String.format("输入【%s】到 页面【%s】的控件【%s】",inputValue, pageObject.getPageName(), pageObject.getObjectName()): desc;
        try {
            element.sendKeys(inputValue);
            Reporter.info(desc);
        }catch (Exception e){
            Reporter.fail("操作失败！:"+ desc);
            throw new Exception(e);
        }
    }

    public void inputText(PageObject pageObject,String inputValue) throws Exception {
        inputText(pageObject,inputValue,"");
    }


    public WebElement getPageElement(PageObject pageObject) throws Exception {
        return getPageElements(pageObject).get(0);
    }

    public List<WebElement> getPageElements(PageObject pageObject) throws Exception {
        List<Locator> locators = PageReader.getLocators(pageObject);

        for (Locator locator : locators) {
            String expression = locator.getExpression();
            String type = locator.getType().toUpperCase();
            switch (type){
                case "XPATH":
                    return driver.findElements(By.xpath(expression));
                case "ID":
                    return driver.findElements(By.id(expression));
                case "CSS":
                    return driver.findElements(By.cssSelector(expression));
                case "Name":
                    return driver.findElements(By.name(expression));
                case "TEXT":
                    return driver.findElements(By.xpath("//*[contains(text(),'"+expression+"')]"));
                default:
                    throw new Exception(String.format("未知的定位类型【%s】", type));
            }
        }
        throw new Exception(String.format("找不到匹配的元素！页面【%s】控件【%s】，定位属性【%s】",
                pageObject.getPageName(),pageObject.getObjectName(),locators));

    }


}
