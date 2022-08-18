package parcing;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.Match;

@Service
public class ParsingService {

	@Autowired
	MatchSniffer matchDataFetcher;			// takes match id, returns Match object
	@Autowired
	MatchSelector idProvider;				// provides a list of numbers that definitely are ids of recent played matches
	
	public ArrayList<Match> fetchData() {
		
		var	output = new ArrayList<Match>();
		for (int matchId : idProvider.suggestIds()) {
			output.add(matchDataFetcher.formById(matchId));
		}
		return output;
		
	}
	
}
