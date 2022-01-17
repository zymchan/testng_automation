package common.selenium;

public class PageObject {

    private String pageName;

    private String objectName;

    public PageObject(String pageName, String objectName) {
        this.pageName = pageName;
        this.objectName = objectName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
