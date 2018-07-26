package test;

import java.io.File;

import com.alibaba.fastjson.JSONObject;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class Demod {

	   static Object runWithGroovyClassLoader() throws Exception {
	        // 获取当前资源路径，用于指定Groovy脚本
	        // 使用当前线程的context创建GroovyClassLoader
	        // parseClass()方法将文件解析成可以运行的class
	        Class aClass = new GroovyClassLoader().parseClass(new File("d:/MultipleTopScore.groovy"));
	        // 创建此 Class 对象所表示的类的一个新实例
	        GroovyObject groovyObject = (GroovyObject) aClass.newInstance();
	        // groovy 方法的入参，多个参数从左到右书写，无入参保持为空new Object[]{}
	        Object[] objects = new Object[]{5, "2","3"};
	        // 调用方法 testC 并获得返回值(如果后者存在)
	        return groovyObject.invokeMethod("score2", objects);
	    }

	    public static void main(String[] args) throws Exception {
	        System.out.println(runWithGroovyClassLoader()); 
	        
	        //MultipleTopScore.score2(1, "2","3");
	    }
}
