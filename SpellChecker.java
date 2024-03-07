
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
		
	}

	public static String tail(String str) {
		// Your code goes here
		String newS = "";
		if (str.length() == 1) {
			return "";
		}
		for (int i = 1; i < str.length(); i++) {
			newS += str.charAt(i);
		}
		return newS;
	}
	public static int levenshtein(String word1, String word2) {
		if (word1.length() == 0) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		}
		int a = levenshtein(tail(word1), word2);
		int b = levenshtein(word1, tail(word2));
		int c = levenshtein(tail(word1), tail(word2));
		return 1 + Math.min(Math.min(a, b), c);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for (int i = 0; i < 3000; i++) {
			String s = in.readString();
			dictionary[i] = s;
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String newW = word.toLowerCase();
		int min = 1000;
		String save = "";
		for (int i = 0; i < dictionary.length; i++) {
			if (levenshtein(dictionary[i], newW) < min) {
				min = levenshtein(dictionary[i], newW);
				save = dictionary[i];
			}
		}
		if (min > threshold) {
			return word;
		}
		return save;
	}

}
