package application.parsing;

import application.data.Match;
import application.data.OfficialMatch;

public interface MatchSniffer {

	public Match formById(Long matchId);
	
	public OfficialMatch proById(Long matchId);
	
}
