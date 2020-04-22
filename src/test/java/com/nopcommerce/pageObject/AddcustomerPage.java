package com.nopcommerce.pageObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {
	WebDriver ldriver;
	WebElement listitem;

	public AddcustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		ldriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	By lnkCustomers_menu = By.xpath("//a[@href='#']//span[text()='Customers']");
	By lnkCustomers_menuitem = By.xpath("//ul[@class='treeview-menu']//span[text()='Customers']");
	By btnAddnew = By.xpath(" //a[@class='btn bg-blue']");
	By txtemail = By.id("Email");
	By txtpassword = By.id("Password");
	By txtfirstname = By.xpath("//input[@id='FirstName']");
	By txtlastname = By.xpath("//input[@id='LastName']");
	By btnMale = By.id("Gender_Male");
	By btnfemale = By.id("Gender_Female");
	By txtDob = By.xpath("//span[@class='k-icon k-i-calendar']");
	By txtcompany = By.id("Company");
	By txtcustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

	By lstitemRegistered = By.xpath("//li[text()='Registered']");
	By lstitemGuests = By.xpath("//li[text()='Guests']");
	By lstitemVendors = By.xpath("//li[text()='Vendors']");
	By lstitemAdministrators = By.xpath("//li[text()='Administrators']");
	By drpmgrOfVendor = By.id("VendorId");

	By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");

	By btnSave = By.xpath("//button[@name='save']");

	By txtmsg = By.xpath("//div[@class='alert alert-success alert-dismissable']");

	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
	}

	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem).click();
	}

	public void clickAddNew() {
		ldriver.findElement(btnAddnew).click();;
	}

	public void setEmail(String email) {
		ldriver.findElement(txtemail).sendKeys(email);
	}

	public void setPassword(String password) {
		ldriver.findElement(txtpassword).sendKeys(password);
	}

	public void setFirstName(String fname) {
		ldriver.findElement(txtfirstname).sendKeys(fname);
	}

	public void setLastName(String lname) {
		ldriver.findElement(txtlastname).sendKeys(lname);
	}

	public void setGender(String gender) {
		if(gender.equals("Male")) {
			ldriver.findElement(btnMale).click();
		}
		else if(gender.equals("Female")) {
			ldriver.findElement(btnfemale).click();
		}
		else {
			ldriver.findElement(btnMale).click();
		}
	}
	public void setManagerOfVendor(String value) {
		
		Select s=new Select(ldriver.findElement(drpmgrOfVendor));
		s.selectByVisibleText(value);
	}
	public void setCustomerRoles(String role) {
		ldriver.findElement(txtcustomerRoles ).click();
		if(role.equals("Registered")) {
			listitem=ldriver.findElement(lstitemRegistered);
		}
		else if(role.equals("Administrators")) {
			listitem=ldriver.findElement(lstitemAdministrators);
		}
		else if(role.equals("Vendors")) {
			listitem=ldriver.findElement(lstitemVendors);
		}
		else if(role.equals("Guests")) {
			ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
			listitem=ldriver.findElement(lstitemGuests);
		}
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listitem);
	}
	public void setAdmincontent(String content) {
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void setcompany(String company) {
		ldriver.findElement(txtcompany).sendKeys(company);
	}
	public void setDOB(String mthyr,String day) throws InterruptedException {
		System.out.println("inside setDOB");
		ldriver.findElement(txtDob).click();
		while (true) {
			System.out.println("inside while true");
			String date = ldriver.findElement(By.xpath("//a[@class='k-link k-nav-fast']")).getText();
			System.out.println("date:"+date);
			if (date.equals(mthyr)) {
				break;
				
			}
			else{
				ldriver.findElement(By.xpath("//span[@class='k-icon k-i-arrow-w']")).click();	
			}
		}
		System.out.println("done with while...");
		Thread.sleep(3000);
		List<WebElement> alldays=ldriver.findElements(By.xpath("//table//tbody//tr//td"));
		for(WebElement days:alldays) {
			String Actdays=days.getText();
			if(day.equals(Actdays)) {
				days.click();
				break;
			}	
		}
	}
	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}
	
	public boolean verifyConfirmationMsg() {
		String msg=ldriver.findElement(txtmsg).getText();
		if( msg.contains("The new customer has been added successfully")){
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
		
		
		
		
		
		
		
		
		
	}


