package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.PostsFeel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostsFeelRepository extends JpaRepository<PostsFeel, Long> {
    @Query("select p from PostsFeel p where p.posts.postsId = ?1 and p.user.userId = ?2")
    PostsFeel checkFeelPostsUser(long postsId, long userId);


}