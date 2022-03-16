package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.Contact;

public interface ContactService {
    long getChannelId(long myUserId, long userId);
    Contact newContact(long userId1, long userId2);
}
