package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.repository.SongCommentRepository;
import nts.sixblack.hexa.service.SongCommentService;
import org.springframework.beans.factory.annotation.Autowired;

public class SongCommentServiceImpl implements SongCommentService {
    @Autowired
    SongCommentRepository songCommentRepository;
}
