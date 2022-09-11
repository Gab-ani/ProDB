package application.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroNicknamesDictionary {
	
	@Autowired
	private HeroNicknamesRepository nicknamesData;
	
	public int heroByNickname(String nickname) {
		return nicknamesData.getByNickname(nickname).getHeroId();
	}
	
}
