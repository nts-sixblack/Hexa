package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsUser;
import nts.sixblack.hexa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Posts findByPostsId(long postsId);

}