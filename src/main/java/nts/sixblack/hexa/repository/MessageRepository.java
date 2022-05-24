package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Channel;
import nts.sixblack.hexa.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m where m.channel = ?1 order by m.messageId ")
    List<Message> findByChAndChannel(Channel channel);

    List<Message> findTop20ByChannelOrderByMessageId(Channel channel);

    @Query("select m from Message m " +
            "where m.channel.channelId = ?1 order by m.dateCreate ")
    List<Message> listMessage(long channelId, Pageable pageable);
}