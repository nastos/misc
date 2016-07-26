import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Words {

	static int trivialSize = 0;
	static ArrayList<String[]> edges = new ArrayList<String[]>();
	
	public static void main(String[] args) throws FileNotFoundException {
	
		//File f = new File("ulysses.txt");
//		File f = new File("MsObamaSpeech.txt");
		File f = new File("TrumpSpeech.txt");
			
		//makeTGFFromText(f);
		wordCounts(f);

	}

	public static void wordCounts (File f) throws FileNotFoundException {

		Scanner s = new Scanner(f,"UTF-8");
		Map<String,Integer> counts = new HashMap<String,Integer>();

		while (s.hasNext()) {
			String st = s.nextLine();
			String[] lineOfWords = st.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
			for (String i : lineOfWords) {
				if (i.length() <= trivialSize ) continue;
				if (counts.containsKey(i)) counts.put(i, counts.get(i)+1);
				else counts.put(i,1);
			}
		}

		ArrayList<Map.Entry<String,Integer>> words = (ArrayList<Entry<String, Integer>>) MapSortByVal.getMapSortedByValue(counts);
		
		for (Map.Entry<String,Integer> i : words) {
			System.out.println(i.getKey()+" " + i.getKey().length() + " " + i.getValue());
		}
	}
	
	public static void makeTGFFromText (File f) throws FileNotFoundException {
		Scanner s = new Scanner(f,"UTF-8");
		Map<String,Integer> counts = new HashMap<String,Integer>();
		String prev = "";
		while (s.hasNext()) {
			String st = s.nextLine();
			String[] lineOfWords = st.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
			for (String i : lineOfWords) {
				i = i.trim();
				if (i.length() <= trivialSize ) continue;
				if (counts.containsKey(i)) counts.put(i, counts.get(i)+1);
				else counts.put(i,1);
				if (prev != "" && i != "" && prev !=null && i != null) {
					String[] edge = new String[2];
					edge[0]=prev; edge[1]=i;
					edges.add(edge);
				}
				prev = i;
			}
		}
		
//		for (String word : counts.keySet()) {
//			System.out.println(word + " " + counts.get(word));
//		}
		System.out.println("#");
		for (String[] edge : edges) {
			System.out.println(edge[0] + " " + edge[1]);
		}
		
	}
	
}
