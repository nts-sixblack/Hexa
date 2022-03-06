package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.SongRepository;
import nts.sixblack.hexa.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository songRepository;
}
