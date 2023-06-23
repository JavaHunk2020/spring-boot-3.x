package com.kuebiko;

import java.util.Currency;
import java.util.Locale;  
  
public class CurrencyGetSymbolLocaleExample4 {  
   public static void main(String args[]) {  
	   
        Currency currency = Currency.getInstance("INR");  
        System.out.println(currency.getCurrencyCode());
        System.out.println(currency.getDisplayName());
        System.out.println(currency.getNumericCode());
        System.out.println(currency);
        System.out.println("IN: "+currency.getSymbol(new Locale("en","IN")));  
        
        System.out.println(")@)@)@");
        Currency currency2 = Currency.getInstance(new Locale("en","IN")); 
        System.out.println(currency2.getCurrencyCode());
        System.out.println(currency2.getDisplayName());
        System.out.println(currency2.getNumericCode());
        System.out.println(currency2.getSymbol());
        System.out.println(currency2);
        System.out.println("India Symbol = "+currency2.getSymbol(new Locale("en","IN")));  
        System.out.println("USA Symbol = "+currency2.getSymbol(new Locale("en","US")));
        //FIND FOR USA
        String usa=Currency.getInstance(new Locale("en","US")).getSymbol(new Locale("en","US"));
        System.out.println("USA = "+usa);
        
   }  
}  
