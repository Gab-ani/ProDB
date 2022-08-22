package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import application.data.MatchHistory;
import application.parsing.ParsingService;

@Service
public class ProMatchesFlowSaver {

	@Autowired
	ParsingService parser;
	@Autowired
	MatchHistory history;

	@Scheduled(cron = "0 0 * * * *")
	public void savePortion() {
		history.addAll(parser.fetchData());
	}
	
}
