/*
 * This program asks the user to input their desired boat type, accessory type, quantity, boat cost, 
 * and prep cost. The user inputs are validated and if the data is invalid the prompt loops until valid
 * data is inputed. Then the appropriate values are calculated and displayed in a receipt-style format.
 * the user can input as many boat sales as desired, but are prompted after each sale if they would like
 * to process another. When they are finished entering sales, the grand total of all entered sales is 
 * displayed.
 */
import java.util.*;
import java.text.*;
public class BoatSalesRevised {
	static String iBoatType, oBoatType, oAcc, oQty, oBoatCost, oAccCost, oPrepCost, oMarkupAmt, oSubtotal, oTax, oTotal, inputAgain = "Y", oGtTotal;
	static int iAcc, iQty, cSaleCtr = 0;
	static double iBoatCost, iPrepCost, cMarkupPct, cMarkupAmt, cAccCost, cSubtotal, cTax, cTotal, cGtTotal = 0;
	static boolean valid;
	static Scanner myScanner; 
	static NumberFormat nf;
	static final String DASHES = "--------------------------------";
	public static void main(String[] args) {
		init ();
		while (inputAgain.equals("Y")){
			input();
			calcs();
			output();
		}//end of inputAgain while 
		closing();		

	} // end of main
	public static void init() {
		myScanner = new Scanner(System.in);
		myScanner.useDelimiter(System.getProperty("line.separator"));
		nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
	} // end of init
	
	public static void input() {
		do {
			valid = true;
			try {
				System.out.print("Enter boat type (B, P, S, C) ");
				iBoatType = myScanner.next().toUpperCase().substring(0, 1);
				switch (iBoatType) {
					case "B":
						oBoatType = "Bass";
						cMarkupPct = .33;
						break;
					case "P":
						oBoatType = "Pontoon";
						cMarkupPct = .25;
						break;
					case "S":
						oBoatType = "Ski";
						cMarkupPct = .425;
						break;
					case "C":
						oBoatType = "Canoe";
						cMarkupPct = .2;
						break;
					default: 
						System.out.print("Error, boat type must be B, P, S, or C. Please re-enter ");
						valid = false;
				}
			}
			catch (Exception e) {
				System.out.print("Error, boat type must be B, P, S, or C. Please re-enter ");
				valid = false;
			}
			
		}while (!valid); // end of boat type while
		
		do {
			valid = true;
			try {
				System.out.print("Enter accessory type (1, 2, or 3) ");
				iAcc = Integer.parseInt(myScanner.next());
				switch (iAcc) {
					case 1:
						oAcc = "Electronics";
						cAccCost = 5415.30;
						break;
					case 2:
						oAcc = "Ski Package";
						cAccCost = 3980;
						break;
					case 3:
						oAcc = "Fishing Package";
						cAccCost = 345.45;
						break;
					default:
						System.out.println("Error, accessory type must be 1, 2 or 3. Please re-enter ");
						valid = false;
				}
			}
			catch (Exception e) {
				System.out.print("Error, accessory type must be 1, 2 or 3 and it must be numeric. Please re-enter ");
				valid = false;
			}
			
		}while (!valid); //end of acc type while
		
		do {
			valid = true;
			try {
				System.out.print("Enter desired quantity (1 - 25) ");
				iQty = Integer.parseInt(myScanner.next());
					if (iQty < 1 || iQty > 25) {
						valid = false;
						System.out.print("Error, quantity must be 1 - 25. Please re-enter ");
					}
				
			}
			catch (Exception e) {
				valid = false;
				System.out.print("Error, quantity must be numeric and between 1 - 25. Please re-enter ");
			}
			
		}while (!valid); // end of qty while
		
		do {
			valid = true;
			try {
				System.out.print("Enter boat cost (2,500.00 - 150,000.00 ");
				iBoatCost = Double.parseDouble(myScanner.next());
				if (iBoatCost < 2500 || iBoatCost >150000) {
					valid = false;
					System.out.print("Error, boat cost must be 2,500.00 - $150,000.00. Please re-enter ");
				}
			}
			catch (Exception e) {
				valid = false;
				System.out.print("Error, boat cost must be 2,500.00 - $150,000.00 and must be numeric. Please re-enter ");
			}
			
		}while (!valid); // end of boat cost while
		
		do {
			valid = true;
			try {
				System.out.print("Enter the prep cost (100.00 - 9999.99");
				iPrepCost = Double.parseDouble(myScanner.next());
				if (iPrepCost < 100 || iPrepCost > 9999.99) {
					valid = false; 
					System.out.print("Error, prep cost must be 100.00 - 9999.99. Please re-enter ");
				}
			}
			catch (Exception e) {
				valid = false;
				System.out.print("Error, prep cost must be 100 - 9999.99 and must be numeric. Please re-enter ");
			}
			
		}while (!valid); //end of prep cost while
		

	} //end of input module

