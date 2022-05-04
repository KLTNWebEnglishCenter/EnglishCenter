package Web.EnglishCenter.api;

import Web.EnglishCenter.entity.Post;
import Web.EnglishCenter.entity.user.Users;
import Web.EnglishCenter.entityDTO.PostDTO;
import Web.EnglishCenter.service.PostService;
import Web.EnglishCenter.service.UsersService;
import Web.EnglishCenter.utils.ConvertDTOHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class PostRestAPI {

    @Autowired
    private PostService postService;

    @Autowired
    private UsersService usersService;

    private ConvertDTOHelper convertDTOHelper=new ConvertDTOHelper();

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPost(){
        List<PostDTO> postDTOList = convertDTOHelper.convertListPost(postService.findAll());
        return ResponseEntity.ok().body(postDTOList);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") int id){
        return ResponseEntity.ok().body(convertDTOHelper.convertPost(postService.findById(id)));
    }

    @PostMapping("/post/save")
    public ResponseEntity<Post> savePost(@RequestBody Post post){
        Users users = usersService.findByUsername(post.getUsers().getUsername());
        post.setUsers(users);
        return ResponseEntity.ok().body(postService.save(post));
    }

    @GetMapping("/post/delete/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") int id){
        Post post = postService.findById(id);
        postService.delete(post);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/post/status/no/accept")
    public ResponseEntity<List<Post>> getAllPostWithStatusNoAccept(){
        return ResponseEntity.ok().body(postService.findAllPostWithStatusNoAccept());
    }

    @GetMapping("/post/status/has/accept")
    public ResponseEntity<List<Post>> getAllPostWithStatusHasAccept(){
        return ResponseEntity.ok().body(postService.findAllPostWithStatusHasAccept());
    }

    @GetMapping("/post/my/{id}")
    public ResponseEntity<List<Post>> getMyPost(@PathVariable int id){
        return ResponseEntity.ok().body(postService.findMyPost(id));
    }

    @PostMapping("/post/searchByIdOrTitle")
    public ResponseEntity<List<PostDTO>> getPostByIdOrTitle(String idOrTitle){
        List<Post> posts = postService.findByIdOrTitle(idOrTitle);
        List<PostDTO> postDTOList = convertDTOHelper.convertListPost(posts);
        return ResponseEntity.ok().body(postDTOList);
    }

//    @GetMapping("/post/testSave")
//    public ResponseEntity<Post> testSave(){
//        Post post = new Post("68jutfygb","114hfvh");
//        Users users = usersService.findById(1);
//        post.setUsers(users);
//        return ResponseEntity.ok().body(postService.save(post));
//    }
}
