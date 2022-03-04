package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}