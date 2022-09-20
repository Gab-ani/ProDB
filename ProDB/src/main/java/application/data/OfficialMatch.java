package application.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pros")
public class OfficialMatch extends Match {

	private String radiantTeam;
	private String direTeam;
	
	private String tourney;
	private String additionalInfo; 
	
	public void setCompetitors(String radiantTeam, String direTeam) {
		this.radiantTeam = radiantTeam;
		this.direTeam = direTeam;
	}
	
	public void setTourney(String tourney) {
		this.tourney = tourney;
	}
	
	public String toString() {
		String matchInfo = "";
		matchInfo += radiantTeam + " vs " + direTeam;
		matchInfo += ", " + tourney;
		matchInfo += " " + additionalInfo;
		return matchInfo;
	}
	
}
