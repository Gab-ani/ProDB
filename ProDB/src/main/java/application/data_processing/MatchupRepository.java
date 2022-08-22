package application.data_processing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchupRepository extends JpaRepository<HeroMatchup, String> {

}
