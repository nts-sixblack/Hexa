package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.SongUserRepository;
import nts.sixblack.hexa.service.SongUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class SongUserServiceImpl implements SongUserService {
    @Autowired
    SongUserRepository songUserRepository;
}
