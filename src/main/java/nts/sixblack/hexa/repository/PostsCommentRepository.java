package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsCommentRepository extends JpaRepository<PostsComment, Long> {
    List<PostsComment> findPostsCommentByPosts(Posts posts);

    List<PostsComment> findTop3ByPostsOrderByPostsCommentId(Posts posts);
}