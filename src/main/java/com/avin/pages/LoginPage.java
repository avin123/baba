package com.avin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avin.base.BasePage;




public class LoginPage extends BasePage {
	
	@FindBy(id="txtUsername")
	private WebElement unTB;
	@FindBy(id="txtPassword")
	private WebElement unPW;
	@FindBy(id="btnLogin")
	private WebElement loginBtn;
	public LoginPage(WebDriver driver)
	{
	super(driver);
	}
//	public void setUserName(String un)
//	{
//		unTB.sendKeys(un);
//	}
//	public void setPassword(String pw)
//	{
//		unPW.sendKeys(pw);
//	}
	public void clickLoginBTN(String un,String pw)
	{
		unTB.sendKeys(un);
		unPW.sendKeys(pw);
		loginBtn.click();
	}
	
//	public LoginPage(WebDriver driver)
//	{
//	super(driver);
//	}
}
