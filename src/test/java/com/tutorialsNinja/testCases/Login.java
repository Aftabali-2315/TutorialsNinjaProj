package com.tutorialsNinja.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsNinja.base.Base;
import com.tutorialsNinja.pageObjects.AccountPage;
import com.tutorialsNinja.pageObjects.HomePage;
import com.tutorialsNinja.pageObjects.LoginPage;
import com.tutorialsNinja.utils.Utilities;


public class Login extends Base{

	@Test(priority = 1)
	public void loginWithValidCredentials() {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.navigateToLoginPage();
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(prop.getProperty("validEmail"));
		lp.enterPassword(prop.getProperty("validPassword"));
		lp.clickLoginButton();
		AccountPage ap = new AccountPage(driver);
		Assert.assertTrue(ap.displayStatusOfEditAccountInformation(), "Edit your account information is not displayed");
	}
	
	@Test(priority = 2, dataProvider = "loginTest")
	public void loginWithMultipleCredentials(String email, String password) {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.navigateToLoginPage();
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(password);
		lp.clickLoginButton();
		AccountPage ap = new AccountPage(driver);
		Assert.assertTrue(ap.displayStatusOfEditAccountInformation(), "Edit your account information is not displayed");

	}
	
	@DataProvider(name="loginTest")
	public Object[][] supplyDataFromExcel() {
		Object[][] data = Utilities.getExcelData("Login");
		return data;
	}
	
}
