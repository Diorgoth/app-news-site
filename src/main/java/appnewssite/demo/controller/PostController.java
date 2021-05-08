package appnewssite.demo.controller;

import appnewssite.demo.entity.Post;
import appnewssite.demo.payload.ApiResponse;
import appnewssite.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;



    @PreAuthorize(value = "hasAnyAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> addPost(@Valid @RequestBody Post post){
        ApiResponse apiResponse = postService.addPost(post);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_POST')")
    @PutMapping("/{id}")
    public HttpEntity<?> editPost(@PathVariable Long id,@Valid @RequestBody Post post){
        ApiResponse apiResponse = postService.editPost(id, post);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPost(@PathVariable Long id){
        ApiResponse apiResponse = postService.getPost(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping( )
    public HttpEntity<?> getPosts(){
        ApiResponse apiResponse = postService.getPosts();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id){
        ApiResponse apiResponse = postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
