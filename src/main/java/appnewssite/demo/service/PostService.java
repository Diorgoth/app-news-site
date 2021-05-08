package appnewssite.demo.service;


import appnewssite.demo.entity.Post;
import appnewssite.demo.entity.User;
import appnewssite.demo.payload.ApiResponse;
import appnewssite.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ApiResponse addPost(Post post){

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails.getRole().getName().equals("ROLE_ADMIN")){

            postRepository.save(post);
            return new ApiResponse("Post added",true);
        }
        return new ApiResponse("You are not eligible for this action",false);
    }



    public ApiResponse editPost(Long id,Post post){

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getRole().getName().equals("ROLE_ADMIN")){


            Optional<Post> optionalPost = postRepository.findById(id);
            if (!optionalPost.isPresent()){
                return new ApiResponse("Such post not found",false);
            }


            Post post1 = optionalPost.get();
            post1.setText(post.getText());
            post1.setTitle(post.getTitle());
            post1.setUrl(post.getUrl());
            postRepository.save(post1);

            return new ApiResponse("Post edited",true);

        }
        return new ApiResponse("You are not eligible for this action",false);

    }

    public ApiResponse getPost(Long id){

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getRole().getName().equals("ROLE_ADMIN")){

            Optional<Post> optionalPost = postRepository.findById(id);
            return optionalPost.map(post -> new ApiResponse("Here post", true, post)).orElseGet(() -> new ApiResponse("Such post not found", false));

        }
        return new ApiResponse("You are not eligible for this action",false);
    }


    public ApiResponse getPosts(){
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getRole().getName().equals("ROLE_ADMIN")){

            return new ApiResponse("All Posts",true,postRepository.findAll());

        }
        return new ApiResponse("You are not eligible for this action",false);
    }


    public ApiResponse deletePost(Long id){
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getRole().getName().equals("ROLE_ADMIN")){
            Optional<Post> optionalPost = postRepository.findById(id);
            if (!optionalPost.isPresent()){
                return new ApiResponse("Such Post not found",false);
            }

            postRepository.deleteById(id);
            return new ApiResponse("Post deleted",true);
        }
        return new ApiResponse("You are not eligible for this action",false);
    }



}
