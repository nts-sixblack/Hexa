package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}