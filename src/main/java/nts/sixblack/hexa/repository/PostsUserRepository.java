package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.PostsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsUserRepository extends JpaRepository<PostsUser, Long> {
}