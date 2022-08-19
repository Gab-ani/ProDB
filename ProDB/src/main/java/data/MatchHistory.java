package data;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class MatchHistory {

	MatchRepository matchesDB;
	
	public void add(Match match) {
		matchesDB.save(match);
	}
	
	public void addAll(Iterable<Match> collection) {
		matchesDB.saveAll(collection);
	}
	
}
