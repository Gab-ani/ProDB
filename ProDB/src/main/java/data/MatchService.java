package data;

import org.springframework.stereotype.Service;

@Service
public class MatchService {

	MatchRepository matchesDB;
	
	public void add(Match match) {
		matchesDB.save(match);
	}
	
	public void addAll(Iterable<Match> collection) {
		matchesDB.saveAll(collection);
	}
	
	public int[] verifyAbsence(int[] ids) {				// takes array of match ids, returns an array of only ids of matches that aren't saved in db
		//TODO
	}
	
}
