package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.SongCategoryRepository;
import nts.sixblack.hexa.service.SongCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongCategoryServiceImpl implements SongCategoryService {
    @Autowired
    SongCategoryRepository songCategoryRepository;
}
