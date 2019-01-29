import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class SummaryReport {
	static DecimalFormat df = new DecimalFormat("#,###,###.00");
	static NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
	static LocalDate today = LocalDate.now();
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
	static PrintWriter pw;
	static Scanner fileScanner, consoleScanner;
	static boolean eof =false, over500 = false;
	static String iRecord, iStudentID, iGender, hMajor, iMajor, iDonation, oMajor, oGender, oMDonation;
	static double cDonation, cMInfoDonation = 0, cFInfoDonation = 0, cMManDonation =0, cFManDonation = 0, cMTransDonation =0, cFTransDonation =0, cMDonation, cFDonation, cInfoDonation, cManDonation, cTransDonation, cTotDonation, avgM, avgF, avgInfo, avgMan, avgTrans, avgMInfo, avgFInfo, avgMMan, avgFMan, avgMTrans, avgFTrans, avgTot;
	static int cMInfoCtr = 0, cFInfoCtr = 0,cMManCtr = 0, cFManCtr = 0, cMTransCtr = 0, cFTransCtr =0,  cMCtr, cFCtr, cInfoCtr, cManCtr, cTransCtr, cTotCtr;
	static char input;
	
	public static void main(String[] args) {
		
		init();
		
		while (!eof) {
			calcs();
			read();
		}
		closing();		
	} //main end
	public static void init() {
		
		consoleScanner = new Scanner(System.in);
		System.out.println("For the Summary Report, would you like to proccess records with a donation amount of $500.00 or more? (Y or N)");
		input = consoleScanner.next().toUpperCase().charAt(0);
		if (input == 'Y') {
			over500 = true;
		}
		else {
			over500 =false;
		}
		
		//setting scanner to input file
		try {
			fileScanner = new Scanner (new File("IHCCFUND.dat"));
			fileScanner.useDelimiter(System.getProperty("line.separator"));
		} catch (FileNotFoundException e1) {
			System.out.println("Input file error");
			System.exit(1);
		}//catch end		
		//setting PrintWriter to output file
		try {
			pw = new PrintWriter(new File ("summary.prt"));
		} catch (FileNotFoundException e) {
			System.out.println("Output file error");
		}		

		//setting headings for output file
		pw.format("%5s%8s%38s%30s%57s\n", "Date: ", today.format(dtf), "", "Indian Hills Community College", "");
		if (over500) {
			pw.format("%48s%39s%45s\n\n", "", "Fundraising Subtotal Report - Over $500", "");
		}
		if (!over500) {
			pw.format("%54s%27s%51s\n\n", "", "Fundraising Subtotal Report", "");
		}
		pw.format("%10s%8s%24s%5s%24s%13s%24s%14s%10s\n", "", "Category", "", "Count", "", "Amount Raised", "", "Average Raised", "" );

		read();
	}//end init
	public static void read() {
		if (over500) {
			do {
				if (fileScanner.hasNext()) {
					iRecord = fileScanner.next();
					iStudentID = iRecord.substring(0, 7);
					iGender = iRecord.substring(7,8);
					iMajor = iRecord.substring(8,10);
					iDonation = iRecord.substring(10,17);
					cDonation = Double.parseDouble(iDonation);
				}
				else {
					eof = true;
				}
			} while (cDonation < 500);
		}
		if (!over500) {
			if (fileScanner.hasNext()) {
				iRecord = fileScanner.next();
				iStudentID = iRecord.substring(0, 7);
				iGender = iRecord.substring(7,8);
				iMajor = iRecord.substring(8,10);
				iDonation = iRecord.substring(10,17);
				cDonation = Double.parseDouble(iDonation);
			}
			else {
				eof = true;
			}
		}	
	}//read end
	public static void calcs() {
			
			
			switch (iMajor) {
			case "01":
				if (iGender.equals("M")){
					cMInfoDonation += cDonation;
					cMInfoCtr ++;
				}
				else {
					cFInfoDonation += cDonation;
					cFInfoCtr ++;
				}
				break;
			case "02":
				if (iGender.equals("M")) {
					cMTransDonation += cDonation;
					cMTransCtr ++;
				}
				else {
					cFTransDonation += cDonation;
					cFTransCtr ++;
				}
				break;
			case "03":
				if (iGender.equals("M")) {
					cMTransDonation += cDonation;
					cMTransCtr ++;
				}
				else {
					cFTransDonation += cDonation;
					cFTransCtr ++;
				}
				break;
			case "04":
				if (iGender.equals("M")) {
					cMManDonation += cDonation;
					cMManCtr ++;
				}
				else {
					cFManDonation += cDonation;
					cFManCtr ++;
				}
				break;
			case "05":
				if (iGender.equals("M")) {
					cMManDonation += cDonation;
					cMManCtr ++;
				}
				else {
					cFManDonation += cDonation;
					cFManCtr ++;
				}
				break;
			case "06":
				if (iGender.equals("M")){
					cMInfoDonation += cDonation;
					cMInfoCtr ++;
				}
				else {
					cFInfoDonation += cDonation;
					cFInfoCtr ++;
				}
				break;
			case "07":
				if (iGender.equals("M")) {
					cMManDonation += cDonation;
					cMManCtr ++;
				}
				else {
					cFManDonation += cDonation;
					cFManCtr ++;
				}
				break;
			case "08":
				if (iGender.equals("M")){
					cMInfoDonation += cDonation;
					cMInfoCtr ++;
				}
				else {
					cFInfoDonation += cDonation;
					cFInfoCtr ++;
				}
				break;
			case "09":
				if (iGender.equals("M")){
					cMInfoDonation += cDonation;
					cMInfoCtr ++;
				}
				else {
					cFInfoDonation += cDonation;
					cFInfoCtr ++;
				}
				break;
			case "10":
				if (iGender.equals("M")){
					cMInfoDonation += cDonation;
					cMInfoCtr ++;
				}
				else {
					cFInfoDonation += cDonation;
					cFInfoCtr ++;
				}
				break;
			case "11":
				if (iGender.equals("M")) {
					cMManDonation += cDonation;
					cMManCtr ++;
				}
				else {
					cFManDonation += cDonation;
					cFManCtr ++;
				}
				break;
			case "12":
				if (iGender.equals("M")) {
					cMTransDonation += cDonation;
					cMTransCtr ++;
				}
				else {
					cFTransDonation += cDonation;
					cFTransCtr ++;
				}
				break;
			case "13":
				if (iGender.equals("M")) {
					cMTransDonation += cDonation;
					cMTransCtr ++;
				}
				else {
					cFTransDonation += cDonation;
					cFTransCtr ++;
				}
				break;
		}//end switch
	}// calcs end	
	public static void closing() {
		//figuring higher level accums and ctrs
		cInfoDonation = cMInfoDonation + cFInfoDonation;
		cManDonation = cMManDonation + cFManDonation;
		cTransDonation = cMTransDonation + cFTransDonation;
		cInfoCtr = cMInfoCtr + cFInfoCtr;
		cManCtr = cMManCtr + cFManCtr;
		cTransCtr = cMTransCtr + cFTransCtr;
		cMDonation = cMInfoDonation + cMManDonation + cMTransDonation;
		cFDonation = cFInfoDonation + cFManDonation + cFTransDonation;
		cMCtr = cMInfoCtr + cMManCtr + cMTransCtr;
		cFCtr = cFInfoCtr + cFManCtr + cFTransCtr;
		cTotDonation = cMDonation + cFDonation;
		cTotCtr = cMCtr + cFCtr;
		
		//calculating averages
		if (cMCtr == 0) {
			avgM = 0;
		}
		else {
			avgM = cMDonation / cMCtr;

		}
		if (cFCtr == 0) {
			avgF = 0;
		}
		else {
		avgF = cFDonation / cFCtr;
		}
		if (cInfoCtr == 0) {
			avgInfo = 0;
		}
		else {
		avgInfo = cInfoDonation / cInfoCtr;
		}
		if (cManCtr == 0) {
			avgMan = 0;
		}
		else {
		avgMan = cManDonation / cManCtr;
		}
		if (cTransCtr == 0) {
			avgTrans = 0;
		}
		else {
		avgTrans = cTransDonation / cTransCtr;
		}
		if (cMInfoCtr == 0) {
			avgMInfo = 0;
		}
		else {
		avgMInfo = cMInfoDonation / cMInfoCtr;
		}
		if (cFInfoCtr == 0) {
			avgFInfo =0;
		}
		else {
		avgFInfo = cFInfoDonation / cFInfoCtr;
		}
		if (cMManCtr == 0) {
			avgMMan = 0;
		}
		else {
		avgMMan = cMManDonation / cMManCtr;
		}
		if (cFManCtr == 0) {
			avgFMan = 0;
		}
		else {
		avgFMan = cFManDonation / cFManCtr;
		}
		if (cMTransCtr == 0) {
			avgMTrans = 0;
		}
		else {
		avgMTrans = cMTransDonation / cMTransCtr;
		}
		if (cFTransCtr == 0) {
			avgFTrans = 0;
		}
		else {
		avgFTrans = cFTransDonation / cFTransCtr;
		}
		if (cTotCtr == 0) {
			avgTot = 0;
		}
		else {
			avgTot = cTotDonation / cTotCtr;
		}
		
		//round output variables to the hundrends place
		cMDonation = cMDonation * 100;
		cMDonation = Math.round(cMDonation);
		cMDonation = cMDonation /100;
		avgM = avgM * 100;
		avgM = Math.round(avgM);
		avgM = avgM /100;
		cFDonation = cFDonation * 100;
		cFDonation = Math.round(cFDonation);
		cFDonation = cFDonation / 100;
		avgF = avgF *100;
		avgF = Math.round(avgF);
		avgF = avgF /100;
		cInfoDonation = cInfoDonation * 100;
		cInfoDonation = Math.round(cInfoDonation);
		cInfoDonation = cInfoDonation /100;
		avgInfo = avgInfo * 100;
		avgInfo = Math.round(avgInfo);
		avgInfo = avgInfo /100;
		cManDonation = cManDonation *100;
		cManDonation = Math.round(cManDonation);
		cManDonation = cManDonation/ 100;
		avgMan = avgMan * 100;
		avgMan = Math.round(avgMan);
		avgMan = avgMan / 100;
		cTransDonation = cTransDonation * 100; 
		cTransDonation = Math.round(cTransDonation);
		cTransDonation = cTransDonation /100;
		avgTrans = avgTrans * 100;
		avgTrans = Math.round(avgTrans);
		avgTrans = avgTrans /100;
		cMInfoDonation = cMInfoDonation * 100; 
		cMInfoDonation = Math.round(cMInfoDonation);
		cMInfoDonation = cMInfoDonation / 100;
		avgMInfo = avgMInfo * 100;
		avgMInfo = Math.round(avgMInfo);
		avgMInfo = avgMInfo / 100;
		cFInfoDonation = cFInfoDonation * 100;
		cFInfoDonation = Math.round(cFInfoDonation);
		cFInfoDonation = cFInfoDonation / 100;
		avgFInfo = avgFInfo * 100; 
		avgFInfo = Math.round(avgFInfo);
		avgFInfo = avgFInfo / 100;
		cMManDonation = cMManDonation * 100;
		cMManDonation = Math.round(cMManDonation);
		cMManDonation = cMManDonation /100;
		avgMMan *= 100;
		avgMMan = Math.round(avgMMan);
		avgMMan /= 100;
		cFManDonation *= 100;
		cFManDonation = Math.round(cFManDonation);
		cFManDonation /=100;
		avgFMan *=100;
		avgFMan = Math.round(avgFMan);
		avgFMan /=100;
		cMTransDonation *= 100;
		cMTransDonation = Math.round(cMTransDonation);
		cMTransDonation /= 100;
		avgMTrans *= 100;
		avgMTrans = Math.round(avgMTrans);
		avgMTrans /= 100;
		cFTransDonation *= 100; 
		cFTransDonation = Math.round(cFTransDonation);
		cFTransDonation /= 100; 
		avgFTrans *=100;
		avgFTrans = Math.round(avgFTrans);
		avgFTrans /=100; 
		cTotDonation *= 100;
		cTotDonation = Math.round(cTotDonation);
		cTotDonation /=100; 
		avgTot *= 100;
		avgTot = Math.round(avgTot);
		avgTot /=100;
		
		output();
		
		pw.close();
		fileScanner.close();
	}//end closing 
	public static void output() {
		// formatting final output
		pw.format("%10s%-8s%24s%5s%24s%13s%24s%14s%10s\n", "", "Male", "", cMCtr, "", nf.format(cMDonation), "", nf.format(avgM), "" );
		pw.format("%10s%-8s%24s%5s%24s%13s%24s%14s%10s\n", "", "Female", "", cFCtr, "", nf.format(cFDonation), "", nf.format(avgF), "" );
		pw.format("%10s%-22s%10s%5s%24s%13s%24s%14s%10s\n", "", "Information Technology", "", cInfoCtr, "", nf.format(cInfoDonation), "", nf.format(avgInfo), "" );
		pw.format("%10s%-30s%2s%5s%24s%13s%24s%14s%10s\n", "", "Manufacturing Technology", "", cManCtr, "", nf.format(cManDonation), "", nf.format(avgMan), "" );
		pw.format("%10s%-30s%2s%5s%24s%13s%24s%14s%10s\n", "", "Transportation Technology", "", cTransCtr, "", nf.format(cTransDonation), "", nf.format(avgTrans), "" );
		pw.format("%10s%-30s%2s%5s%24s%13s%24s%14s%10s\n", "", "Male Information Technology", "", cMInfoCtr, "", nf.format(cMInfoDonation), "", nf.format(avgMInfo), "" );
		pw.format("%10s%-30s%2s%5s%24s%13s%24s%14s%10s\n", "", "Female Information Technology", "", cFInfoCtr, "", nf.format(cFInfoDonation), "", nf.format(avgFInfo), "" );
		pw.format("%10s%-30s%2s%5s%24s%13s%24s%14s%10s\n", "", "Male Manufacturing Technology", "", cMManCtr, "", nf.format(cMManDonation), "", nf.format(avgMMan), "" );
		pw.format("%10s%-31s%1s%5s%24s%13s%24s%14s%10s\n", "", "Female Manufacturing Technology", "", cFManCtr, "", nf.format(cFManDonation), "", nf.format(avgFMan), "" );
		pw.format("%10s%-30s%2s%5s%24s%13s%24s%14s%10s\n", "", "Male Transportation Technology", "", cMTransCtr, "", nf.format(cMTransDonation), "", nf.format(avgMTrans), "" );
		pw.format("%10s%-30s%1s%4s%24s%13s%24s%14s%10s\n", "", "Female Transportation Technology", "", cFTransCtr, "", nf.format(cFTransDonation), "", nf.format(avgFTrans), "" );
		pw.format("%10s%-30s%2s%5s%24s%13s%24s%14s%10s\n", "", "Overall", "", cTotCtr, "", nf.format(cTotDonation), "", nf.format(avgTot), "" );

	}
	
}//class end 






 //pw.format("%10s%8s%24s%5s%24s%13s%24%14s%10s\n", "", "Category", "", "Count", "", "Amount Raised", "", "Average Raised", "" );












