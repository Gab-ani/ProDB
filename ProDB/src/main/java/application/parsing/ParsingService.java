package application.parsing;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.data.Match;
import application.data.OfficialMatch;

@Service
public class ParsingService {

	@Autowired
	private MatchSniffer matchDataFetcher;			// takes match id, returns Match object
	@Autowired
	private MatchSelector idProvider;				// provides a list of numbers that definitely are ids of recent played matches

	
	public OfficialMatch fetchOfficialMatch(long id) {
		return matchDataFetcher.proById(id);
	}
	
	public ArrayList<Match> updateUnparsed(List<Match> blankMatches) {
		System.out.println("в базе " + blankMatches.size() + " пустых матчей");
		
		var	output = new ArrayList<Match>();
		
		for (Match blankMatch : blankMatches) {
			Match match = matchDataFetcher.formById(blankMatch.getId());
			if(match != null) {
				output.add(match);
			}
		}
		
		System.out.println("удалось обновить " + output.size() + " матчей");
		return output;
		
	}

	public ArrayList<Match> createNewBlankMatches() {
		System.out.println("запрашиваю болванки матчей");
		var idsToFetch =  idProvider.suggestIds();
		var	output = new ArrayList<Match>();
		for (Long matchId : idsToFetch) {
			Match match = Match.createEmpty(matchId);
			output.add(match);
		}
		return output;
		
	}
	
}
