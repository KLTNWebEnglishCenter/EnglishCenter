package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepo extends JpaRepository<Exam,Integer> {
}
