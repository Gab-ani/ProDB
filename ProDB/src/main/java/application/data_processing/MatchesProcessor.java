package application.data_processing;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import application.data.Match;

@Service
public class MatchesProcessor {

	private List<Match> history;
	
	public List<Match> selectByHero(int heroId) {
		List<Match> subHistory = new ArrayList<>();
		for(Match match : history) {
			if(match.played(heroId)) {
				subHistory.add(match);
			}
		}
		return subHistory;
	}
	
	public List<Match> selectByEnemy(int heroId, int enemyId) {
		List<Match> subHistory = new ArrayList<>();
		for(Match match : history) {
			if(match.wereOpposed(heroId, enemyId)) {
				subHistory.add(match);
			}
		}
		return subHistory;
	}
	
	public List<Match> selectByAlly(int heroId, int allyId) {
		List<Match> subHistory = new ArrayList<>();
		for(Match match : history) {
			if(match.teamedUp(heroId, allyId)) {
				subHistory.add(match);
			}
		}
		return subHistory;
	}
	
	public double winrate(List<Match> subHistory, int heroId) {
		double wins = 0;
		double games = 0;
		for(Match match : subHistory) {
			games += 1;
			if(match.heroWon(heroId))
				wins += 1;
		}
		if(games == 0)
			return -1;
		return wins / games;
	}
	
	public double calculateAgainst(int forHero, int againstHero) {
//		System.out.println(selectByEnemy(forHero, againstHero).size() + " матчей " + forHero + " " + againstHero);
		return winrate( selectByEnemy(forHero, againstHero) , forHero ) - baseWinrate(forHero);
	}
	
	public double calculateWith(int forHero, int withHero) {
//		System.out.println(selectByAlly(forHero, withHero).size() + " матчей " + forHero + " " + withHero);
		return winrate( selectByAlly(forHero, withHero) , forHero ) - baseWinrate(forHero);
	}
	
	public void setData(List<Match> history) {
		this.history = history;
	}

	public double baseWinrate(int heroId) {
		return winrate(selectByHero(heroId), heroId);
	}
	
}
