package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.model.SongCommentInfo;

import java.util.List;

public interface SongCommentService {
    List<SongCommentInfo> findListCommentBySongId(long songId);
    void comment(CommentForm commentForm);
}
