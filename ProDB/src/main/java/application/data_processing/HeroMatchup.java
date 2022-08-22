package application.data_processing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matchups")
public class HeroMatchup {

	@Id
	private String id;
	
	@Column(name = "target")
	private int forHero;
	@Column(name = "secondary")
	private int withHero;
	
	@Column(name = "together")
	private double withWR;
	@Column(name = "against")
	private double againstWR;
	
	public HeroMatchup() {
		
	}
	
	public HeroMatchup(int forHero, int withHero, double with, double against) {
		this.forHero = forHero;
		this.withHero = withHero;
		this.withWR = with;
		this.againstWR = against;
		this.id = forHero + "-" + withHero;
	}
	
	//*****************************************************************************
	// winrate with actually represents not winrate with certain hero in the team, 
	// but the difference between that winrate and base winrate of the hero and can be negative.
	// I.e base winrate of A is 54% and A wins 60 games in 100 if teamed up with B.
	// This will be represented as withWR = 6 for A-B matchup.
	// Note that B can have for example 51% base winrate, for B-A matchup withWR = 9 so A-B != B-A.
	// Same logic for winrateAgainst.
	//*****************************************************************************
}
