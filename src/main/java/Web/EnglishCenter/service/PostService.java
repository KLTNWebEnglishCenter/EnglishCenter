package Web.EnglishCenter.service;


import Web.EnglishCenter.entity.Post;

import java.util.List;

public interface PostService {
    public Post save(Post post);
    public void delete(Post post);
    public List<Post> findAll();
    public Post findById(int id);
    public List<Post> findAllPostWithStatusNoAccept();
    public List<Post> findMyPost(int id);
    public List<Post> findByIdOrTitle(String idOrTitle);
}
