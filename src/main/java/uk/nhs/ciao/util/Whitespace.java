package uk.nhs.ciao.util;

import java.util.regex.Pattern;

/**
 * Class for dealing with whitespace in CharSequences
 */
public class Whitespace {
	private static final Pattern MULTIPLE_WHITESPACE = Pattern.compile("\\s+");
	
	/**
	 * @param text
	 * @return text with whitespace collapsed into a single whitespace character
	 */
	public static String collapseWhitespaceAndTrim(final CharSequence text) {
		return collapseWhitespaceAndTrim(text, " ");
	}
	
	/**
	 * @param text
	 * @param replacement
	 * @return text with whitespace replaced and trimmed
	 */
	public static String collapseWhitespaceAndTrim(final CharSequence text, final String replacement) {
		if (text == null) {
			return null;
		} else if (text.length() == 0){
			return text.toString();
		}
		
		return collapseWhitespace(text, replacement).trim();
	}
	
	/*
	public static String collapseWhitespace(final CharSequence text) {
		return collapseWhitespace(text);
	}*/
	
	/**
	 * @param text
	 * @param replacement
	 * @return text with whitespace replaced
	 */
	public static String collapseWhitespace(final CharSequence text, final String replacement) {
		if (text == null) {
			return null;
		} else if (text.length() == 0){
			return text.toString();
		}
		
		return MULTIPLE_WHITESPACE.matcher(text).replaceAll(replacement);
	}
}
