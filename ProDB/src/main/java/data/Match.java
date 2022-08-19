package data;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name="history")
public class Match {

	@Id
	private long matchId;
	 
	private String date;
	
	private String winner;
	
	private String rCarry;
	private String rMid;
	private String rOfflane;
	private String rSoft;
	private String rHard;				// indexes are farm priority sensetive eg 1 = safe, 2 = mid...
	private String dCarry;
	private String dMid;
	private String dOfflane;
	private String dSoft;
	private String dHard;
	
	public Match() {
		
	}
	
	public ArrayList<String> getPick() {
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
}
