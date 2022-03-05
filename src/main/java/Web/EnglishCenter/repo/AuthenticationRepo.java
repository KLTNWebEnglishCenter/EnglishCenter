package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.user.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepo extends JpaRepository<Authentication,Integer> {
}
