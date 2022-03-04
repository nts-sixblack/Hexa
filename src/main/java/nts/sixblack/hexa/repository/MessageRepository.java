package nts.sixblack.hexa.repository;

import nts.sixblack.hexa.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}