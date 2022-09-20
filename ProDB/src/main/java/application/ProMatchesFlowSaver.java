package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import application.data.MatchHistory;
import application.parsing.ParsingService;

@Service
public class ProMatchesFlowSaver {

	@Autowired
	private ParsingService parser;
	@Autowired
	private MatchHistory history;

	@Scheduled(cron = "* 0/30 * * * *")
	public void savePortion() {
		history.addAll(parser.createNewBlankMatches());		// reads proTracker and create a bunch of unparsed matches in history
		history.addAll(parser.updateUnparsed(history.getUnparsed()));
	}
	
	public void saveOfficial(long id) {
		history.add(parser.fetchOfficialMatch(id));
	}
	
}
