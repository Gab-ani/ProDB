package application.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MatchRepository extends JpaRepository<Match, Integer> {
	
	public List<Match> findByParsed(boolean isParsed);
	
}
