package com.automation.flexmoney;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TransactionCompletePage {
	
public Set<Entry<String, String>> getTransationDetails() {
		
		List<WebElement> elements = AutomationSetup.driver.findElements(By.xpath("//h3"));
		HashMap<String, String> map=new HashMap<>();
		for(WebElement element: elements)
		{
			
			String s[]=element.getText().split(":");
		    map.put(s[0], s[1]);
		    Iterator<String> iterator = map.keySet().iterator(); 
		    while(iterator.hasNext()){ 
		    	String f = iterator.next(); 
		    	if(f.contains("SIGN")){ iterator.remove(); } }

		}
		
		return map.entrySet();
	
}
}
