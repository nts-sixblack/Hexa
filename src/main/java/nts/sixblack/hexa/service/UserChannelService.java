package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.model.UserChannelInfo;

import java.util.List;

public interface UserChannelService {
    List<UserChannelInfo> findUserChannelByChannelId(long channelId);
    void newUserChannel(User user1, User user2, long channelId);
}
