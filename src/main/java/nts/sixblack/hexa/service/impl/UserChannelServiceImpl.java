package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Channel;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.entity.UserChannel;
import nts.sixblack.hexa.model.UserChannelInfo;
import nts.sixblack.hexa.repository.UserChannelRepository;
import nts.sixblack.hexa.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserChannelServiceImpl implements UserChannelService {
    @Autowired
    UserChannelRepository userChannelRepository;

    @Override
    public List<UserChannelInfo> findUserChannelByChannelId(long channelId) {
        Channel channel = new Channel();
        channel.setChannelId(channelId);
        List<UserChannel> userChannelList = userChannelRepository.findUserChannelByChannel(channel);
        List<UserChannelInfo> userChannelInfoList = new ArrayList<UserChannelInfo>();
        for (UserChannel userChannel:userChannelList){
            UserChannelInfo userChannelInfo = new UserChannelInfo();
            userChannelInfo.setUserId(userChannel.getUser().getUserId());
            userChannelInfo.setName(userChannel.getUser().getName());
            userChannelInfo.setImage(userChannel.getUser().getAvatar());
            userChannelInfoList.add(userChannelInfo);
        }

        return userChannelInfoList;
    }

    @Override
    public void newUserChannel(User user1, User user2, long channelId) {
        Channel channel = new Channel();
        channel.setChannelId(channelId);

        UserChannel userChannel1 = new UserChannel();
        userChannel1.setChannel(channel);
        userChannel1.setUser(user1);
        userChannelRepository.save(userChannel1);

        UserChannel userChannel2 = new UserChannel();
        userChannel2.setChannel(channel);
        userChannel2.setUser(user2);
        userChannelRepository.save(userChannel2);
    }
}
