package com.contactmgr.misc;

public class Util {
	
	public static boolean isNumeric(String strNum) {
	    try {
	        int n = Integer.parseInt(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}

}
