package com.monetique.um.utils;

/**
 * @author bpm digitale
 *
 */

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class UtilString {

	/** Creates a new instance of UtilString */
	private UtilString() {
	}

	/**
	 * Returns <tt>true</tt> if the array contains the specified element.
	 * 
	 * @param toSearch
	 *            element whose presence in the array is to be tested.
	 * @param tab
	 *            where to search.
	 * @return <code>true</code> if the specified element is present;
	 *         <code>false</code> otherwise.
	 */
	public static boolean contains(String toSearch, String[] tab) {
		return UtilString.indexOf(toSearch, tab) >= 0;
	}

	/**
	 * Searches for the first occurence of the given argument, testing for
	 * equality using the <tt>equals</tt> method.
	 * 
	 * @param toSearch
	 *            a String.
	 * @param tab
	 *            where to search.
	 * @return the index of the first occurrence of the argument in this list;
	 *         returns <tt>-1</tt> if the object is not found.
	 * @see Object#equals(Object)
	 */
	public static int indexOf(String toSearch, String[] tab) {
		if (toSearch != null) {
			int size = tab.length;
			for (int i = 0; i < size; i++)
				if (toSearch.equals(tab[i]))
					return i;
		}
		return -1;
	}

	public static String[] remove(String[] src, int index) {
		String[] tab;
		int srcSize = src.length;
		if ((index >= 0) && (index < srcSize)) {
			tab = new String[srcSize - 1];
			System.arraycopy(src, 0, tab, 0, index);
			System.arraycopy(src, index + 1, tab, index, srcSize - index);
		}
		else {
			tab = new String[srcSize];
			System.arraycopy(src, 0, tab, 0, srcSize);
		}
		return tab;
	}

	public static String[] remove(String[] src, String toRemove) {
		int srcSize = src.length;
		String[] tab = new String[srcSize];
		System.arraycopy(src, 0, tab, 0, srcSize);
		int index;
		while ((index = UtilString.indexOf(toRemove, tab)) != -1) {
			tab = UtilString.remove(tab, index);
		}
		return tab;
	}

	public static String[] removeAll(String[] src, List<String> toRemove) {
		int srcSize = src.length;
		String[] tab = new String[srcSize];
		System.arraycopy(src, 0, tab, 0, srcSize);
		int remSize = toRemove.size();
		for (int i = 0; i < remSize; i++)
			tab = UtilString.remove(tab, (String) toRemove.get(i));
		return tab;
	}

	@SuppressWarnings("rawtypes")
	public static String dump(Map map) {
		String ret = null;
		try {
			Set entries = map.entrySet();
			Iterator it = entries.iterator();
			while (it.hasNext()) {
				Object entry = it.next();
				ret = ret + ", " + entry;
			}
		}
		catch (Exception ex) {
		}
		return ret;
	}

	public List<String> getSplitString(String s) {
		if (s == null || s.trim().length() == 0)
			return null;
		StringTokenizer st = new StringTokenizer(s, " ,:;");
		List<String> ls = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			ls.add(st.nextToken());
		}
		return ls;
	}

	/**
	 * Duplique les simple quote a l'int�rieur d'une String pour les requetes
	 * SQL : 'L'OREAL' est incorrect, mais 'L''OREAL' l'est<br>
	 * Les carat�res & sont remplac� par \&<br>
	 * 
	 * @param strIN
	 *            la chaine a modifier
	 * @return String la chaine modifi�e
	 */
	public static String toSQLStringQuoteAnd(String strIn) {
		// Nombre de quote ds la string
		int nbQuote = 0;
		int nbAnd = 0;
		if (strIn != null) {
			int sizeIn = strIn.length();
			// Teste d'abord si il y a des qoutes dans la chaine
			for (int i = 0; i < sizeIn; i++) {
				char c = strIn.charAt(i);
				switch (c) {
					case '\'':
						nbQuote++;
						break;
					case '&':
						nbAnd++;
						break;
				}
			}
			if ((nbQuote == 0) && (nbAnd == 0))
				// Il n'y a pas de quote ou &, on retourne la chaine d'entree
				return strIn;
			else {
				int sizeOut = sizeIn + nbQuote + nbAnd;
				char[] tabOut = new char[sizeOut];
				int indexIn = 0;
				int indexOut = 0;
				for (indexIn = 0; indexIn < strIn.length(); indexIn++) {
					char c = strIn.charAt(indexIn);
					switch (c) {
						case '\'':
							tabOut[indexOut] = c;
							indexOut++;
							tabOut[indexOut] = c;
							break;
						case '&':
							tabOut[indexOut] = '\\';
							indexOut++;
							tabOut[indexOut] = c;
							break;
						default:
							tabOut[indexOut] = c;
							break;
					}
					indexOut++;
				}
				return new String(tabOut);
			}
		}

		return null;
	}

	/**
	 * Duplique les simple quote a l'int�rieur d'une String pour les requetes
	 * SQL : 'L'OREAL' est incorrect, mais 'L''OREAL' l'est
	 * 
	 * @param strIN
	 *            la chaine a modifier
	 * @return String la chaine modifi�e
	 */
	public static String toSQLString(String strIn) {
		// Nombre de quote ds la string
		int nbQuote = 0;

		if (strIn != null) {
			int sizeIn = strIn.length();
			// Teste d'abord si il y a des qoutes dans la chaine
			for (int i = 0; i < sizeIn; i++) {
				if (strIn.charAt(i) == '\'')
					nbQuote++;
			}
			if (nbQuote == 0)
				// Il n'y a pas de quote, on retourne la chaine d'entree
				return strIn;
			else {
				int sizeOut = sizeIn + nbQuote;
				char[] tabOut = new char[sizeOut];
				int indexIn = 0;
				int indexOut = 0;
				for (indexIn = 0; indexIn < strIn.length(); indexIn++) {
					char c = strIn.charAt(indexIn);
					switch (c) {
						case '\'':
							tabOut[indexOut] = c;
							indexOut++;
							tabOut[indexOut] = c;
							break;
						default:
							tabOut[indexOut] = c;
							break;
					}
					indexOut++;
				}
				return new String(tabOut);
			}
		}
		return null;
	}

	/**
	 * D�termine si 2 cha�nes de caract�res sont �gales (ie correspondent
	 * exactement � la m�me cha�ne de caract�res)
	 */
	public static boolean equal(String s1, String s2) {
		if (s1 == null)
			return (s2 == null);
		if (s2 == null)
			return false;
		return (s1.compareTo(s2) == 0);
	}

	/**
	 * Determine si une String est non null et non vide
	 * 
	 * @param s
	 * @return (s != null) && (s.length() > 0)
	 */
	public static boolean isCorrect(String s) {
		return ((s != null) && (s.length() > 0) && (s.trim().length() > 0));
	}

	public static boolean isCorrect(Double s) {
		return ((s != null));
	}

	/**
	 * D�termine si 2 cha�nes de caract�res sont �gales (ie correspondent
	 * exactement � la m�me cha�ne de caract�res)
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compareAsInteger(String s1, String s2) {
		if (!isCorrect(s1)) {
			if (isCorrect(s2))
				return Integer.MAX_VALUE;
			else
				return 0;
		}
		if (!isCorrect(s2)) {
			if (isCorrect(s1))
				return Integer.MIN_VALUE;
			else
				return 0;
		}
		try {
			int i1 = Integer.parseInt(s1);
			int i2 = Integer.parseInt(s2);
			if (i1 > i2)
				return 1;
			else if (i1 == i2)
				return 0;
			else
				return -1;
		}
		catch (Exception e) {
			// Xystem.err.println(e.getClass().getName()+" : "+s1+".compareTo "+s2);
			return s1.compareTo(s2);
		}
	}

	/**
	 * D�termine si 2 cha�nes de caract�res sont �gales (ie correspondent
	 * exactement � la m�me cha�ne de caract�res)
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compareAsDouble(String s1, String s2) {
		if (!isCorrect(s1)) {
			if (isCorrect(s2))
				return Integer.MAX_VALUE;
			else
				return 0;
		}
		if (!isCorrect(s2)) {
			if (isCorrect(s1))
				return Integer.MIN_VALUE;
			else
				return 0;
		}
		try {
			Double d1 = new Double(s1);
			Double d2 = new Double(s2);
			return d1.compareTo(d2);
		}
		catch (Exception e) {
			return s1.compareTo(s2);
		}
	}

	/**
	 * Determine le nombre de caractere d'une String multiligne. Le nombre de
	 * caractere est celui de la plus grande ligne.
	 * 
	 * @param str
	 *            la String
	 * @return int la largeur de la plus grande ligne
	 */
	public static int getMultiLineStringWidth(String str) {
		int startOfSubString = 0;
		int endOfSubString = 0;
		int ret = -1;
		String subString = null;

		if (str == null)
			return 0;
		endOfSubString = str.indexOf('\n', startOfSubString);
		if (endOfSubString == -1)
			// une seule ligne
			return str.length();
		else
			while (startOfSubString < str.length()) {
				endOfSubString = str.indexOf('\n', startOfSubString);
				if (endOfSubString == -1) {
					// on a atteint la fin de la String
					subString = str.substring(startOfSubString, str.length());
					ret = Math.max(ret, subString.length());
					break;
				}
				subString = str.substring(startOfSubString, endOfSubString);
				ret = Math.max(ret, subString.length());
				startOfSubString = endOfSubString + 1;
			}
		return ret;
	}

	public static String nomaliser(String champ, boolean delAccent, boolean delEspace, boolean lowercase) {
		String s = new String(champ);
		if (delAccent)
			s = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
		if (delEspace)
			s = s.replaceAll(" ", "");
		if (lowercase)
			s = s.toLowerCase();
		return s;
	}
}