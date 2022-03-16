package nts.sixblack.hexa.service;

import nts.sixblack.hexa.entity.Channel;
import nts.sixblack.hexa.model.ChannelInfo;

import java.util.List;

public interface ChannelService {
    Channel newChannel();
    ChannelInfo findChannel(long channelId);
}
