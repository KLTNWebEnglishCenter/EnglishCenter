package Web.EnglishCenter.service.impl;

import Web.EnglishCenter.entity.Post;
import Web.EnglishCenter.repo.PostRepo;
import Web.EnglishCenter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Override
    public Post save(Post post) {
        return postRepo.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepo.delete(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Post findById(int id) {
        return postRepo.findById(id).get();
    }

    @Override
    public List<Post> findAllPostWithStatusNoAccept() {
        return postRepo.getListPostHasNotAccept();
    }

    @Override
    public List<Post> findAllPostWithStatusHasAccept() {
        return postRepo.getListPostHasAccept();
    }

    @Override
    public List<Post> findMyPost(int id) {
        return postRepo.getMyPost(id);
    }

    @Override
    public List<Post> findByIdOrTitle(String idOrTitle) {
        List<Post> posts = new ArrayList<>();
        try{
            int id = Integer.parseInt(idOrTitle);
            Post post = postRepo.getById(id);
            if(post != null) posts.add(post);
        }catch (Exception e){
            List<Post> postList = postRepo.getPostByTitle(idOrTitle);
            if(postList.size() > 0) posts.addAll(postList);
        }
        return posts;
    }
}
