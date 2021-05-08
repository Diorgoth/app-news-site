package appnewssite.demo.service;

import appnewssite.demo.entity.Comment;
import appnewssite.demo.entity.Post;
import appnewssite.demo.entity.Role;
import appnewssite.demo.entity.User;
import appnewssite.demo.payload.ApiResponse;
import appnewssite.demo.payload.CommentDTO;
import appnewssite.demo.repository.CommentRepository;
import appnewssite.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;


    public ApiResponse addComment(CommentDTO commentDTO){
        Optional<Post> optionalPost = postRepository.findById(commentDTO.getPostId());
        if (!optionalPost.isPresent()){
            return new ApiResponse("Such post not found",false);
        }

        Comment comment = new Comment();
        comment.setPost(optionalPost.get());
        comment.setText(commentDTO.getText());
        return new ApiResponse("Comment added",true);



    }


    public ApiResponse editComment(Long commentId, CommentDTO commentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();

            Optional<Comment> optionalComment = commentRepository.findByIdAndCreatedBy(commentId, user.getId());
            if (!optionalComment.isPresent())
                return new ApiResponse("Comment was not found!", false);

            optionalComment.get().setText(commentDto.getText());
            commentRepository.save(optionalComment.get());
            return new ApiResponse("Comment updated!", true);
        }
        return new ApiResponse("Authorization empty!", false);
    }

    public ApiResponse getComments(){

        List<Comment> commentList = commentRepository.findAll();
        return new ApiResponse("All comments",true,commentList);
    }


    public ApiResponse deleteComment(Long commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            User user = (User) authentication.getPrincipal();
            Role role = user.getRole();

            Optional<Comment> optionalComment;
            if (role.getName().equals("ADMIN")) {
                optionalComment = commentRepository.findById(commentId);
            } else {
                optionalComment = commentRepository.findByIdAndCreatedBy(commentId, user.getId());
            }

            if (!optionalComment.isPresent())
                return new ApiResponse("Comment was not found!", false);

            commentRepository.deleteById(commentId);
            return new ApiResponse("Comment deleted!", true);
        }
        return new ApiResponse("Authorization empty!", false);
    }
}
