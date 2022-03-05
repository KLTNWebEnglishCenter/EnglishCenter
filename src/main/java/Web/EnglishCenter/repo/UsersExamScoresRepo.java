package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.exam.UsersExamScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersExamScoresRepo extends JpaRepository<UsersExamScores,Integer> {
}
