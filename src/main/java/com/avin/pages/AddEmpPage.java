package com.avin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avin.base.HomePage;

public class AddEmpPage extends HomePage
{
	public AddEmpPage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(id="firstName")
	private WebElement fN;
	@FindBy(id="lastName")
	private WebElement lN;
	@FindBy(id="btnSave")
	private WebElement saveBtn;
public void clickSaveBtn(String fn,String ln)
{
	fN.sendKeys(fn);
	lN.sendKeys(ln);
	saveBtn.click();
}

}
