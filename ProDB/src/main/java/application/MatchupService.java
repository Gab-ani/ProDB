package application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.data.HeroesService;
import application.data.MatchHistory;
import application.data_processing.HeroMatchup;
import application.data_processing.MatchesProcessor;
import application.data_processing.MatchupRepository;

@Service
public class MatchupService {

	@Autowired
	MatchupRepository matchups;
	@Autowired
	MatchesProcessor processor;
	@Autowired
	MatchHistory history;
	@Autowired
	HeroesService heroes;
	
	public void updateAll() {
		actualiseHistory();
		for(int heroId : heroes.getAllIds()) {
			updateHeroMatchups(heroId);
		}
	}
	
	//TODO maybe async
	private void updateHeroMatchups(int forHero) {
		List<Integer> allHeroes = heroes.getAllIds();
		for(int withHero : allHeroes) {
			if(withHero == forHero)
				continue;
			HeroMatchup matchup = new HeroMatchup(forHero, withHero, processor.calculateWith(forHero, withHero), processor.calculateAgainst(forHero, withHero));
			matchups.save(matchup);
		}
	}
	
	public void save(HeroMatchup matchup) {
		matchups.save(matchup);
	}
	
	private void actualiseHistory() {
		processor.setData(history.findAll());
	}
}
