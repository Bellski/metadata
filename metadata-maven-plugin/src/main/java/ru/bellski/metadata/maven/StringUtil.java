package ru.bellski.metadata.maven;

import java.beans.Introspector;

/**
 * Created by oem on 6/30/16.
 */
public class StringUtil {

	public static String capitalize(String s) {
		if (s.isEmpty()) return s;
		if (s.length() == 1) return toUpperCase(s).toString();

		// Optimization
		if (Character.isUpperCase(s.charAt(0))) return s;
		return toUpperCase(s.charAt(0)) + s.substring(1);
	}

	public static String decapitalize(String s) {
		return Introspector.decapitalize(s);
	}


	public static CharSequence toUpperCase(CharSequence s) {
		StringBuilder answer = null;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			char upCased = toUpperCase(c);
			if (answer == null && upCased != c) {
				answer = new StringBuilder(s.length());
				answer.append(s.subSequence(0, i));
			}

			if (answer != null) {
				answer.append(upCased);
			}
		}

		return answer == null ? s : answer;
	}

	public static char toUpperCase(char a) {
		if (a < 'a') {
			return a;
		}
		if (a <= 'z') {
			return (char)(a + ('A' - 'a'));
		}
		return Character.toUpperCase(a);
	}
}
