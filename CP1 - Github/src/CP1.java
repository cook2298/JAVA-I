/*
 * This program calculates paycheck amount
 * Jacob Cook 11/27/2018
 */
import java.text.*;
import java.util.*;
public class CP1 {
		//variable declaration
		static String iString; //input string
		static String iFirstName, iLastName; // first and last name
		static int iHours; // hours after conversion
		static double iRate; //rate after conversion
		static double cPay; //calculated pay
		static String oPay; //pay after formatting currency 
		static Scanner myScanner; //input device
		static NumberFormat nf; //used to format currency
	public static void main(String[] args) {
		 init(); //call init
		 input();//call input
		 calcs();//call calcs
		 output();//call output
		 System.out.println("Program ended");
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
		//prompt for first name
		System.out.print("Enter first name: ");
		iFirstName = myScanner.next();
		//prompt for last name
		System.out.print("Enter last Name: ");
		iLastName = myScanner.next();
		//prompt input and convert
		System.out.print("Enter hours worked: ");
		iString = myScanner.next();
		iHours = Integer.parseInt(iString);
		System.out.print("Enter payrate: ");
		iString = myScanner.next();
		iRate = Double.parseDouble(iString);
	}
	public static void calcs() {
		cPay = iHours * iRate;
	}
	public static void output() {
		System.out.println("Employee name: " +iLastName + ", " + iFirstName);
		oPay = nf.format(cPay);
		System.out.println("Paycheck amount: " + oPay);
	}
}
