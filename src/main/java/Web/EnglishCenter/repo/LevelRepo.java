package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.course.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepo extends JpaRepository<Level,Integer> {
}
