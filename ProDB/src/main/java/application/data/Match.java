package application.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="history")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Match {

	@Id
	private long matchId;
	 
	private String date;
	
	private String winner;
	
	private String rCarry;
	private String rMid;
	private String rOfflane;
	private String rSoft;
	private String rHard;				
	private String dCarry;
	private String dMid;
	private String dOfflane;
	private String dSoft;
	private String dHard;
	
	private boolean parsed;
	
	public Match() {
		
	}
	
	public static Match createEmpty(long matchId) {
		Match emptyMatch = new Match();
		emptyMatch.matchId = matchId;
		emptyMatch.parsed = false;
		emptyMatch.rCarry = -1 + "";
		emptyMatch.rMid = -1 + "";
		emptyMatch.rOfflane = -1 + "";
		emptyMatch.rSoft = -1 + "";
		emptyMatch.rHard = -1 + "";
		emptyMatch.dCarry = -1 + "";
		emptyMatch.dMid = -1 + "";
		emptyMatch.dOfflane = -1 + "";
		emptyMatch.dSoft = -1 + "";
		emptyMatch.dHard = -1 + "";
		emptyMatch.winner = "tbd";
		emptyMatch.date = "-1";
		return emptyMatch;
	}
	
	public List<String> getPick() {
		var pick = new ArrayList<String>();
		pick.add(rCarry);
		pick.add(rMid);
		pick.add(rOfflane);
		pick.add(rSoft);
		pick.add(rHard);
		pick.add(dCarry);
		pick.add(dMid);
		pick.add(dOfflane);
		pick.add(dSoft);
		pick.add(dHard);
		return pick;
	}
	
	public void radiantCarry(String hero) {		// these setters cycle because stratz API doubledipping roles sometimes
		if(this.rCarry != null) {							
			radiantMid(hero);
			return;
		}
		this.rCarry = hero;
	}
	public void radiantMid(String hero) {
		if(this.rMid != null) {							
			radiantOfflane(hero);
			return;
		}
		this.rMid = hero;
	}
	public void radiantOfflane(String hero) {
		if(this.rOfflane != null) {							
			radiantSoft(hero);
			return;
		}
		this.rOfflane = hero;
	}
	public void radiantSoft(String hero) {					
		if(this.rSoft != null) {							
			radiantHard(hero);
			return;
		}
		this.rSoft = hero;
	}
	public void radiantHard(String hero) {
		if(this.rHard != null) {
			radiantCarry(hero);
			return;
		}
		this.rHard = hero;
	}
	
	public void direCarry(String hero) {
		if(this.dCarry != null) {							
			direMid(hero);
			return;
		}
		this.dCarry = hero;
	}
	public void direMid(String hero) {
		if(this.dMid != null) {							
			direOfflane(hero);
			return;
		}
		this.dMid = hero;
	}
	public void direOfflane(String hero) {
		if(this.dOfflane != null) {							
			direSoft(hero);
			return;
		}
		this.dOfflane = hero;
	}
	public void direSoft(String hero) {
		if(this.dSoft != null) {
			direHard(hero);
			return;
		}
		this.dSoft = hero;
	}
	public void direHard(String hero) {
		if(this.dHard != null) {
			direCarry(hero);
			return;
		}
		this.dHard = hero;
	}

	public void setID(long id) {
		this.matchId = id;		
	}

	public void setDate(String date) {
		this.date = date;		
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("match id: ");
		builder.append(matchId);
		builder.append("\ndate: ");
		builder.append(date + "\n");
		builder.append(winner + " won\n");
		builder.append("radiant side: \n");
		builder.append(rCarry + "\n");
		builder.append(rMid + "\n");
		builder.append(rOfflane + "\n");
		builder.append(rSoft + "\n");
		builder.append(rHard + "\n");
		builder.append("dire side: \n");
		builder.append(dCarry + "\n");
		builder.append(dMid + "\n");
		builder.append(dOfflane + "\n");
		builder.append(dSoft + "\n");
		builder.append(dHard + "\n");
		
		return builder.toString();
	}

	public boolean played(int heroId) {
		for(String hero : getPick()) {
			if(Integer.parseInt(hero) == heroId) {
				return true;
			}
		}
		return false;
	}
	
	public boolean heroWon(int heroId) {
		if( ( forDirePlayed(heroId) && winner.equals("dire") ) || ( forRadiantPlayed(heroId) && winner.equals("radiant") ) )
			return true;
		return false;
	}

	public boolean wereOpposed(int heroId, int enemyId) {
		if( (forRadiantPlayed(heroId) && forDirePlayed(enemyId) ) || (forRadiantPlayed(enemyId) && forDirePlayed(heroId) ))
			return true;
		return false;
	}
	
	public boolean teamedUp(int heroId, int allyId) {
		if( (forRadiantPlayed(heroId) && forRadiantPlayed(allyId) ) || (forDirePlayed(allyId) && forDirePlayed(heroId) ))
			return true;
		return false;
	}

	private boolean forDirePlayed(int heroId) {
		var pick = getPick();
		for(int i = 5; i < 10; i++) {
			if(pick.get(i).equals(heroId + "")) {
				return true;
			}
		}
		return false;
	}

	private boolean forRadiantPlayed(int heroId) {
		var pick = getPick();
		for(int i = 0; i < 5; i++) {
			if(pick.get(i).equals(heroId + "")) {
				return true;
			}
		}
		return false;
	}

	public long getId() {
		return matchId;
	}

	public void setParsed(boolean parsed) {
		this.parsed = parsed;
	}
}