	public static void calcs() {
		//calc detail fields
		cMarkupAmt = cMarkupPct * iBoatCost;
		cMarkupAmt *= 100;
		cMarkupAmt = Math.round(cMarkupAmt);
		cMarkupAmt /= 100;
		cSubtotal = (iBoatCost + cAccCost + iPrepCost + cMarkupAmt) * iQty;
		cTax = cSubtotal * 0.06;
		cTax *= 100;
		cTax = Math.round(cTax);
		cTax /= 100;
		cTotal = cSubtotal + cTax;
		// adding to gt ctrs and accums
		cGtTotal += cTotal;
		cSaleCtr ++;
	} // end of calcs
	
	public static void output() {
		//format detail fields
		oBoatCost = nf.format(iBoatCost);
		oAccCost = nf.format(cAccCost);
		oPrepCost = nf.format(iPrepCost);
		oMarkupAmt = nf.format(cMarkupAmt);
		oSubtotal = nf.format(cSubtotal);
		oTax = nf.format(cTax);
		oTotal = nf.format(cTotal);
		
		//formating receipt-style output
		System.out.printf("%32s%6s%32s\n", DASHES , "Order-", DASHES);
		System.out.printf("%-15s%40s%15s\n", "Boat Type:", "", oBoatType);
		System.out.printf("%-15s%40s%15s\n", "Accessory:" , "" , oAcc);
		System.out.printf("%-15s%40s%15s\n", "Quantity:", "" , iQty);
		System.out.printf("%-15s%40s%15s\n", "Boat Cost:", "", oBoatCost);
		System.out.printf("%-15s%40s%15s\n", "Accessory Cost:", "", oAccCost);	
		System.out.printf("%-15s%40s%15s\n", "Prep Cost:", "", oPrepCost);
		System.out.printf("%-15s%40s%15s\n", "Markup Amount:", "", oMarkupAmt);
		System.out.printf("%-15s%40s%15s\n", "Subtotal:", "", oSubtotal);
		System.out.printf("%-15s%40s%15s\n", "Tax:", "",oTax);
		System.out.printf("%-15s%40s%15s\n", "Total:", "", oTotal);
		System.out.printf("%32s%6s%32s\n", DASHES , "------", DASHES);
		//asking user if they want to loop again
		System.out.print("Would you like to enter another boat sale? Y or N ");
		inputAgain = myScanner.next().toUpperCase().substring(0, 1);
		
	}//end of output
	
	public static void closing() {
		oGtTotal = nf.format(cGtTotal);
		
		//print gt totals in receipt-style 
		System.out.printf("%32s%6s%32s\n", DASHES , "Totals", DASHES);
		System.out.printf("%-16s%39s%15s\n", "Number of Sales:", "", cSaleCtr);
		System.out.printf("%-15s%40s%15s\n", "Grand Total:", "", oGtTotal);
	}
}//end of class
