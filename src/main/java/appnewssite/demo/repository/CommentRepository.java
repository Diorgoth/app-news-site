package appnewssite.demo.repository;

import appnewssite.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

     Optional<Comment> findByIdAndCreatedBy(Long id, Long createdBy);
}
