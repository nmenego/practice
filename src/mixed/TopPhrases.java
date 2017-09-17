import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author nmenego
 *
 * Problem Statement:
 * Given a large file that does not fit in memory (say 10GB), find the top 100000 most frequent phrases. The file has 50 phrases per line  
 * separated by a pipe (|). Assume that the phrases do not contain pipe. Example line may look like: Foobar Candy | Olympics 2012 | PGA |  
 * CNET | Microsoft Bing .... The above line has 5 phrases in visible region.
 */
public class TopPhrases {

	private static final int TOP_LIMIT = 100000;
	private static final int ONE = 1;

	public static void main(String[] args) throws Exception {
		System.out.println("-- Top Phrases");

		TopPhrases topPhrases = new TopPhrases("sample.txt");

		Map<String, Integer> top100kPhrases = null;
		try {
			top100kPhrases = topPhrases.getTop100kPhrases();
			// print results of sorted hashmap
			int i = 1;
			for (String key : top100kPhrases.keySet()) {
				System.out.printf("Rank %s: %s => %s\n", i++, key, top100kPhrases.get(key));
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	private String fileName;
	public TopPhrases(String fileName) {
		this.fileName = fileName;
	}

	// This is just a simplistic solution given the short amount of time.
	// Another possible solution I am considering:
	// I think performance can be significantly improved if we break down the big file into smaller chunks (takes time)
	//  then allow multiple threads to handle the chunks, this of course would need extra steps
	//  as we have to merge the results from the different threads.
	// One disadvantage of this proposed solution is that accessing the smaller chunks will then require
	//  harddisk head to move to and from different segments of the disk when another thread processes its file.
	public Map<String, Integer> getTop100kPhrases() throws IOException {
		// to take advantage of O(1) access we use a hashmap
		Map<String, Integer> phraseMap = new LinkedHashMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

		String line = "";
		while ((line = br.readLine()) != null) {

			String[] phrases = line.split("\\|");
			for (String phrase : phrases) {
				phrase = phrase.trim();
				if (phraseMap.containsKey(phrase)) {
					phraseMap.put(phrase, phraseMap.get(phrase) + 1);
				} else {
					phraseMap.put(phrase, 1);
				}
			}
		}

		// sorting map in Java 8
		return phraseMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(100000)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1,v2)->v1, LinkedHashMap::new));

	}

}