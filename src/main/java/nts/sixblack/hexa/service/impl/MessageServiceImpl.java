package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.MessageRepository;
import nts.sixblack.hexa.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
}
