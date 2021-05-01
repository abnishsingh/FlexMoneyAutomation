package com.automation.flexmoney;


public class Assertion {

	
	public boolean assertStringEquals(String actual, String expected, String msg)
    {

            if (actual.equals(expected)) return true;
            else throw new AssertionError(msg + " \nString found \n[" + actual + "] \n doesnot contain Expected \n[" + expected + "];");

    }

}
