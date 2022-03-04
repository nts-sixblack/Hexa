package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.UserChannelRepository;
import nts.sixblack.hexa.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserChannelServiceImpl implements UserChannelService {
    @Autowired
    UserChannelRepository userChannelRepository;
}
