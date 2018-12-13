
import java.util.*;
import java.text.*;

public class BoatSales {
	static String oBoatType, oAccessory, oMarkupAmt, oAccCost, oBoatCost, oPrepCost, oQty, oSubtotal, oTax, oTotal, oSaleCtr, oGtTotal;
	static int iAccType, iQty, cSaleCtr = 0;
	static double iBoatCost, iPrepCost, cMarkupPct, cMarkupAmt, cAccCost, cSubtotal, cTax, cTotal, cGtTotal = 0;
	static char iBoatType, inputAgain = 'Y';
	static Scanner myScanner;
	static NumberFormat nf;
	
	public static void main(String[] args) {
		init();
		while (inputAgain == 'Y') {
			input();
			calcs();
			output();
		}
		totals();
	}	

	public static void init() {
		myScanner = new Scanner(System.in);
		myScanner.useDelimiter(System.getProperty("line.separator"));
		nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
	}
	
	public static void input () {
		System.out.print("Enter the boat type ('B','P','S','C') ");
		try { //inputing and validating boat type
			iBoatType = myScanner.next().toUpperCase().charAt(0);
			switch (iBoatType) {
				case 'B':
					oBoatType = "Bass";
					cMarkupPct = .33;
					break;
				case 'P':
					oBoatType = "Pontoon";
					cMarkupPct = .25;
					break;
				case 'S':
					oBoatType = "Ski";
					cMarkupPct = .425;
					break;
				case 'C':
					oBoatType = "Canoe";
					cMarkupPct = .20;
					break;
				default:
					System.out.println("Error, incorrct boat type. Defaluted to 'S'");
					oBoatType = "Ski";
					cMarkupPct = .425;
			}
		}
		catch (Exception e) {
			System.out.println("Error, incorrct boat type. Defaluted to 'S'");
			oBoatType = "Ski";
			cMarkupPct = .425;
		}
		System.out.print("Enter the accessory type (1, 2, 3) ");
			try { //inputing and validating accessory type
			iAccType = Integer.parseInt(myScanner.next());
				switch (iAccType) {
				case 1:
					oAccessory ="Electronics";
					cAccCost = 5415.30;	
					break;
				case 2:
					oAccessory = "Ski Package";
					cAccCost = 3980.00;
					break;
				case 3: 
					oAccessory = "Fishing Package";
					cAccCost = 345.45;
					break;
				default: 
					System.out.println("Error, accessory type must be 1, 2, or 3. default set to 1");
					oAccessory ="Electronics";
					cAccCost = 5415.30;	
					break;
				}
			}
			
			catch (Exception e) {
				System.out.println("Error, accessory type must be 1, 2, or 3. default set to 1");
				oAccessory ="Electronics";
				cAccCost = 5415.30;		
			}
			
			System.out.print("Enter Quantity (1 - 25 ");
			try { //inputing and validating quantity
				iQty = Integer.parseInt(myScanner.next());
				if (iQty < 0 || iQty > 26) {
					System.out.println("Error, quantity must be between 1 to 25. Defaulted to 1");
					iQty = 1;
				}
			}
			catch (Exception e) {
				System.out.println("Error, quantity must be between 1 to 25. Defaulted to 1");
				iQty = 1;
			}
			System.out.print("Enter cost of boat (2,500 - 150,000) ");
			try { // inputing and validating boat cost
				iBoatCost = Double.parseDouble(myScanner.next());
				if (iBoatCost < 2500 || iBoatCost >150000) {
					System.out.println("Error, boat cost must be 2,500 - 150,000. Defaulted to 25,000");
					iBoatCost = 25000;
				}
			}
			catch (Exception e) {
				System.out.println("Error, boat cost must be 2,500 - 150,000. Defaulted to 25,000");
				iBoatCost = 25000;
			}
			System.out.print("Enter prep cost (100.00 - 9,999.99) ");
			try { //inputing and validating prep cost
				iPrepCost = Double.parseDouble(myScanner.next());
				if (iPrepCost < 100.00 || iPrepCost > 9999.99) {
					System.out.println("Error, prep cost must be 100 - 9,999.99. Defaulted to 5,000");
					iPrepCost = 5000;
				}
			}
			catch (Exception e) {
				System.out.println("Error, prep cost must be 100 - 9,999.99. Defaulted to 5,000");
				iPrepCost = 5000;
			}
	}
	
	public static void calcs() {
		//markup calcs
		cMarkupAmt = iBoatCost * cMarkupPct;
		//rounding markup amount to 2 decimal places
		cMarkupAmt *= 100;
		cMarkupAmt = Math.round(cMarkupAmt);
		cMarkupAmt /= 100;
		//subtotal calcs
		cSubtotal =(iBoatCost + cAccCost + iPrepCost + cMarkupAmt)*iQty;
		//tax calcs
		cTax = cSubtotal * 0.06;
		//rounding tax to 2 decimal places
		cTax *= 100;
		cTax = Math.round(cTax);
		cTax /= 100;
		//total calc
		cTotal = cSubtotal + cTax;
		//adding to ctr and accum
		cSaleCtr ++; //keeping track of number of sales for GT line
		cGtTotal += cTotal; 
	}
	public static void output() {
		//formatting currency fields
		oBoatCost = nf.format(iBoatCost);
		oAccCost = nf.format(cAccCost);
		oPrepCost = nf.format(iPrepCost);
		oMarkupAmt = nf.format(cMarkupAmt);
		oSubtotal = nf.format(cSubtotal);
		oTax = nf.format(cTax);
		oTotal = nf.format(cTotal);
		
		//printing outputs
		System.out.println("Boat:           " + oBoatType);
		System.out.println("Accessory:      " + oAccessory);
		System.out.println("Quantity:       " + iQty);
		System.out.println("Boat Cost:      " + oBoatCost);
		System.out.println("Accessory Cost: " + oAccCost);
		System.out.println("Prep Cost:      " + oPrepCost);
		System.out.println("Markup Amount:  " + oMarkupAmt);
		System.out.println("Subtotal:       " + oSubtotal);
		System.out.println("Tax:            " + oTax);
		System.out.println("Total:          " + oTotal);
		
		//Prompting user to input another record
		System.out.println("Would you like to process another record? Y or N");
		inputAgain = myScanner.next().toUpperCase().charAt(0);
	}
	public static void totals() {
		//formatting currency
		oGtTotal = nf.format(cGtTotal);
		//printing total amounts
		System.out.println("Number of Sales: " + cSaleCtr);
		System.out.println("Total Sales:     " + oGtTotal);
		System.out.println("Program End.");
	}
}	





