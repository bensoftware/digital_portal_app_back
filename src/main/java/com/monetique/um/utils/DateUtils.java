package com.monetique.um.utils;
/**
 * @author bpm digitale
 *
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DateUtils {

	private static final SimpleDateFormat	dateFormat			= new SimpleDateFormat("dd/MM/yyyy");

	private static final SimpleDateFormat	dateFormatYYYYMMDD	= new SimpleDateFormat("yyyy-MM-dd");

	private static final SimpleDateFormat	dateTimeFormat		= new SimpleDateFormat("dd-MM-yyyy HH:mm");

	private static final SimpleDateFormat	dateFormatJJMM		= new SimpleDateFormat("dd/MM");

	private static final SimpleDateFormat	timeFormatHHMM		= new SimpleDateFormat("HH:mm");

	private static final SimpleDateFormat	dateTimeFormatAll	= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

	public static String formatDate(Date date) {
		if (date != null)
			return dateFormat.format(date);
		return "";
	}

	public static String formatDateJourMois(Date date) {
		return dateFormatJJMM.format(date);
	}

	public static String formatDateAAAAMMDD(Date date) {
		return dateFormatYYYYMMDD.format(date);
	}

	public static Date parseDateAAAAMMDD(String date) throws ParseException {
		return dateFormatYYYYMMDD.parse(date);
	}

	public static void initHourMinSec(Calendar cal) {
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	public static Date constructDate(String date, String heure, String minute) throws ParseException {
		Calendar cal = new GregorianCalendar();
		Date d = dateFormat.parse(date);
		cal.setTime(d);
		initHourMinSec(cal);
		if (heure != null && heure.length() > 0)
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(heure));
		if (minute != null && minute.length() > 0)
			cal.set(Calendar.MINUTE, Integer.parseInt(minute));
 

		return cal.getTime();
	}

	public static String formatDateAll(Date date) {
		return dateTimeFormatAll.format(date);
	}

	public static String formatDate2(Date date) {
		return dateFormat.format(date);
	}

	public static String formatDateTime(Date date) {
		return dateTimeFormat.format(date);
	}

	public static String formatDateToTimeHHMM(Date date) {
		return timeFormatHHMM.format(date);
	}

	public static int nbJours(Date d1, Date d2) {
		GregorianCalendar dd1 = new GregorianCalendar();
		dd1.setTime(d1);
		GregorianCalendar dd2 = new GregorianCalendar();
		dd2.setTime(d2);

		int yearFut = dd1.get(Calendar.YEAR);
		int yearPast = dd2.get(Calendar.YEAR);
		int nbJours = dd1.get(Calendar.DAY_OF_YEAR) - dd2.get(Calendar.DAY_OF_YEAR);

		if (yearFut != yearPast) {
			for (int k = yearPast; k < yearFut; k++) {
				if (dd1.isLeapYear(k))
					nbJours += 366;
				else
					nbJours += 365;
			}
			for (int k = yearFut; k < yearPast; k++) {
				if (dd1.isLeapYear(k))
					nbJours -= 366;
				else
					nbJours -= 365;
			}
		}
		return nbJours;
	}

	public static int calculAge(Calendar maintenant, Calendar dateNais) {
		int age = maintenant.get(Calendar.YEAR) - dateNais.get(Calendar.YEAR);
		if ((dateNais.get(Calendar.MONTH) > maintenant.get(Calendar.MONTH)) || (dateNais.get(Calendar.MONTH) == maintenant.get(Calendar.MONTH) && dateNais.get(Calendar.DAY_OF_MONTH) > maintenant.get(Calendar.DAY_OF_MONTH))) {
			age--;
		}
		return age;
	}

	public static int nbMois(Date d1, Date d2) {
		GregorianCalendar dd1 = new GregorianCalendar();
		dd1.setTime(d1);
		GregorianCalendar dd2 = new GregorianCalendar();
		dd2.setTime(d2);

		int yearFut = dd1.get(Calendar.YEAR);
		int yearPast = dd2.get(Calendar.YEAR);

		int nbMois = dd1.get(Calendar.MONTH) - dd2.get(Calendar.MONTH) + 1 + (yearFut - yearPast) * 12;
		return nbMois;
	}

	public static Date firstDateFromYearMonth(int year, int month) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date lastDateFromYearMonth(int year, int month) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	
	public static String getMois(int mois) {

		switch (mois) {
			case 0:
				return "Janvier";
			case 1:
				return "Fevrier";
			case 2:
				return "Mars";
			case 3:
				return "Avril";
			case 4:
				return "Mai";
			case 5:
				return "Juin";
			case 6:
				return "Juillet";
			case 7:
				return "Août";
			case 8:
				return "Septembre";
			case 9:
				return "Octobre";
			case 10:
				return "Novembre";
			case 11:
				return "Decembre";
			default:
				return "";
		}
	}
	
}

