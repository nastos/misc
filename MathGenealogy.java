package org.jsoup.examples;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Example program to list links from a URL.
 */
public class MathGenealogy {
    public static void main(String[] args) throws IOException {
        //Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        //String url = args[0];
        
    	
    	String url = "https://www.genealogy.math.ndsu.nodak.edu/id.php?id="; // needs ID number appended at end
    	int myID = 197572; // my ID
    	int yongID = 157631; // yong's ID
    	int joeID = 67018; // Joe Culberson's ID
    	
    	int currID = myID;
    	while(true) {
        	print("Fetching %s...", (url+currID));
            Document doc = Jsoup.connect(url+currID).get();
            System.out.println(doc.body().getElementById("mainContent").select("h2").text());
            String adv = getGenealogyAdvisorName(doc);
            System.out.println("Advisor = " + adv);
            int idNum = getGenealogyAdvisorID(doc,adv);
            System.out.println("Advisor ID# = " + idNum +"\n");
    		currID = idNum;
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	

        
        //System.out.println(doc);
        
        //Elements links = doc.select("a[href]");

        // gets person page
        
        //System.out.println(doc.body().getElementById("mainContent"));
        
        
        
        /*
        BufferedReader bufReader = new BufferedReader(new StringReader(doc.toString()));
        String line=null;
        while( (line=bufReader.readLine()) != null )
        {
        	//if (line.contains("a href") && line.contains("id="))
        	if (line.contains("<title>")) {
        		int dash = line.indexOf('-');
        		String name = line.substring(dash+1);
        		System.out.println(name);
        	}
        }
        
        //Elements media = doc.select("[src]");
        //Elements imports = doc.select("link[href]");

        /*
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
		*/
        
        
    }

    private static String getGenealogyAdvisorName(Document doc) {
    	Elements links = doc.select("a[href]");
    	for (Element e : links) {
    		String line = e.toString();
        	if (line.contains("id=")) {
        		int left = 1+line.indexOf('>');
        		return line.substring(left, line.length()-4);
        	}
        }
    	return null;
	}

    private static int getGenealogyAdvisorID(Document doc, String s) {
    	Elements links = doc.select("a[href]");
    	for (Element e : links) {
    		String line = e.toString();
        	if (line.contains(s)) {
        		int left = 3+line.indexOf("id=");
        		int right = line.indexOf("\">");
        		return Integer.parseInt(line.substring(left,right));
        	}
        }
    	return -1;
	}
    
	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}