package application.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroNicknamesDictionary {
	
	@Autowired
	private HeroNicknamesRepository nicknamesData;
	
	public int heroByNickname(String nickname) {
		HeroNickname nick = nicknamesData.getByNickname(nickname);
		if(nick == null)
			System.out.println(nickname);
		int id = nick.getHeroId();
		return id;
	}
	
}
