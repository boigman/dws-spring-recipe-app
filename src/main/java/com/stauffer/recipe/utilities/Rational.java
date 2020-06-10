package com.stauffer.recipe.utilities;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class Rational {
	
	    private int whole, num, denom;
	    
	    private static String[] fraclist = new String[] {"&frac12;","&frac13;","&frac23;","&frac14;","&frac34;","&frac15;"
	    ,"&frac25;","&frac35;","&frac45;","&frac16;","&frac56;","&frac17;","&frac18;","&frac38;","&frac58;","&frac78;"
}; 

	    public Rational(BigDecimal d) {
//	    	System.out.println("Rational of "+d.toString());
	    	this.whole = d.intValue();
	    	BigDecimal fracPart = d.remainder(BigDecimal.ONE).round(new MathContext(4));
	    	for(int ii=2;ii<10;ii++) {
	    		Float tolerance = (float) .002;
	    		BigDecimal testnum = fracPart.multiply(BigDecimal.valueOf(ii)); 
	    		BigDecimal testfrac = testnum.remainder(BigDecimal.ONE).round(new MathContext(4));
	    		if(testfrac.floatValue() <= tolerance || testfrac.floatValue() >= 1.0 - tolerance) {
	    	        this.num = testnum.round(new MathContext(1, RoundingMode.HALF_EVEN)).intValue();
	    	        this.denom = ii;
	    	        return;
	    		}
//	    		System.out.println(testnum);
	    	}
	        String s = String.valueOf(fracPart);
	        int digitsDec = s.length() - 1 - s.indexOf('.');
	        int denom = 1;
	        for (int i = 0; i < digitsDec; i++) {
	        	fracPart = fracPart.multiply(new BigDecimal(10));    
	            denom *= 10;
	        }

	        int num = fracPart.intValue();
	        int g = gcd(num, denom);
	        this.num = num / g;
	        this.denom = denom /g;
	    }

	    public Rational(int num, int denom) {
	        this.num = num;
	        this.denom = denom;
	    }

	    public String toString() {
	    	String testString = "&frac"+String.valueOf(num)+String.valueOf(denom)+";";
	    	String wholeString = whole>0?String.valueOf(whole)+" ":"";
	    	if(num==0) {
	    		return String.valueOf(whole);
	    	} else if(Arrays.asList(fraclist).contains(testString)) {
	    		return wholeString+"&frac"+String.valueOf(num)+String.valueOf(denom)+";".replace("null", "");
	    	} else {
	    		return wholeString+String.valueOf(num) + "/" + String.valueOf(denom).replace("null", "");
	    	}
	    }

	    public static int gcd(int num, int denom) {
	        int num1 = num, num2 = denom;

	        while (num1 != 0 && num1 != num2) {
	        	if(num1 > num2)
	                num1 = num1 - num2;
	            else
	                num2 = num2 - num1;
	        }

//	        System.out.printf("GCD of given numbers is: %d", num2);
	        return num2;
	    }

//	    public static void main(String[] args) {
//	    	BigDecimal testnum = new BigDecimal("4.28571");
//	        System.out.println("Rational of "+testnum+" is: "+new Rational(testnum).toString());
//	    }
	}
