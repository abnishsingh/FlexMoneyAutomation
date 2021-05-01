package com.automation.flexmoney;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TransactionFormDetails {
	
	
	public void set(String label, String value) {
		
		WebElement ele=AutomationSetup.getValidatedElementNoWait(By.xpath("//tr[(contains(@class,'normal'))]//label[contains(.,'"+label+"')]/parent::td/following-sibling::td/input"));
		if(ele != null)
		{
			AutomationSetup.set(ele, value);
			return;
		}
		ele=AutomationSetup.getValidatedElementNoWait(By.xpath("//label[contains(.,'"+label+"')]/parent::td/following-sibling::td/select"));
		if(ele != null)
		{
			AutomationSetup.set(ele, value);//input[contains(@type,'checkbox')]
			return;
		}
		if(label.equals("Mobile registered with your bank") || label.equals("Verify Account Last 4 Digit") || label.equals("OTP")) {
			ele=AutomationSetup.getValidatedElementNoWait(By.xpath("//input[contains(@type,'tel')]"));
			if(ele != null)
			{
				AutomationSetup.set(ele, value);
				return;
			}
		}
		if(label.equals("I Agree")) {
			ele=AutomationSetup.getValidatedElementNoWait(By.xpath("//input[contains(@type,'tel')]"));
			if(ele != null)
			{
				AutomationSetup.set(ele, value);
				return;
			}
		}
		throw new RuntimeException("Unable to locate "+label+" for "+value);
	}
	
	public String get(String label) {
		
		WebElement get=AutomationSetup.getValidatedElementNoWait(By.xpath("//tr[(contains(@class,'normal'))]//label[contains(.,'"+label+"')]/parent::td/following-sibling::td/input"));
		if(get != null)
		{
			return AutomationSetup.get(get);
		}
		get=AutomationSetup.getValidatedElementNoWait(By.xpath("//label[contains(.,'"+label+"')]/parent::td/following-sibling::td/select"));
		if(get != null)
		{
			return AutomationSetup.get(get);
			
		}
		throw new RuntimeException("Unable to get value for "+label);
		
	}
	
	public void click(String click) {
		
		if(click.contains("Submit") || click.contains("Continue") || click.contains("Verify Debit Card") || click.contains("Verify & Confirm")) {
			WebElement clk=AutomationSetup.getValidatedElementNoWait(By.xpath("//button"));
			if(clk != null)
			{
				 AutomationSetup.click(clk);
				 return;
			}
		}
		
		throw new RuntimeException("Unable to click "+click);
		
	}
	
	

}
