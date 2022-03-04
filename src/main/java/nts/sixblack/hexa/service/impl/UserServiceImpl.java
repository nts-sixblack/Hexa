package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.UserRepository;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
}
