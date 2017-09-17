/**
 * @author nmenego
 *
 * Problem Statement: 
 * Write an efficient algorithm to check if a string is a palindrome. A string is a
 * palindrome if the string matches the reverse of string.
 * Example: 1221 is a palindrome but not 1121.
 */
public class Palindrome {

	public static void main(String[] args) {
		System.out.println("-- Palindrome Checker");
		if(args == null || args.length < 1 || args[0].length() == 0) {
			throw new IllegalArgumentException("Please input the string to check.");
		}

		System.out.println("String to check: " + args[0]);
		System.out.printf("result: %s!", isPalindrome(args[0]) ? "PALINDROME" : "NOT A PALINDROME");
	}

	public static boolean isPalindrome(String word){
		word = word.toLowerCase(); // ignoring case
		// this is O(n/2).. essentially, O(n)
		for (int i = 0, j = word.length() - 1; i < j; i++, j--) {
			if (word.charAt(i) != word.charAt(j)) {
				return false;
			}
		}
		return true;
	}

}