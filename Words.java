import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Words {

	public static void main(String[] args) throws FileNotFoundException {
	
		File f = new File("ulysses.txt");
		Scanner s = new Scanner(f);
		
		int trivialSize = 4;
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
		
		System.out.println(words);

	}

}
