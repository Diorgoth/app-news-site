package appnewssite.demo.controller;

import appnewssite.demo.entity.Post;
import appnewssite.demo.payload.ApiResponse;
import appnewssite.demo.payload.CommentDTO;
import appnewssite.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDTO commentDTO){
        ApiResponse apiResponse = commentService.addComment(commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_COMMENT')")
    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id,@Valid @RequestBody CommentDTO commentDTO){
        ApiResponse apiResponse = commentService.editComment(id, commentDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @GetMapping()
    public HttpEntity<?> getComments(){
        ApiResponse apiResponse = commentService.getComments();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyAuthority('DELETE_MY_COMMENT', 'DELETE_COMMENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable(name = "id") Long commentId) {
        ApiResponse response = commentService.deleteComment(commentId);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

}
