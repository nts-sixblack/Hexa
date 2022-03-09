package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsFeel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsFeelRepository extends JpaRepository<PostsFeel, Long> {
    @Query("select p from PostsFeel p where p.posts.postsId = ?1 and p.user.userId = ?2")
    PostsFeel checkFeelPostsUser(long postsId, long userId);

    List<PostsFeel> findPostsFeelByPosts(Posts posts);

}