package tests;

import common.selenium.MyBaseTest;
import common.selenium.MyListener;
import common.selenium.Reporter;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Listeners({ MyListener.class })
public class TestClassMy extends MyBaseTest {

    //-----------------------------------Tests-----------------------------------
    @Test
    public void firstTest () {
        Reporter.info("1");
        Reporter.info("2");
        Reporter.info("3");
    }

    @Test(dataProvider = "secondTest")
    public void secondTest (String username, String password) {
        System.out.println("Second Test is Started.");
        Reporter.info("Username: " + username);
        Reporter.info("Password: " + password );
        Assert.assertEquals(username,"user1");
    }

    @DataProvider(name = "secondTest")
    public static Object[][] secondTestData (Method method) {
        //Userdata is formatted as username and password
        System.out.println("+++++++++++++++++++++");
        System.out.println(method.getName());
        return new Object[][] { { "user1", "pass1" }, { "user2", "pass2" }};
    }


//    @DataProvider(name="iterator")
//    public Iterator<Object[]> getMethodData1(Method method){
//        String[]  strArray= {"1","2","3","4"};
//        List<Object[]> testCases=new ArrayList<>();
//        for(String str :strArray){
//            TestCase testcase = JSON.parseObject(str	,TestCase.class);
//            if(testcase.getName().equals(method.getName())){
//                //查询接口的信息要封装成对象
//                testCases.add(new Object[]{JSON.parseObject(str	,TestCase.class)});
//            }
//        }
//        return testCases.iterator();
//    }
}
