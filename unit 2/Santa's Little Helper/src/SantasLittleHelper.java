/*Jacob Cook
Date written: 12/04/2018 
This program uses the console to ask the user to input values
then display totals for the toys and the child's*/
import java.text.*;
import java.util.*;
public class SantasLittleHelper {
	static String iFName, iLName,iAge, iToy1, iToy2, iToyCost1, iToyCost2, oToyCost1, oToyCost2, oSubtotal, oTax, oTotal;
	static Double cToyCost1, cToyCost2, cSubtotal, cTax, cTotal, TAX = 0.07;
	static Scanner myScanner;
	static NumberFormat nf;
	
	public static void main(String[] args) {
		init();
		input();
		calcs();
		output();
		System.out.print("Thank you, Merry Christmas!");
	}
	public static void init() {
		//set scanner to console
		myScanner = new Scanner(System.in);
		//change delimiter from black space to Enter key to allow spaces in strings
		myScanner.useDelimiter(System.getProperty("line.separator"));
		//set formatter to use US currency
		nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
	}
	public static void input() {
		//prompt for name 
		System.out.print("Enter the child's first name ");
		iFName = myScanner.next();
		System.out.print("Enter the child's last name ");
		iLName = myScanner.next();
		//prompt for age
		System.out.print("Enter the child's age");
		iAge = myScanner.next();
		//prompt for name of toy 1
		System.out.print("Enter the name of the first toy ");
		iToy1 = myScanner.next();
		//prompt for price of toy 1 in try catch for validation
		try {
			System.out.print("Enter the price of the first toy");
			iToyCost1 = myScanner.next();
			cToyCost1 = Double.parseDouble(iToyCost1);
		}
		catch(Exception e) {
			System.out.println("Error, input must be numeric, toy price set to 0");
			cToyCost1 = (double) 0;
		}
		//prompt for name of toy 2
		System.out.print("Enter the name of the second toy ");
		iToy2 = myScanner.next();
		//prompt for toy 2 price in try catch for validation
		try {
			System.out.print("Enter the price of the second toy");
			iToyCost2 = myScanner.next();
			cToyCost2 = Double.parseDouble(iToyCost2);
		}
		catch(Exception e) {
			System.out.println("Error, input must be numeric, toy price set to 0");
			cToyCost2 = (double) 0;
		}
	}
	public static void calcs() {
		//calc sub total
		cSubtotal = cToyCost1 + cToyCost2;
		//calc tax
		cTax = cSubtotal * TAX;
		//add tax and sub total for total
		cTotal = cSubtotal + cTax;
	}
	public static void output() {
		//display full name
		System.out.println("Child's name: "+iFName + " " + iLName);
		System.out.println("Age: " + iAge);
		//format number values with nf
		oToyCost1 = nf.format(cToyCost1);
		oToyCost2 = nf.format(cToyCost2);
		oSubtotal = nf.format(cSubtotal);
		oTax = nf.format(cTax);
		oTotal = nf.format(cTotal);
		
		//display toy1 and price
		System.out.println(iToy1 + " price: " + oToyCost1);
		//display toy2 and price
		System.out.println(iToy2 + " price: " + oToyCost2);
		//display sub total
		System.out.println("Subtotal: " + oSubtotal );
		//display tax amount
		System.out.println("Tax: " + oTax);
		//display total amount
		System.out.println("Total: " +oTotal);
	}
}
