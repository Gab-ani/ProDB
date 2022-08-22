package application.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class HeroesService {

	@Autowired
	HeroRepository heroesDB;
	
	public List<Integer> getAllIds() {
		List<Integer> ids = new ArrayList<>();
		for(Hero hero : heroesDB.findAll()) {
			ids.add(hero.getId());
		}
		return ids;
	}
	
}
