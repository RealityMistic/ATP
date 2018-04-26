package AudioTrainerPlay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Utils {
	static final String logfile = "/temp/SCM-v0.1-Logfile.csv";
	static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
	static final String TIME_FORMAT_NOW = "yyyy-MM-dd:HH-mm-ss";
	static String RecordingList = null;
	static ArrayList userPasswords;
	int call;
	String DateTime = null;
	String Understanding = null;
	String Selection = null;
	String rssLinks = null;
	String lastRunDate = null;
	
	public static void debugOut(String s) {
		System.out.println(s);
	}	
	
	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
	
	public static String weekLater() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,3);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

}
