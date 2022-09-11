package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.data.Hero;
import application.data.HeroesService;

@Service
public class MatchAnalyser {

	@Autowired
	private MatchupService matchupData;
	@Autowired
	private HeroesService heroes;
	
	// TODO make enumeration maybe?
	public String predict(Hero[] dire, Hero[] radiant) {
		
		double direCombined = teamSynergy(dire) + advantage(dire, radiant);
		double radiantCombined = teamSynergy(radiant) + advantage(radiant, dire);
		
		System.out.println(radiantCombined + " " + direCombined);
		
		if(radiantCombined > direCombined)
			return "radiant";
		
		return "dire";
	}
	
	private double advantage(Hero[] team, Hero[] over) {
		double advantage = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				advantage += matchupData.againstEnemy(team[i].getId(), over[j].getId());
			}
		}
		return advantage;
	}

	private double teamSynergy(Hero[] team) {
		double synergy = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(j == i)
					continue;
				synergy += matchupData.withAlly(team[i].getId(), team[j].getId());
			}
		}
		return synergy;
	}

	
	// rewrite this abomination to heroservice methods using builder-like syntax
	public Hero[] createTeam(String name1, String name2, String name3, String name4, String name5) {
		var team = new Hero[5];
		team[0] = heroes.getByNickname(name1);
		team[1] = heroes.getByNickname(name2);
		team[2] = heroes.getByNickname(name3);
		team[3] = heroes.getByNickname(name4);
		team[4] = heroes.getByNickname(name5);
		return team;
	}
	
	
	
}
