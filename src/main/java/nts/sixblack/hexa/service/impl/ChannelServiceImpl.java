package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Channel;
import nts.sixblack.hexa.repository.ChannelRepository;
import nts.sixblack.hexa.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChannelServiceImpl implements ChannelService {
    @Autowired
    ChannelRepository channelRepository;

}
