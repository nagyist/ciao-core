package uk.nhs.ciao.util;

import java.util.regex.Pattern;

public class Whitespace {
	private static final Pattern MULTIPLE_WHITESPACE = Pattern.compile("\\s+");
	
	public static String collapseWhitespace(final String text) {
		return collapseWhitespace(text, " ");
	}
	
	public static String collapseWhitespace(final String text, final String replacement) {
		if (text == null || text.isEmpty()) {
			return text;
		}
		
		return MULTIPLE_WHITESPACE.matcher(text).replaceAll(replacement).trim();
	}
}
