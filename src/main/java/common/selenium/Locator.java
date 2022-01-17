package common.selenium;

public class Locator {

    private String type;

    private String expression;

    public Locator(String type, String expression) {
        this.type = type;
        this.expression = expression;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Locator{" +
                "type='" + type + '\'' +
                ", expression='" + expression + '\'' +
                '}';
    }
}
