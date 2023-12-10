package com.tutorialsNinja.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccount;
	
	@FindBy(linkText="Login")
	private WebElement login;

	@FindBy(linkText="Register")
	private WebElement register;
	
	public void clickMyAccount() {
		myAccount.click();
	}
	
	public void navigateToLoginPage() {
		login.click();
	}
	
	public void navigateToRegisterPage() {
		register.click();
	}
}
