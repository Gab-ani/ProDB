package application.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroNicknamesRepository extends JpaRepository<HeroNickname, Integer> {

	HeroNickname getByNickname(String nickname);

}
