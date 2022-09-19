package application.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroesService {

	@Autowired
	private HeroRepository heroesDB;
	@Autowired
	private HeroNicknamesDictionary dictionary;
	
	public List<Integer> getAllIds() {
		List<Integer> ids = new ArrayList<>();
		for(Hero hero : heroesDB.findAll()) {
			ids.add(hero.getId());
		}
		return ids;
	}
	
	public Hero getByNickname(String nickname) {
		Optional<Hero> hero = heroesDB.findById(dictionary.heroByNickname(nickname));
		return hero.get();
	}

	public Hero getById(int heroId) {
		Optional<Hero> hero = heroesDB.findById(heroId);
		return hero.get();
	}
	
	public Hero getByName(int heroId) {
		Optional<Hero> hero = heroesDB.findById(heroId);
		return hero.get();
	}
	
}
