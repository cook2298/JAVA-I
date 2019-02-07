import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class PopSalesArray {
	static DecimalFormat df = new DecimalFormat("$#,###,##0.00");
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	static LocalDate today = LocalDate.now();
	static boolean eof = false, valid;
	static Scanner inputScanner;
	static PrintWriter pwValid, pwInvalid;
	static String iRec, iLName, iFName, iAddress, iCity, iState, errMsg, iZip1, iZip2, oCases, oDepAmt, oTotal;
	static char iTeam;
	static int iZip, iPopType, iCases, indexDep, indexTeam, cPCtr = 0, cErrPCtr = 0, cLCtr, newPageLines, cErrCtr = 0;
	static double cDepAmt, cTotal;
	static String[] 
					arrState = {"IA", "IL", "MI", "MO", "NE", "WI"},
					arrPopLit = {"Coke", "Diet Coke", "Mello Yello", "Cherry Coke", "Diet Cherry Coke", "Sprite"}, 
					arrErrorMsg = {" - Last name cannot be blank", 				//0
								" - First name cannot be blank",  				//1
								" - Address cannot be blank",					//2
								" - City cannot be blank", 						//3
								" - State must be IA, IL, MI, MO, NE, or WI",  	//4
								" - Zip must be numeric",						//5
								" - Pop type must be numeric", 					//6
								" - Pop type must be 1 - 6", 					//7
								" - Cases must be numeric", 					//8
								" - Cases must be greater than 0", 				//9
								" - Team must be A - E"};						//10
	static char[] arrTeamLit = {'A', 'B', 'C', 'D', 'E'};
	static int[] arrPopCtr = new int[arrPopLit.length];
	static double[] arrTeamAccum = new double[arrTeamLit.length], arrPopDep = {.05, 0, .10, 0, .05, .05};
	
	
	public static void main(String[] args) {
		init();
		while(!eof) {
			validation();
			if (valid) {
				calcs();
				output();
				read();	
			}
			else {
				error();
				read();
			}				
			
		}//while end

		closing();
	}//main end
	
	public static void init() {
		try {
			inputScanner = new Scanner(new File("CBLPOPSL.DAT"));
			inputScanner.useDelimiter(System.getProperty("line.separator"));
		} catch (FileNotFoundException e1) {
			System.out.println("File error");
			System.exit(1);
		}
		try {
			pwValid = new PrintWriter(new File ("javapopslb.prt"));
		} catch (FileNotFoundException e) {
			System.out.println("Output file error");
		}
		try {
			pwInvalid = new PrintWriter(new File ("javapoperb.prt"));
		} catch (FileNotFoundException e) {
			System.out.println("Output file error");
		}
		
		for(int x=0; x < arrTeamAccum.length;x++) {
			arrTeamAccum[x] = 0;
		}
		for (int x=0; x < arrPopCtr.length; x++) {
			arrPopCtr[x] = 0;
		}
		
		validHdgs();
		invalidHdgs();
		read();
		
		
	}//end init
	public static void read() {
		if (inputScanner.hasNext()) {
			iRec = inputScanner.next();
		}//end if
		else {
			eof = true;
		}//end else
		
	}//end read
	
	public static void validation() {
		valid = false;
		iLName = iRec.substring(0,15);
		if (iLName.trim().isEmpty()) {
			errMsg = arrErrorMsg[0];
			return;
		}
		iFName = iRec.substring(15,30);
		if (iFName.trim().isEmpty()) {
			errMsg = arrErrorMsg[1];
			return;
		}
		iAddress = iRec.substring(30,45);
		if (iAddress.trim().isEmpty()) {
			errMsg = arrErrorMsg[2];
			return;
		}
		iCity = iRec.substring(45,55);
		if (iCity.trim().isEmpty()) {
			errMsg = arrErrorMsg[3];
			return;
		}
		
		iState = iRec.substring(55,57).toUpperCase();
		int x;
		indexDep = -1;
		for(x = 0; x < arrPopDep.length; x++) {
			if (iState.equals(arrState[x])) {
				indexDep = x;
			}
		}
		if (indexDep == -1) {
			errMsg = arrErrorMsg[4];
			return;
		}

		try {
			iZip = Integer.parseInt(iRec.substring(57,66));
			
		}catch(Exception e) {
			errMsg = arrErrorMsg[5];
			return;
		}
		try {
			iPopType = Integer.parseInt(iRec.substring(66,68));
		}catch (Exception e) {
			errMsg = arrErrorMsg[6];
			return;
		}
		if (iPopType < 1 || iPopType > 6) {
			errMsg = arrErrorMsg[7];
			return;
		}
		try {
			iCases = Integer.parseInt(iRec.substring(68,70));
		}catch(Exception e) {
			errMsg = arrErrorMsg[8];
			return;
		}
		if (iCases < 1) {
			errMsg = arrErrorMsg[9];
			return;
		}
		iTeam = iRec.substring(70,71).toUpperCase().charAt(0);
		if (iTeam != 'A' && iTeam != 'B' && iTeam != 'C' && iTeam != 'D' && iTeam != 'E' ) {
			errMsg = arrErrorMsg[10];
			return;
		}
		valid = true;
		
	}//end validation
	
	public static void error() {
		
		pwInvalid.format("%-132s\n\n", iRec + errMsg);
		cErrCtr ++;
		
	}
	
	public static void calcs() {
		//assigning deposit index

		cDepAmt = iCases * 24 * arrPopDep[indexDep];
		cTotal = (18.71  * iCases) + cDepAmt;
		
		arrPopCtr[iPopType - 1] += iCases;
		
		for (int x = 0; x < arrTeamLit.length; x++) {
			if (iTeam == arrTeamLit[x]) {
				indexTeam = x;
			}
		}
		arrTeamAccum[indexTeam] += cTotal;
	}//end calcs
	
	public static void output() {
		
		iZip1 = Integer.toString(iZip).substring(0,4);
		iZip2 = Integer.toString(iZip).substring(4,8);
		oCases = Integer.toString(iCases);
		oDepAmt = df.format(cDepAmt);
		oTotal = df.format(cTotal);
		pwValid.format("%3s%15s%2s%15s%2s%10s%3s%2s%3s%5s%1s%4s%3s%-16s%8s%2s%11s%7s%9s%9s\n\n", " ", iLName, "",iFName,"", iCity, "", iState, "",  iZip1, "-", iZip2, "", arrPopLit[iPopType - 1], "", oCases, "", oDepAmt , "", oTotal);
		cLCtr +=2;
		if (cLCtr >45) {
			validHdgs();
		}
		
	}//end output
	
	
	public static void validHdgs() {
		
		cPCtr ++;
		
		pwValid.format("Date: %s%36s%28s%44s%6s%2s\n", today.format(dtf), " ", "Albia Soccer Club Fundraiser", " ", "Page: ", cPCtr);
		pwValid.format("JavaJHC06%48s%15s%48s\n", " ", "Cook Division", " " );		
		pwValid.format("%60s%12s%60s\n\n", " ", "Sales Report", " ");
		pwValid.format("%3s%9s%8s%10s%7s%4s%7s%5s%3s%8s%4s%8s%13s%8s%6s%11s%6s%11s\n\n", "", "Last Name","", "First Name","", "City","", "State","", "Zip Code","", "Pop Type","", "Quantity","", "Deposit","", "Total Sales");
		cLCtr = 6;
	}//end validHdgs
	
	public static void invalidHdgs() {
		cErrPCtr ++;
		pwInvalid.format("Date: %s%36s%28s%44s%6s%2s\n", today.format(dtf), " ", "Albia Soccer Club Fundraiser", " ", "Page: ", cErrPCtr);
		pwInvalid.format("JavaJHC06%48s%15s%48s\n", " ", "Cook Division", " " );		
		pwInvalid.format("%60s%12s%60s\n\n", " ", "Error Report", " ");
		pwInvalid.format("%12s%60s%16s\n", "Error Record", " ", "Error Discription");
	}//end invalidHdgs
	
	public static void closing() {
		
		for (newPageLines = (45 - cLCtr); newPageLines > 0; newPageLines --) {
			pwValid.format("%132s\n", "");
		}
		validHdgs();
		pwValid.format("%132s\n", "");
		pwValid.format("%15s\n\n", "Grand Totals:");
		
		for(int x=0; x < 3; x++) {
			pwValid.format("%3s%-17s%-13s", "", arrPopLit[x], arrPopCtr[x]);
		}
		pwValid.format("%132s\n\n", "");
		
		for (int x = 3; x < 6;x++ ) {
			pwValid.format("%3s%-17s%-13s", "", arrPopLit[x], arrPopCtr[x]);
		}
		pwValid.format("%132s\n\n", "");
		pwValid.format("%15s\n\n", "Team Totals:");
		
		for(int x=0; x < arrTeamLit.length; x++) {
			cTotal = arrTeamAccum[x];
			oTotal = df.format(cTotal);
			
			pwValid.format("%3s%-2s%-15s\n\n", "", arrTeamLit[x], oTotal  );
		}
		
		pwInvalid.format("%132s\n", "");
		pwInvalid.format("%13s%5s", "Total Errors ", cErrCtr);
		pwValid.close();
		pwInvalid.close();
		inputScanner.close();
		
	}//end closing 
	
	
	
	
	
	
	
	
	
}//end class
