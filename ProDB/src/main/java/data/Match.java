package data;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
public class Match {

	@Id
	private long matchId;
	 
	private String date;
	
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
	
	public void radiantCarry(String hero) {
		this.rCarry = hero;
	}
	public void radiantMid(String hero) {
		this.rMid = hero;
	}
	public void radiantOfflane(String hero) {
		this.rOfflane = hero;
	}
	public void radiantSoft(String hero) {
		this.rSoft = hero;
	}
	public void radiantHard(String hero) {
		this.rHard = hero;
	}
	
	public void direCarry(String hero) {
		this.dCarry = hero;
	}
	public void direMid(String hero) {
		this.dMid = hero;
	}
	public void direOfflane(String hero) {
		this.dOfflane = hero;
	}
	public void direSoft(String hero) {
		this.dSoft = hero;
	}
	public void direHard(String hero) {
		this.dHard = hero;
	}

	public void setID(long id) {
		this.matchId = id;		
	}

	public void setDate(String date) {
		this.date = date;		
	}

}
