package common.selenium;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.util.*;

public class PageReader {

    public static void main(String[] args) throws Exception {
        PageObject userNamePO = new PageObject("loginPage","username") ;
        System.out.println(getLocators(userNamePO));
    }



    public static List<Locator> getLocators(PageObject pageObject) throws Exception {
        String fileName = pageObject.getPageName();
        String path = Objects.requireNonNull(PageReader.class.getResource("/")).getFile();
        fileName = "pages/"+fileName+".yml";
        File  file= new File(path+"/"+fileName);
        if(!file.exists()){
            throw new Exception(String.format("页面文件【%s】不存在",fileName));
        }
        InputStream inputStream = PageReader.class.getClassLoader().getResourceAsStream(fileName);
        LinkedHashMap<String, List<HashMap<String, String>>> allLocators = new Yaml().load(inputStream);
        List<HashMap<String, String>> objectLocators = allLocators.get(pageObject.getObjectName());
        if (objectLocators==null || objectLocators.isEmpty()){
            throw new Exception(String.format(("页面文件【%s.yml】中没有找到【%s】的定位locator，请添加！！"),
                    pageObject.getPageName(),pageObject.getObjectName()));
        }else{
            List<Locator> locators = new ArrayList<>();
            for(HashMap<String, String> map : objectLocators){
                for (String key : map.keySet()) {
                    locators.add(new Locator(key,map.get(key)));
                }
            }
            return locators;
        }
    }


}
