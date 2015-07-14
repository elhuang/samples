package com.paypal.test;

import java.lang.reflect.Method;

import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;

@WebTest
@Test(singleThreaded = true)
public class DataProviderTest {

    @BeforeClass
    public void isTestMethod(ITestContext context) {
        ITestNGMethod[] methods = context.getAllTestMethods();
        for (ITestNGMethod method : methods) {
            System.out.println(method.getMethodName() + ": " + method.isTest());
        }
    }
    
    @DataProvider(name = "dp")
    @Test(priority = 0)
    public static Object[][] dp() {
        return new Object[][] {{ "http://www.paypal.com" }, { "http://www.google.com" }}; 
    }
    
    @Test(priority = 1)
    public void meFirst() {
        System.out.println("first");
        Grid.driver().get("http://www.facebook.com");
    }
    
    @Test(priority = 2, dataProvider = "dp")
    public void test1(String row) {
        System.out.println(row);
        Grid.driver().get(row);
    }
}

