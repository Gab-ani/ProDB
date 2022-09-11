package application.parsing;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.data.Match;

@Service
public class ParsingService {

	@Autowired
	private MatchSniffer matchDataFetcher;			// takes match id, returns Match object
	@Autowired
	private MatchSelector idProvider;				// provides a list of numbers that definitely are ids of recent played matches
	
	private ArrayList<Long> omitted;				// contains match ids that hadn't been fetched on previous cycles for some reason (connection wise)
	
	@PostConstruct
	public void init() {
		omitted = new ArrayList<>();
	}
	
	public ArrayList<Match> fetchData() {
		System.out.println("размер остатков: " + omitted.size());
		var idsToFetch =  idProvider.suggestIds();
		if(!omitted.isEmpty())
			idsToFetch.addAll(omitted);					// add previous ids then clear omitted to contain this cycle leftover
		omitted.clear();
		
		var	output = new ArrayList<Match>();
		
		for (Long matchId : idsToFetch) {
			Match match = matchDataFetcher.formById(matchId);
			if(match != null) {
				output.add(match);
			} else {
				omitted.add(matchId);
			}
		}
		return output;
		
	}
	
}
