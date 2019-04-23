package com.jsc.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    //类似Spring的初始生成对象封装进容器的功能，后续使用相同对象时，直接从容器中调用。
    //创建一个HashMap的容器用来存储生成的对象
    //String 为创建对象的名称，Object为创建的对象
    private static Map<String, Object> map = new HashMap<String, Object>();

    static {
        //解析xml的配置文件，获取对象的全路径，使用Class.forName加载对象进入内存，使用反射创建对象，存入容器中
        //1、获取xml配置文件的路径
        try {
            String path = DaoFactory.class.getClassLoader().getResource("applicationContext.xml").getPath();
            //2、使用jsoup获得该配置文件的document对象
            Document document = Jsoup.parse(new File(path), "utf-8");
            //查找所有的bean创建对象
            Elements beanEles = document.select("beans>bean");
            if (beanEles != null) {
                for (Element beanEle : beanEles) {
                    String objectName = beanEle.attr("id");
                    String objectClassName = beanEle.attr("class");
                    Class cls = Class.forName(objectClassName);
                    Object object = cls.getDeclaredConstructor().newInstance();
                    map.put(objectName, object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String name) {
        return map.get(name);
    }
}

