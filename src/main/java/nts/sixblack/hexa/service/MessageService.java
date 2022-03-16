package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.Message;
import nts.sixblack.hexa.form.MessageForm;
import nts.sixblack.hexa.model.MessageInfo;

import java.util.List;

public interface MessageService {
    Message newMessage(MessageForm messageForm);
    List<MessageInfo> findMessage(long channelId);
}
