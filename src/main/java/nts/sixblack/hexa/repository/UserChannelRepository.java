package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Channel;
import nts.sixblack.hexa.entity.UserChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserChannelRepository extends JpaRepository<UserChannel, Long> {
    @Query("select u from UserChannel u where u.channel = ?1")
    List<UserChannel> findUserChannelByChannel(Channel channel);
}