package com.automation.flexmoney;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class AutomationSetup {

	static WebDriver driver;
	
	public static void loadApp() {
		System.setProperty("webdriver.chrome.driver", "J:\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://staging.instacred.me/simulation/tpsl/e2e-transaction.jsp");
		System.out.println("App Loaded Successfully");
	}
	
	public static void quit() {
		 driver.quit();
		 System.out.println("Quitting Driver");
		 return;
	}
	
	public static void click(WebElement element)
	{
		 element.click();
		 return;
	}
	
	public static void set(WebElement element, String value) {
		if (element != null) {
			String tagName = element.getTagName();

			switch (tagName) {
			case "input": {
				String typeAttribute = element.getAttribute("type").toLowerCase();

				switch (typeAttribute) {
				/**** Handle set on html input tag type radio ****/
				case "radio":
					if (!element.isSelected()) {
						click(element);
					}
					break;

				case "checkbox": {
					switch (value.trim().toLowerCase()) {
					case "check":
					case "true":
						if (!element.isSelected()) {
							click(element);
						}
						break;
					case "uncheck":
					case "false":
					case "":
						if (element.isSelected()) {
							click(element);
						}
						break;
					default:
						throw new RuntimeException("Checkbox set for value =" + value + "= is not handled");
					}

					break;
				}

				case "button":
					throw new RuntimeException("Can't set on a " + typeAttribute);

					/**** Handle set on html input tag type text ****/
				default:
					element.clear();
					element.sendKeys(value);

					break;
				}
				break;
			}

			case "textarea":
				element.clear();
				element.sendKeys(value);
				break;

			case "select":
				selectOptionUsingVisibleText(element, value);
				break;

			default:
				throw new RuntimeException("set function for " + element + " with tag " + tagName + " not handled");
			}
		} else
			throw new RuntimeException("can't set on WebElement ==  null ");
	}
	
	static void selectOptionUsingVisibleText(WebElement field, String visibleText)
    {
        Select select;
        try
        {
            select = new Select(field);
            if (select.isMultiple())
            {
                String[] visibleTextSplit = visibleText.split(",");
                for (int i = 0; i < visibleTextSplit.length; i++)
                {
                    select.selectByVisibleText(visibleTextSplit[i]);
                }
            }
            else
            {
                select.selectByVisibleText(visibleText);
            }
        }
        catch (Exception exception)
        {

            List<String> options = null;
            try
            {
                options = getAllOptionsInSelect(field);
            }
            catch (Exception e)
            {
                options = null;
            }
            if (options != null)
            {
                RuntimeException runtimeException = new RuntimeException("Valid options in select are" + options + " tried to set[" + visibleText + "]", exception);
                throw runtimeException;
            }

            throw exception;
        }
    }
	
	 public static LinkedList<String> getAllOptionsInSelect(WebElement field)
	    {
	        LinkedList<String> response = new LinkedList<String>();
	        Select select;
	        try
	        {
	            select = new Select(field);
	            List<WebElement> options = select.getOptions();
	            for (Iterator<WebElement> iterator = options.iterator(); iterator.hasNext();)
	            {
	                WebElement option = iterator.next();
	                response.add(get(option));
	            }
	        }
	        catch (Exception exception)
	        {
	            exception.printStackTrace();
	            throw exception;
	        }
	        finally
	        {
	        }
	        return response;

	    }
	 public static String get(WebElement element)
	    {
	        String textToReturn = null;
	        if (element != null)
	        {
	            String tagName = element.getTagName();

	            switch (tagName)
	            {

	                case "input":
	                {
	                    String typeAttribute = element.getAttribute("type");

	                    switch (typeAttribute)
	                    {

	                        case "radio":
	                            if (element.isSelected())
	                            {
	                                textToReturn = "true";
	                            }
	                            else
	                            {
	                                textToReturn = "false";
	                            }

	                            break;

	                        case "checkbox":
	                            if (element.isSelected())
	                            {
	                                textToReturn = "true";
	                            }
	                            else
	                            {
	                                textToReturn = "false";
	                            }

	                            break;

	                        case "button":
	                            throw new RuntimeException("Can't get on a " + typeAttribute);

	                            /**** Handle get on html input tag type text ****/
	                        default:
	                            textToReturn = element.getAttribute("value");
	                            break;
	                    }
	                    break;
	                }

	                case "textarea":
	                    textToReturn = element.getAttribute("value");
	                    break;

	                case "select":
	                    textToReturn = getSelectedOption(element);
	                    break;
	                case "img":
	                    textToReturn = element.getAttribute("title") + "@" + element.getAttribute("src");
	                    break;

	                default:
	                    textToReturn = element.getText();
	                    break;
	            }
	        }
	        else throw new RuntimeException("can't get on WebElement ==  null ");
	        return textToReturn.replaceAll("\\s{2,}", " ").trim();

	    }
	 
	 public static WebElement getValidatedElementNoWait(By by)
		{
			try
			{
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				WebElement result = getValidatedElement(by);
				return result;
			}
			finally
			{
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
		}
	 
	 public static WebElement getValidatedElement(By by)
		{
			try
			{
				WebElement temp = driver.findElement(by);
				if (temp.isDisplayed()) return temp;
				else return null;
			}
			catch (NoSuchElementException e)
			{
				return null;
			}
			catch (StaleElementReferenceException e)
			{
				return null;
			}
		}
	 
	 private static String getSelectedOption(WebElement element)
	    {
	        try
	        {
	            Select select = new Select(element);
	            String reply = "";
	            if (select.isMultiple())
	            {
	                List<WebElement> selectedOptions = select.getAllSelectedOptions();
	                for (WebElement option : selectedOptions)
	                {
	                    reply += option.getText() + ", ";
	                }
	                return reply.substring(0, reply.lastIndexOf(","));
	            }
	            else return select.getFirstSelectedOption().getText();
	        }
	        catch (Exception exception)
	        {
	            throw new RuntimeException("Error in geting Selected in " + element + " ", exception);
	        }
	    }
	
}
