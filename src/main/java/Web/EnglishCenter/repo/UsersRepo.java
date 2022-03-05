package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {

    @Query(value ="select * from users where username=:username",nativeQuery = true)
    Users findByUsername(@Param("username") String username);
}
