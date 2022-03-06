package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.UserChannelRepository;
import nts.sixblack.hexa.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserChannelServiceImpl implements UserChannelService {
    @Autowired
    UserChannelRepository userChannelRepository;
}
