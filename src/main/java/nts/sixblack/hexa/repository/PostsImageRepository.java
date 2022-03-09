package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Posts;
import nts.sixblack.hexa.entity.PostsImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsImageRepository extends JpaRepository<PostsImage, Long> {
    List<PostsImage> findPostsImageByPosts(Posts posts);
}