package org.jsoup.examples;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Example program to list links from a URL.
 */
public class WikiLeaksDNC {
	public static void main(String[] args) throws Exception {
		//Validate.isTrue(args.length == 1, "usage: supply url to fetch");
		//String url = args[0];

		ArrayList<String> emailAddresses = new ArrayList<String>();
		emailAddresses.add("mirandal@dnc.org");
		emailAddresses.add("comers@dnc.org");
		emailAddresses.add("kaplanj@dnc.org");
		emailAddresses.add("paustenbachm@dnc.org");
		emailAddresses.add("manriquezp@dnc.org");
		emailAddresses.add("parrishd@dnc.org");
		emailAddresses.add("noreply@messages.whitehouse.gov");
		emailAddresses.add("walkere@dnc.org");
		emailAddresses.add("palermor@dnc.org");
		emailAddresses.add("garciaw@dnc.org");
		emailAddresses.add("SchmuckB@gmail.com");
		emailAddresses.add("lcobey@gmail.com");
		emailAddresses.add("RLouijeune@perkinscoie.com");
		emailAddresses.add("Angelica_P_Allen@who.eop.gov");
		emailAddresses.add("zallen@tipahconsulting.com");
		emailAddresses.add("atobias123@gmail.com");
		emailAddresses.add("lindsay@skyadvisorygroup.com");
		emailAddresses.add("design@democraticnationalcommittee.zendesk.com");
		emailAddresses.add("jordandavidkaplan@gmail.com");
		emailAddresses.add("myvastfortune@aol.com");
		emailAddresses.add("courtney@dwsforcongress.com");
		emailAddresses.add("comm_d@dnc.org");
		emailAddresses.add("dncpress@gmail.com");
		emailAddresses.add("allenz@dnc.org");
		emailAddresses.add("shapiroa@dnc.org");
		emailAddresses.add("dncpress@dnc.org");


		setTrustAllCerts();
		for (int i=0; i<emailAddresses.size(); i++) {
			String email1 = emailAddresses.get(i);
			for (int j=0; j<emailAddresses.size(); j++) {
				String email2 = emailAddresses.get(j);
						
				String url = "https://wikileaks.org/dnc-emails/?q=&mfrom="+email1+"&mto="+email2 ; // needs ID number appended at end
				
				//print("Fetching %s...", url);
				Document doc = Jsoup.connect(url).get();
				String[] page = doc.body().toString().split("\n");
				int num = findNumber(page); 
				if (num > 0) System.out.println(email1 +" " + email2 + " " + num);
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}


	private static int findNumber(String[] page) {
		for (int i=0; i<page.length; i++) {
			if (page[i].contains("<h2>")) {
				int n = page[i].indexOf('(');
				String s = page[i].substring(n+1);
				int m = s.indexOf(" ");
				return Integer.parseInt(s.substring(0, m));
			}
		}
		return 0;
	}


	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}


	private static void setTrustAllCerts() throws Exception
	{
		/* taken from SO user CornPuff
		 * http://stackoverflow.com/questions/373518/how-do-i-use-a-local-https-url-in-java
		 */

		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkClientTrusted( java.security.cert.X509Certificate[] certs, String authType ) {	}
					public void checkServerTrusted( java.security.cert.X509Certificate[] certs, String authType ) {	}
				}
		};

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance( "SSL" );
			sc.init( null, trustAllCerts, new java.security.SecureRandom() );
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier( 
					new HostnameVerifier() {
						public boolean verify(String urlHostName, SSLSession session) {
							return true;
						}
					});
		}
		catch ( Exception e ) {
			//We can not recover from this exception.
			e.printStackTrace();
		}
	}

}