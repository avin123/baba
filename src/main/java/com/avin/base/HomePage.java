package com.avin.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	
	
	
@FindBy(id="welcome")
private WebElement welcome;
@FindBy(xpath="//a[.='Logout']")
private WebElement logout;
@FindBy(id="menu_pim_viewPimModule")
private WebElement pim;
@FindBy(id="menu_pim_addEmployee")
private WebElement addEmp;
public void clickPim()
{
	pim.click();
}
public void clickAddEmp()
{
	addEmp.click();
}
//public void clickLogoutBtn()
//{
//welcome.click();
////Visibility(logout);
//logout.click();
//}
public void clickLogoutBtn()
{
	clickUsingJS(welcome);
	clickUsingJS(logout);
}
	
   public HomePage(WebDriver driver)
   {
	   super(driver);
   }

}
