package application.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HeroNickname {

	public HeroNickname() {
		
	}
	
	private int heroId;
	
	@Id
	private String nickname;
	
	public int getHeroId() {
		return heroId;
	}
	
}
