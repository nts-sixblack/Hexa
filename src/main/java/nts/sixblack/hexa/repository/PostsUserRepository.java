package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsUser;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsUserRepository extends JpaRepository<PostsUser, Long> {
    List<PostsUser> findPostsUserByPosts(Posts posts);

    @Query("select pu.posts from PostsUser pu where pu.user = ?1")
    List<Posts> findPostsByUser(User user);

    @Query("select pu.posts from PostsUser pu where pu.user = ?1")
    List<Posts> findPostsByUser(User user, Pageable pageable);


}