package com.avin.base;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import generics.Utility;

public class BasePage {
	public Logger log=Logger.getLogger(this.getClass());
	public WebDriver driver;
	
	public long Timeout=Long.parseLong(Utility.getValue(AutomationConstants.CONFIG_PATH,"EXPLICIT"));
 public BasePage(WebDriver driver)
 {
	 this.driver=driver;
	 PageFactory.initElements(driver,this);
 }
 public void verifyListBoxSorted(WebElement ListBox)
 {
	 ArrayList<String> allText = Utility.getAllText(ListBox);
	 boolean Sorted = Utility.isListBoxSorted(allText);
	 Assert.assertTrue(Sorted);
 }
 public void verifyUrl(String expectedUrl)
 {
	 new WebDriverWait(driver,Timeout).until(ExpectedConditions.urlContains(expectedUrl));
 }
 public void Visibility(WebElement element)
 {
	 new WebDriverWait(driver,Timeout).until(ExpectedConditions.visibilityOf(element));
 }
 public void clickUsingJS(WebElement element)
 {
	 JavascriptExecutor js=(JavascriptExecutor)driver;
	 js.executeScript("arguments[0].click()",element);
 }
}
