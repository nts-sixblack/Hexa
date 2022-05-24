package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    @Query("select c from Channel c where c.channelId = ?1")
    Channel findByChannelId(long channelId);
}