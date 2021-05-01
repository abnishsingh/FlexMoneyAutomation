package com.automation.flexmoney;

public class AutomateFlexMoneyTestCases extends AutomationSetup {
	
	public static void main(String[] args) throws InterruptedException {
		
		AutomationTCFlow1();
//		AutomationTCFlow2();
		
	}
	

	public static void AutomationTCFlow1() {
		
		TransactionFormDetails formDetails=new TransactionFormDetails();
		TransactionCompletePage transCompPage=new TransactionCompletePage();
		Assertion assertion=new Assertion();
		AutomationSetup.loadApp();//loads the app
			
		formDetails.set("Gateway*", "TPSL");//we can change gateway based on dynamic fields.
		formDetails.set("Merchant MID", "0001235");
		formDetails.set("Currency", "USD");
		formDetails.set("Amount", "15000");
		formDetails.set("User Lang ID", "002");
		formDetails.set("Mobile", "52565252");
		formDetails.set("Merchant Txn ID", "MRTX_94705");
		formDetails.set("Gateway Txn ID", "GWTPS558487");
		formDetails.set("Lender (Bank code)", "HDFC");
//		formDetails.set("Cart Info", "test cart");
		formDetails.set("Return URL", "https://staging.instacred.me/internal-testing/transaction-complete-page.jsp");
		formDetails.set("Secret Key", "somesecrey key");
		
		assertion.assertStringEquals(formDetails.get("Gateway*"), "TPSL", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Merchant MID"), "0001235", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Currency"), "USD", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Amount"), "15000", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("User Lang ID"), "002", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Mobile"), "52565252", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Merchant Txn ID"), "MRTX_94705", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Gateway Txn ID"), "GWTPS558487", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Lender (Bank code)"), "HDFC", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Cart Info"), "bankCode:10790", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Return URL"), "https://staging.instacred.me/internal-testing/transaction-complete-page.jsp", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Secret Key"), "somesecrey key", "value Not macthed");
		
		formDetails.click("Continue");
		System.out.println("Transaction Info: "+transCompPage.getTransationDetails());
		
		//Validating all transaction info //removed SIGN key as it keeps changing
		assertion.assertStringEquals(transCompPage.getTransationDetails()+"", "[PID = 0001235, KRT = bankCode, PRN = GWTPS558487, AMT = 15000, STATUS = FAIL, MTXID = MRTX_94705, CI = 52565252, RU = https, CRN = USD, USER_LANG_ID = 002, GID = 1, ERROR_CODE = INV_REQ_BDY]", "transaction info Not macthed");
		
//		formDetails.set("Mobile registered with your bank","9988998899");
//		formDetails.click("Submit");
//		
		AutomationSetup.quit();
		System.out.println("Quit");
	}
	
	public static void AutomationTCFlow2() throws InterruptedException {
		
		TransactionFormDetails formDetails=new TransactionFormDetails();
		TransactionCompletePage transCompPage=new TransactionCompletePage();
		EMIPage emiPage=new EMIPage();
		Assertion assertion=new Assertion();
		
		AutomationSetup.loadApp();//loads the app
			
		formDetails.set("Gateway*", "TPSL");//we can change gateway based on dynamic fields.
		formDetails.set("Merchant MID", "0001234");
		formDetails.set("Currency", "INR");
		formDetails.set("Amount", "10000");
		formDetails.set("User Lang ID", "001");
		formDetails.set("Mobile", "9988998899");
		formDetails.set("Merchant Txn ID", "MRTX_315762");
		formDetails.set("Gateway Txn ID", "GWTPS230410");
		formDetails.set("Lender (Bank code)", "None");
		formDetails.set("Cart Info", "test cart");
		formDetails.set("Return URL", "https://staging.instacred.me/internal-testing/transaction-complete-page.jsp");
		formDetails.set("Secret Key", "somesecret");
		
		assertion.assertStringEquals(formDetails.get("Gateway*"), "TPSL", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Merchant MID"), "0001234", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Currency"), "INR", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Amount"), "10000", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("User Lang ID"), "001", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Mobile"), "9988998899", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Merchant Txn ID"), "MRTX_315762", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Gateway Txn ID"), "GWTPS230410", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Lender (Bank code)"), "None", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Cart Info"), "test cart", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Return URL"), "https://staging.instacred.me/internal-testing/transaction-complete-page.jsp", "value Not macthed");
		assertion.assertStringEquals(formDetails.get("Secret Key"), "somesecret", "value Not macthed");
		
		formDetails.click("Continue");
		
//		formDetails.set("Mobile registered with your bank", "9988998899");
//		formDetails.click("Submit");
		
		System.out.println(emiPage.get("Bank Name"));
		System.out.println(emiPage.get("Mobile"));
		System.out.println(emiPage.get("Amount"));
		
		emiPage.set("3 Months", "click");
		emiPage.click("Confirm");
		
		formDetails.set("Verify Account Last 4 Digit", "1234");
		formDetails.click("Verify Debit Card");
		formDetails.set("OTP","777777");
		formDetails.set("I Agree","Check");
		formDetails.click("Verify & Confirm");
		Thread.sleep(6000);
		
		System.out.println("Transaction Info: "+transCompPage.getTransationDetails());
		
		//Validating all transaction info //removed SIGN key as it keeps changing
		assertion.assertStringEquals(transCompPage.getTransationDetails()+"", "[PID = 0001234, KRT = bankCode, PRN = GWTPS558487, AMT = 15000, STATUS = FAIL, MTXID = MRTX_94705, CI = 52565252, RU = https, CRN = USD, USER_LANG_ID = 002, GID = 1, ERROR_CODE = INV_REQ_BDY]", "transaction info Not macthed");
		
//		
		AutomationSetup.quit();
		System.out.println("Quit");
	}


}

