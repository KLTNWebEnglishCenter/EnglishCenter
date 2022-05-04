package Web.EnglishCenter.repo;

import Web.EnglishCenter.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

    @Query(value = "select * from post where status = 'yetAccept'",nativeQuery = true)
    List<Post> getListPostHasNotAccept();

    @Query(value = "select * from post where status = 'hasAccept'",nativeQuery = true)
    List<Post> getListPostHasAccept();

    @Query(value = "select * from post where users_id =:id",nativeQuery = true)
    List<Post> getMyPost(int id);

    @Query(value = "select * from post where title like %:title%",nativeQuery = true)
    List<Post> getPostByTitle(String title);
}
