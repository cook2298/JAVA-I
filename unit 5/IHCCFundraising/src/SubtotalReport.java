/*
 * Author: Jacob Cook
 * Date Written : 01/25/2019 - 01/28/2019
 * This program reads an ordered DAT file and creates a subtotal report that contains a major break on the major type
 * the detail lines, subtotal lines, and grand total line are all outputed to subtotal.prt
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class SubtotalReport {
	static DecimalFormat df = new DecimalFormat("#,###.00");
	static LocalDate today = LocalDate.now();
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
	static PrintWriter pw;
	static Scanner fileScanner, consoleScanner;
	static boolean eof =false;
	static String iRecord, iStudentID, iGender, hMajor, iMajor, iDonation, oMajor, oGender, input;
	static Double cDonation, cSubDonation = 0.0, cGtDonation = 0.0;
	static int cSubCtr = 0, cGtCtr = 0;
	public static void main(String[] args) {
		init();
		while (!eof) {
			if (!(hMajor.equals(iMajor))) {
				subtotal();
			}
			calc();
			output();
			read();
		}
		closing();
		
	}//main end
	public static void init() {
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
			pw = new PrintWriter(new File ("subtotal.prt"));
		} catch (FileNotFoundException e) {
			System.out.println("Output file error");
		}		
		
		//setting headings for output file
		pw.format("%5s%8s%38s%30s%57s\n", "Date:", today.format(dtf), " ", "Indian Hills Community College", " ");
		pw.format("%52s%27s%53s\n\n", " ", "Fundraising Subtotal Report", " ");
		pw.format("%8s%10s%18s%6s%34s%5s%33s%8s%9s\n", "","Student ID", "", "Gender", "", "Major", "", "Donation", "");
		
		read();
		hMajor = iMajor;
	}//init end
	
	public static void read() {
		
		if (fileScanner.hasNext()) {
			iRecord = fileScanner.next();
			iStudentID = iRecord.substring(0, 7);
			iGender = iRecord.substring(7,8);
			iMajor = iRecord.substring(8,10);
			iDonation = iRecord.substring(10,17);
		}
		else {
			eof = true;
		}
	}//read end
	
	public static void subtotal () {
		
		pw.format("%132s\n", "");
		pw.format("%14s%15s%-33s%20s%7s%3s%20s%10s%10s\n\n", "", "Subtotals For: ", oMajor, "", "Count: ", cSubCtr, "", NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(cSubDonation), "");
		
		cGtCtr += cSubCtr;
		cGtDonation += cSubDonation;
		
		cSubCtr = 0;
		cSubDonation = 0.0;
		
		hMajor = iMajor;
	}//subtotal end
	
	public static void calc() {
		cDonation = Double.parseDouble(iDonation);
		cSubDonation += cDonation;
		cSubCtr ++;
	}//calc end
	public static void output() {
		//determining gender and major literal 
		if (iGender.equals("M")){
			oGender = "Male";
		}
		else {
			oGender = "Female";
		}
		
		switch (iMajor) {
			case "01":
				oMajor = "Computer Software Development";
				break;
			case "02":
				oMajor = "Diesel Power Systems Technology";
				break;
			case "03":
				oMajor = "Automotive Technology";
				break;
			case "04":
				oMajor = "Laser / Electro-optics Technology";
				break;
			case "05":
				oMajor = "Robotics/Automation Technology";
				break;
			case "06":
				oMajor = "Digital Forensics";
				break;
			case "07":
				oMajor = "Machine Technology";
				break;
			case "08":
				oMajor = "Geospatial Technology";
				break;
			case "09":
				oMajor = "Administrative Assistant";
				break;
			case "10":
				oMajor = "Accounting Assistant";
				break;
			case "11":
				oMajor = "Welding Technology";
				break;
			case "12":
				oMajor = "Automotive Collision Technology";
				break;
			case "13":
				oMajor = "Avaiation Pilot Training";
				break;
			
		}
		//outputting detail line
		pw.format("%9s%7s%20s%-6s%20s%-33s%18s%9s%10s\n", "", iStudentID, "", oGender, "", oMajor, "", df.format(cDonation), "" );
	}//output end
	
	public static void closing() {
		subtotal();
		pw.format("%132s\n", "");
		pw.format("%14s%12s%55s%7s%4s%16s%13s%10s", "", "Grand Totals:", "", "Count: ", cGtCtr, "", NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(cGtDonation), "");
		pw.close();
		fileScanner.close();
	}//closing end
		
}//class end
