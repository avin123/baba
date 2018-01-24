package com.avin.scripts;

import org.testng.annotations.Test;

import com.avin.base.BaseTest;
import com.avin.base.HomePage;
import com.avin.pages.AddEmpPage;



public class CreateEmp extends BaseTest{
	@Test
	public void CreateEmployee()
	{
		HomePage hp=new HomePage(driver);
		String URL = driver.getCurrentUrl();
		hp.verifyUrl(URL);
		hp.clickPim();
		String exp=driver.getCurrentUrl();
		hp.verifyUrl(exp);
		hp.clickAddEmp();
		String Expected=driver.getCurrentUrl();
		AddEmpPage aep=new AddEmpPage(driver);
		aep.verifyUrl(Expected);
		aep.clickSaveBtn("Arpan", "Tiwari");
		
	}

}
