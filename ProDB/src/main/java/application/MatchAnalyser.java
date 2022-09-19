package application;

import java.util.ArrayList;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.data.Hero;
import application.data.HeroesService;
import application.visual.DraftPanel;
import application.visual.DraftPanel.SetupMode;

@Service
public class MatchAnalyser {

	@Autowired
	private MatchupService matchupData;
	@Autowired
	private HeroesService heroes;
	
	private DraftPanel view;
	
	@PostConstruct
	private void createVisualPart() {
		view = new DraftPanel(SetupMode.LIVE, this);
	}
	
	// TODO make enumeration maybe?
	public String predict(Hero[] radiant, Hero[] dire) {
		
		double direCombined = teamSynergy(dire) + advantage(dire, radiant);
		double radiantCombined = teamSynergy(radiant) + advantage(radiant, dire);
		
//		System.out.println("dire synergy " + teamSynergy(dire));
//		System.out.println("radiant synergy " + teamSynergy(radiant));
//		System.out.println("dire advantage " + advantage(dire, radiant));
//		System.out.println("radiant advantage " + advantage(radiant, dire));
		
		
		System.out.println(radiantCombined + " " + direCombined);
		System.out.println(Math.abs(radiantCombined - direCombined));
		
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
				if(j != i){
					//System.out.println(team[i].getId() + "-" + team[j].getId() + " " + matchupData.withAlly(team[i].getId(), team[j].getId()));
					synergy += matchupData.withAlly(team[i].getId(), team[j].getId());					
				}
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
	
	public Hero[] createTeam(ArrayList<String> names) {
		var team = new Hero[5];
		team[0] = heroes.getByNickname(names.get(0));
		team[1] = heroes.getByNickname(names.get(1));
		team[2] = heroes.getByNickname(names.get(2));
		team[3] = heroes.getByNickname(names.get(3));
		team[4] = heroes.getByNickname(names.get(4));
		return team;
	}

	public void cyclePredictions() {
		
		while(true) {
			var draft = scanTeams();
			Hero[] radiant = draft[0];
			Hero[] dire = draft[1];
			predict(radiant, dire);
		}
		
	}
	
	private Hero[][] scanTeams() {
	Hero[][] teams = new Hero[2][5];
	String[] nicknames = new String[10];
	
	Scanner scan = new Scanner(System.in);
	scan.useDelimiter("\n");
	for(int i = 0; i < 10; i++) {
		nicknames[i] = scan.next();
		System.out.println("считали " + nicknames[i]);
	}
	scan.close();

	teams[0][0] = heroes.getByNickname(nicknames[0]);
	teams[0][1] = heroes.getByNickname(nicknames[1]);
	teams[0][2] = heroes.getByNickname(nicknames[2]);
	teams[0][3] = heroes.getByNickname(nicknames[3]);
	teams[0][4] = heroes.getByNickname(nicknames[4]);
	
	teams[1][0] = heroes.getByNickname(nicknames[5]);
	teams[1][1] = heroes.getByNickname(nicknames[6]);
	teams[1][2] = heroes.getByNickname(nicknames[7]);
	teams[1][3] = heroes.getByNickname(nicknames[8]);
	teams[1][4] = heroes.getByNickname(nicknames[9]);
	
	return teams;
}
	
	
	
}
