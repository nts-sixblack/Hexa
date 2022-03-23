package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Channel;
import nts.sixblack.hexa.entity.Contact;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.repository.ContactRepository;
import nts.sixblack.hexa.service.ChannelService;
import nts.sixblack.hexa.service.ContactService;
import nts.sixblack.hexa.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    ChannelService channelService;
    @Autowired
    UserChannelService userChannelService;

    @Override
    public long getChannelId(long myUserId, long userId) {
        Contact contact = contactRepository.findChannelIdByUser(myUserId, userId);
        if (contact != null){
            return contact.getChannel().getChannelId();
        } else {
            return newContact(myUserId, userId).getChannel().getChannelId();
        }
    }

    @Override
    public Contact newContact(long userId1, long userId2) {
        Channel channel = channelService.newChannel();
        User user1 = new User();
        user1.setUserId(userId1);
        User user2 = new User();
        user2.setUserId(userId2);

        Contact contact = new Contact();
        contact.setChannel(channel);
        contact.setUser1(user1);
        contact.setUser2(user2);
        contact.setDateCreate(new Date());

        userChannelService.newUserChannel(user1, user2, channel.getChannelId());

        return contactRepository.save(contact);
    }
}
