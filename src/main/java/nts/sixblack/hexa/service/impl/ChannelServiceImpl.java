package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.Channel;
import nts.sixblack.hexa.model.ChannelInfo;
import nts.sixblack.hexa.repository.ChannelRepository;
import nts.sixblack.hexa.service.ChannelService;
import nts.sixblack.hexa.service.MessageService;
import nts.sixblack.hexa.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    MessageService messageService;

    @Autowired
    UserChannelService  userChannelService;

    @Override
    public Channel newChannel() {
        Channel channel = new Channel();
        channel.setDateCreate(new Date());
        return channelRepository.save(channel);
    }

    @Override
    public ChannelInfo findChannel(long channelId) {
        Channel channel = channelRepository.findByChannelId(channelId);
        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.setChannelId(channelId);
        channelInfo.setDecription(channel.getDecription());
        channelInfo.setName(channel.getName());
        channelInfo.setDateCreate(TimeConfig.getTime(channel.getDateCreate()));

        channelInfo.setMessageList(messageService.findMessage(channelId, 0));
        channelInfo.setUserChannelList(userChannelService.findUserChannelByChannelId(channelId));

        return channelInfo;
    }
}
