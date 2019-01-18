/*
 * This program brings in contributor information from a .dat file. Then it is validated. If the 
 * data is invalid, the line of bad data is printed to error.prt along with an error message.
 * If the data is valid, it is add to the appropriate counters and accumulators. After all records 
 * have been validated and processed, the console display the count, total contributions, and average
 * for the following groups: Men, Women, Democrats, Republicans, Independents, Democratic Men, 
 * Democratic Women, Republican Women, Independent Men, Independent Women, and the overall total.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
	public class SaveTheBarns {

	static String iRecord, iName, iAddress, iCity, iState, iZip, iCon, errMsg;
	static String oMCtr, oWCtr, oDemCtr, oRepCtr, oIndCtr, oMDemCtr, oWDemCtr, oMRepCtr, oWRepCtr, oMIndCtr, oWIndCtr, oTotCtr, oMCon, oWCon, oDemCon, oRepCon, oIndCon, oMDemCon, oWDemCon, oMRepCon, oWRepCon, oMIndCon, oWIndCon, oTotCon, oAvgM, oAvgW, oAvgDem, oAvgRep, oAvgInd, oAvgMDem, oAvgWDem, oAvgMRep, oAvgWRep, oAvgMInd, oAvgWInd, oAvgCon;
	static Double cCon,cTotCon, cMCon, cWCon, cDemCon,cRepCon, cIndCon, cMDemCon = 0.0, cMRepCon = 0.0, cMIndCon = 0.0, cWDemCon = 0.0, cWRepCon = 0.0, cWIndCon = 0.0;
	static Double cAvgM, cAvgW, cAvgDem, cAvgRep, cAvgInd, cAvgMDem, cAvgWDem, cAvgMRep, cAvgWRep, cAvgMInd, cAvgWInd, cAvgCon;
	static boolean moreRecs= true, valid = true;
	static char iParty, iGender;
	static int cZip, cTotCtr, cMCtr,cWCtr, cDemCtr, cRepCtr, cIndCtr, cMDemCtr = 0, cMRepCtr = 0, cMIndCtr = 0, cWDemCtr = 0, cWRepCtr = 0, cWIndCtr = 0;
	static Scanner conScanner;
	static PrintWriter pw;
	static LocalDate today = LocalDate.now();
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	static NumberFormat nf;
	
	public static void main(String[] args) {
		
		init();
		
		while (moreRecs) {
			if (valid) {
				calcs();
			}
			else {
				error();
			}
			read();
		}
		closing();
	} //main end
	public static void init() {
		
		//setting scanner to input file
		try {
			conScanner = new Scanner (new File("contributors.dat"));
			conScanner.useDelimiter(System.getProperty("line.separator"));
		} catch (FileNotFoundException e1) {
			System.out.println("Input file error");
			System.exit(1);
		}
		//setting PrintWriter to output file
		try {
			pw = new PrintWriter(new File ("error.prt"));
		} catch (FileNotFoundException e) {
			System.out.println("Output file error");
		}
		read();
	}
	public static void read(){
		if (conScanner.hasNext()) {
			iRecord = conScanner.next();
			validation();
		}
		else {
			moreRecs = false;
		}
	}
	public static void validation() {
		valid = false; // assuming data is invalid at top
		//validating iName
		iName = iRecord.substring(0,25); 
		if (iName.trim().isEmpty()) {
			errMsg = " - Contributor name is required";
			return;
		} 
		//validating iAddress
		iAddress = iRecord.substring(25, 50);
		if (iAddress.trim().isEmpty()) {
			errMsg = " - Address is required";
			return;
		}
		//validating iCity
		iCity = iRecord.substring(50,65);
		if (iCity.trim().isEmpty()) {
			errMsg = " - City is required";
			return;
		}
		//validating iState
		iState = iRecord.substring(65,67);
		if (iState.trim().isEmpty()) {
			errMsg = " - State is required";
			return;
		}
		//validating Zip
		iZip = iRecord.substring(67,72);
		try {
			cZip = Integer.parseInt(iZip);
		}
		catch (Exception e) {
			errMsg = " - Zip must be numeric";
			return;
		}
		//validating party
		iParty = iRecord.substring(72,73).charAt(0);
		if(iParty != 'D' && iParty != 'I' && iParty != 'R') {
			errMsg = " - Party must be D, I, or R";
			return;
		}
		//validating gender
		iGender = iRecord.substring(73,74).charAt(0);
		if (iGender != 'M' && iGender != 'F' ) {
			errMsg = " - Gender must be M or F";
			return;
		}
		//validating Contribution
		iCon = iRecord.substring(74,81);
		try {
			cCon = Double.parseDouble(iCon);
			if (cCon < 0.01 || cCon > 9999.99) {
				errMsg = " - Contribution must be 00.01 - 9999.99";
				return;
			}
		}
		catch (Exception e) {
			errMsg = " - Contribution must be numeric and between 00.01 - 9999.99";
			return;
		}
		valid = true; //if no fields are invalid, then they will make it to here
			
	}//validation end
	public static void error() {
		
		pw.println(iRecord + errMsg);
		
	}//error end
	public static void calcs() {
		if (iGender == 'M') {
			switch (iParty) {
				case 'D': 
					cMDemCtr ++;
					cMDemCon += cCon;
					break;
				case 'R':
					cMRepCtr ++;
					cMRepCon += cCon;
					break;
				case 'I':
					cMIndCtr++;
					cMIndCon += cCon;
					break;
			} //end switch
		}//end if
		else {
			switch (iParty) {
				case 'D': 
					cWDemCtr ++;
					cWDemCon += cCon;
					break;
				case 'R':
					cWRepCtr ++;
					cWRepCon += cCon;
					break;
				case 'I':
					cWIndCtr++;
					cWIndCon += cCon;
					break;
			}//end switch
		}//end else
	}//end calcs
	public static void closing() {
		//calculating ctrs
		cMCtr = cMDemCtr + cMRepCtr + cMIndCtr;
		cWCtr = cWDemCtr + cWRepCtr + cWIndCtr;
		cDemCtr = cMDemCtr + cWDemCtr;
		cRepCtr = cMRepCtr + cWRepCtr;
		cIndCtr = cMIndCtr + cWIndCtr;
		//calculating contributions
		cMCon = cMDemCon + cMRepCon + cMIndCon;
		cWCon = cWDemCon + cWRepCon + cWIndCon;
		cDemCon = cMDemCon + cWDemCon;
		cRepCon = cMRepCon + cWRepCon;
		cIndCon = cMIndCon + cWIndCon;
		cTotCtr = cMCtr + cWCtr;
		cTotCon = cMCon + cWCon;
		//Calculating averages
		if (cMCtr == 0) {
			cAvgM = 0.0;
		}
		else {
			cAvgM = cMCon / cMCtr;
		}
		if (cWCtr == 0) {
			cAvgW = 0.0;
		}
		else {
			cAvgW = cWCon / cWCtr;
		}
		if (cDemCtr == 0) {
			cAvgDem = 0.0;
		}
		else {
			cAvgDem = cDemCon / cDemCtr;
		}
		if (cRepCtr == 0) {
			cAvgRep =0.0;
		}
		else {
			cAvgRep = cRepCon / cRepCtr;
		}
		if (cIndCtr == 0) {
			cAvgInd = 0.0;
		}
		else {
			cAvgInd = cIndCon / cIndCtr;
		}
		if (cMDemCtr == 0) {
			cAvgMDem = 0.0;
		}
		else {
			cAvgMDem = cMDemCon / cMDemCtr;
		}
		if(cWDemCtr == 0){
			cAvgWDem = 0.0;
		}
		else {
			cAvgWDem = cWDemCon / cWDemCtr;
		}
		if (cMRepCtr == 0) {
			cAvgMRep = 0.0;
		}
		else {
			cAvgMRep = cMRepCon / cMRepCtr;
		}
		if (cWRepCtr == 0) {
			cAvgWRep = 0.0;
		}
		else {
			cAvgWRep = cWRepCon / cWRepCtr;
		}
		if (cIndCtr == 0) {
			cAvgMInd = 0.0;
		}
		else {
			cAvgMInd = cMIndCon / cMIndCtr;
		}
		if (cWIndCtr == 0) {
			cAvgWInd = 0.0;
		}
		else {
			cAvgWInd = cWIndCon / cWIndCtr;
		}
		if (cTotCtr == 0) {
			cAvgCon = 0.0;
		}
		else {
			cAvgCon = cTotCon / cTotCtr;
		}
		
		//call output to display totals
		output();
		//close files
		pw.close();
		conScanner.close();
		
	}//closing end
	public static void output() {
		NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
		//formatting output variables
		oMCon = nf.format(cMCon);
		oWCon = nf.format(cWCon);
		oDemCon = nf.format(cDemCon);
		oRepCon = nf.format(cRepCon);
		oIndCon = nf.format(cIndCon);
		oMDemCon = nf.format(cMDemCon);
		oMRepCon = nf.format(cMRepCon);
		oMIndCon = nf.format(cMIndCon);
		oWDemCon = nf.format(cWDemCon);
		oWRepCon = nf.format(cWRepCon);
		oWIndCon = nf.format(cWIndCon);
		oTotCon = nf.format(cTotCon);
		
		oAvgM = nf.format(cAvgM);
		oAvgW = nf.format(cAvgW);
		oAvgDem = nf.format(cAvgDem);
		oAvgRep = nf.format(cAvgRep);
		oAvgInd = nf.format(cAvgInd);
		oAvgMDem = nf.format(cAvgMDem);
		oAvgWDem = nf.format(cAvgWDem);
		oAvgMRep = nf.format(cAvgMRep);
		oAvgWRep = nf.format(cAvgWRep);
		oAvgMInd = nf.format(cAvgMInd);
		oAvgWInd = nf.format(cAvgWInd);
		oAvgCon = nf.format(cAvgCon);
						
		//company title
		System.out.format("Date: %s%16s%16s%32s\n", today.format(dtf), " ", "Barn Savers Inc.", " ");
		//report title
		System.out.format("%23s%34s%23s\n\n", " ", "Save the Barns Contribution Report", " ");
		//headings
		System.out.format("%8s%15s%5s%12s%12s%12s%16s\n", "Category"," ", "Count", " ", "Contribution"," ", "Average Donation" );
		//detail lines
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Men", " ", cMCtr, " ", oMCon, " ", oAvgM );							//Men
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Women", " ", cWCtr, " ", oWCon , " ", oAvgW ); 						//Women
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Democrats", " ", cDemCtr , " ", oDemCon, " ", oAvgDem );			//Democrats
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Republicans", " ", cRepCtr, " ", oRepCon, " ", oAvgRep);			//Republicans
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Independent", " ", cIndCtr, " ", oIndCon, " ", oAvgInd);			//Independents 
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Democratic Men", " ", cMDemCtr, " ", oMDemCon, " ", oAvgMDem );		//Democratic Men
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Democratic Women", " ", cWDemCtr, " ", oWDemCon, " ", oAvgWDem);	//Democratic Women
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Republican Men", " ", cMRepCtr, " ", oMRepCon, " ", oAvgMRep);		//Republican Men
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Republican Women", " ", cWRepCtr, " ", oWRepCon, " ", oAvgWRep);	//Republican Women
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Independent Men", " ", cMIndCtr, " ", oMIndCon, " ", oAvgMInd);		//Independent Men
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Independent Women", " ", cWIndCtr, " ", oWIndCon, " ", oAvgWInd);	//Independent Women
		System.out.format("%-17s%6s%5s%12s%12s%12s%16s\n", "Overall", " " , cTotCtr, " ", oTotCon, " ", oAvgCon);				//Overall
			
	}//output end
	
}//class end 
