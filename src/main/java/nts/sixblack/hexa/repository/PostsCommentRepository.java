package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.PostsComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsCommentRepository extends JpaRepository<PostsComment, Long> {

}