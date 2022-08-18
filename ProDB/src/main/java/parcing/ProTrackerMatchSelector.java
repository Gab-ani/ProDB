package parcing;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.h2.store.fs.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProTrackerMatchSelector implements MatchSelector {

	public ArrayList<Long> suggestIds() {
		
		ArrayList<Long> ids = new ArrayList<>();
		
		try (WebClient client = new WebClient()) {
			HtmlPage page = client.getPage("https://www.dota2protracker.com");
			Document doc = Jsoup.parse(page.asXml());
			
			Elements td = doc.select("a[href]");
			for (Element row : td) {
				if(row.attr("href").contains("stratz") && !row.attr("href").contains("live")) {
					String matchReference = row.attr("href").replaceAll("https://stratz.com", "");
					matchReference = matchReference.replaceAll("/match/", "");
					if(matchReference.length() > 1)
						ids.add(Long.parseLong(matchReference));
				}
			}
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		
		for(Long id : ids)
			System.out.println(id);
		
		return ids;
	}

}
