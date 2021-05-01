package com.automation.flexmoney;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class EMIPage {
	
	
	public void set(String label, String value) throws InterruptedException {
		Thread.sleep(2000);
		WebElement ele;
		if(label.contains("3 Months")) {
			simulateEvent(By.xpath("//div[contains(@class,'emiDetail')][@id='emi-panel-0']//input"), value);
			return;
		}
		if(label.contains("6 Months")) {
				simulateEvent(By.xpath("//div[contains(@class,'emiDetail')][@id='emi-panel-1']//input"), value);
				return;
		}
		
		if(label.contains("9 Months")) {
			simulateEvent(By.xpath("//div[contains(@class,'emiDetail')][@id='emi-panel-2']//input"), value);
			return;
		}
		if(label.contains("12 Months")) {
			simulateEvent(By.xpath("//div[contains(@class,'emiDetail')][@id='emi-panel-3']//input"), value);
			return;
		}
		throw new RuntimeException("Unable to locate "+label+" for "+value);
	}
	
	public String get(String label) {
		
		if(label.contains("Bank Name")) {
			WebElement get=AutomationSetup.getValidatedElementNoWait(By.xpath("//div[contains(@class,'lenderHeaderName')]"));
			if(get != null)
			{
				return AutomationSetup.get(get);
			}
		}
		if(label.contains("Mobile")) {
			WebElement get=AutomationSetup.getValidatedElementNoWait(By.xpath("//div[contains(@class,'mobileNumber')]"));
			if(get != null)
			{
				return AutomationSetup.get(get);
			}
		}
		if(label.contains("Amount")) {
			WebElement get=AutomationSetup.getValidatedElementNoWait(By.xpath("//div[contains(@class,'purchaseAmount')]"));
			if(get != null)
			{
				return AutomationSetup.get(get);
			}
		}
		
		throw new RuntimeException("Unable to get value for "+label);
		
	}
	
	  public void simulateEvent(By by, String event)
	    {
	         simulateEvent(AutomationSetup.driver.findElement(by), event);
	         return;
	    }
	
	 public void  simulateEvent(WebElement element, String event)
	    {
	        
	        executeJS("var evt = '" + event + "';if (document.createEvent) { var evObj = document.createEvent('MouseEvents'); evObj.initEvent(evt,true,false,window, 0, 0, 0, 0, 0, false, false, false, false, 0, null); arguments[0].dispatchEvent(evObj);} else if (document.createEventObject) { var evObj = document.createEventObject(); arguments[0].fireEvent('on' + evt, evObj);} else throw new Error('Failed to simulate event')", element);
	        return;
	    }
	 
	 public  String executeJS(String script, WebElement element)
	    {
	        return (String) ((JavascriptExecutor) AutomationSetup.driver).executeScript("try{" + script + "}catch(e){return e+'';}", element);
	    }
	public void click(String click) {
		
		if(click.contains("Confirm")) {
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
