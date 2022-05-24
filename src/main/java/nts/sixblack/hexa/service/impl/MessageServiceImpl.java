package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.Channel;
import nts.sixblack.hexa.entity.Message;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.MessageForm;
import nts.sixblack.hexa.model.MessageInfo;
import nts.sixblack.hexa.repository.MessageRepository;
import nts.sixblack.hexa.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message newMessage(MessageForm messageForm) {
        Channel channel = new Channel();
        channel.setChannelId(messageForm.getChannelId());
        User user = new User();
        user.setUserId(messageForm.getUserId());

        Message message = new Message();
        message.setMessage(messageForm.getMessage());
        message.setChannel(channel);
        message.setUser(user);
        message.setDateCreate(new Date());

        return messageRepository.save(message);
    }

    @Override
    public List<MessageInfo> findMessage(long channelId, int page) {
        Channel channel = new Channel();
        channel.setChannelId(channelId);

        Pageable pageable = PageRequest.of(page, 15);

//        List<Message> messageList = messageRepository.findTop20ByChannelOrderByMessageId(channel);
        List<Message> messageList = messageRepository.listMessage(channelId, pageable);
        List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();
        for (Message message:messageList){
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setMessage(message.getMessage());
            messageInfo.setUserId(message.getUser().getUserId());
            messageInfo.setImage(message.getUser().getAvatar());
            messageInfo.setName(message.getUser().getName());
            messageInfo.setDateCreate(TimeConfig.getTime(message.getDateCreate()));
            messageInfoList.add(messageInfo);
        }
        return messageInfoList;
    }
}
