package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.FollowRepository;
import nts.sixblack.hexa.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;

public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowRepository followRepository;
}
