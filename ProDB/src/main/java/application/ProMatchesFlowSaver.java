package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.MatchHistory;
import parcing.ParsingService;

@Service
public class ProMatchesFlowSaver {

	@Autowired
	ParsingService parser;
	@Autowired
	MatchHistory history;
	
	//TODO probably make sheduled for like 5 hours
	public void savePortion() {
		history.addAll(parser.fetchData());
	}
	
}
