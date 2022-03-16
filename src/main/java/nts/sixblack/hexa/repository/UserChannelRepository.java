package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Channel;
import nts.sixblack.hexa.entity.UserChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChannelRepository extends JpaRepository<UserChannel, Long> {
    List<UserChannel> findUserChannelByChannel(Channel channel);
}