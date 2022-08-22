package application.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchHistory {

	@Autowired
	MatchRepository matchesDB;
	
	public void add(Match match) {
		matchesDB.save(match);
	}
	
	public void addAll(Iterable<Match> collection) {
		matchesDB.saveAll(collection);
	}
	
	public List<Match> findAll() {
		return matchesDB.findAll();
	}
}
