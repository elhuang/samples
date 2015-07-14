package com.paypal.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.paypal.selion.annotations.WebTest;
import com.paypal.selion.platform.grid.Grid;

@WebTest
@Test(singleThreaded = true)
public class DataProviderTest {

    @DataProvider(name = "dp")
    public static Object[][] dp() {
        return new Object[][] {{ "row1" }, { "row2" }}; 
    }
    
    @Test(priority = 0)
    public void meFirst() {
        System.out.println("first");
        Grid.driver().get("http://www.paypal.com");
    }
    
    @Test(priority = 1, dependsOnMethods = "meFirst", dataProvider = "dp")
    public void test1(String row) {
        System.out.println(row);
        Grid.driver().get("http://www.paypal.com");
    }
}

