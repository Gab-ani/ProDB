package application.data_processing;

import java.util.ArrayList;
import java.util.List;

import application.data.Match;

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
		int wins = 0;
		int games = 0;
		for(Match match : subHistory) {
			games += 1;
			if(match.heroWon(heroId))
				wins += 1;
		}
		return wins / games;
	}
	
	public double calculateAgainst(int forHero, int againstHero) {
		return winrate( selectByEnemy(forHero, againstHero) , forHero ) - baseWinrate(forHero);
	}
	
	public double calculateWith(int forHero, int againstHero) {
		return winrate( selectByAlly(forHero, againstHero) , forHero ) - baseWinrate(forHero);
	}
	
	public void setData(List<Match> history) {
		this.history = history;
	}

	public double baseWinrate(int heroId) {
		return winrate(selectByHero(heroId), heroId);
	}
	
}
