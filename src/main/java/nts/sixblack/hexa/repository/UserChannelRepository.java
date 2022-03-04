package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.UserChannel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChannelRepository extends JpaRepository<UserChannel, Long> {
}