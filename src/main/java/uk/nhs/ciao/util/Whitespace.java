package uk.nhs.ciao.util;

import java.util.regex.Pattern;

public class Whitespace {
	private static final Pattern MULTIPLE_WHITESPACE = Pattern.compile("\\s+");
	
	public static String collapseWhitespaceAndTrim(final CharSequence text) {
		return collapseWhitespaceAndTrim(text, " ");
	}
	
	public static String collapseWhitespaceAndTrim(final CharSequence text, final String replacement) {
		if (text == null) {
			return null;
		} else if (text.length() == 0){
			return text.toString();
		}
		
		return collapseWhitespace(text, replacement).trim();
	}
	
	public static String collapseWhitespace(final CharSequence text) {
		return collapseWhitespace(text);
	}
	
	public static String collapseWhitespace(final CharSequence text, final String replacement) {
		if (text == null) {
			return null;
		} else if (text.length() == 0){
			return text.toString();
		}
		
		return MULTIPLE_WHITESPACE.matcher(text).replaceAll(replacement);
	}
}
