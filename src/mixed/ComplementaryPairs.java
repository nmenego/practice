import java.util.*;

/**
 * @author nmenego
 *
 * Problem Statement: 
 * Write an efficient algorithm to find K-complementary pairs in a given array of
 * integers. Given Array A, pair (i, j) is K- complementary if K = A[i] + A[j];
 *
 * Assumptions:
 * 1. i != j
 */
public class ComplementaryPairs {

	public static void main(String[] args) {
		System.out.println("-- K-complementary Pairs");

		printPairs(new ComplementaryPairs(10, new int[] {3, 5, 7}));
		printPairs(new ComplementaryPairs(10, new int[] {3, 5, 5, 7}));
		printPairs(new ComplementaryPairs(100, new int[] {3, 5, 7}));
		printPairs(new ComplementaryPairs(8, new int[] {7, 5, 6, 3, 1, 5, 9, -3, -2}));
		printPairs(new ComplementaryPairs(10, new int[] {7, 1, 5, 6, 9, 3, 11, -1}));
	}

	public static void printPairs(ComplementaryPairs complementaryPairs) {
		List<Pair> pairs = complementaryPairs.getPairs();
		System.out.printf("K= %s, array= %s\n", complementaryPairs.getK(), Arrays.toString(complementaryPairs.getNumbers()));
		System.out.println("number of pairs: " + pairs.size());
		System.out.print("pairs: ");
		for (int i=0; i < pairs.size(); i++) {
			System.out.print(pairs.get(i).toString());
			if (i < pairs.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	private int k;
	private int[] numbers;

	public ComplementaryPairs(int k, int[] numbers) {
		if (numbers == null || numbers.length < 2) {
			// have to have at least 2 numbers in array
			throw new IllegalArgumentException("Invalid input");
		}
		this.k = k;
		this.numbers = numbers;
	}

	public List<Pair> getPairs() {
		List<Pair> pairs = new ArrayList<>();
		// store it in hash collection to take advantage of linear-time searching of hash
		// key will be the difference from k, value will be number of occurences
		Map<Integer, Integer> hash = new HashMap<>();
		for (Integer number: numbers) {
			if (hash.containsKey(number)) hash.put(number, hash.get(number) + 1);
			else hash.put(number, 1);
		}

		// search for the difference from the original array (looking for the pair)
		// if found, add to pairs list
		// this is O(n) since we are using hashmap
		for (Integer number: numbers) {
			int diff = k - number;
			if (hash.containsKey(diff)) {
				int occurences = hash.get(diff);

				// add pairs according to number of occurences
				for (int i=0; i < occurences; i++) {
					if (diff == number && i == 0) continue; // assumption i != j
					pairs.add(new Pair(number, diff));
				}
			}
		}

		return pairs;
	}

	public int getK() {
		return k;
	}

	public int[] getNumbers() {
		return numbers;
	}

	private class Pair {
		private int i;
		private int j;

		Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public String toString() {
			return String.format("<%s, %s>", i, j);
		}
	}
}