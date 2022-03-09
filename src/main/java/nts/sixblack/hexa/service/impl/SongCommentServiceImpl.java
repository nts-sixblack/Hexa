package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.entity.SongComment;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.model.SongCommentInfo;
import nts.sixblack.hexa.repository.SongCommentRepository;
import nts.sixblack.hexa.service.SongCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongCommentServiceImpl implements SongCommentService {
    @Autowired
    SongCommentRepository songCommentRepository;

    @Override
    public List<SongCommentInfo> findListCommentBySongId(long songId) {
        Song song = new Song();
        song.setSongId(songId);
        List<SongComment> songCommentList = songCommentRepository.findBySong(song);
        List<SongCommentInfo> songCommentInfoList = new ArrayList<SongCommentInfo>();
        for (SongComment songComment:songCommentList){
            SongCommentInfo songCommentInfo = new SongCommentInfo();
            songCommentInfo.setSongCommentId(songComment.getSongCommentId());
            songCommentInfo.setComment(songComment.getComment());
            songCommentInfo.setUserId(songComment.getUser().getUserId());
            songCommentInfo.setImage(songComment.getUser().getAvatar());
            songCommentInfo.setName(songComment.getUser().getName());

            songCommentInfoList.add(songCommentInfo);
        }
        return songCommentInfoList;
    }

    @Override
    public void comment(CommentForm commentForm) {
        User user = new User();
        user.setUserId(commentForm.getUserId());
        Song song = new Song();
        song.setSongId(commentForm.getTusId());
        SongComment songComment = new SongComment();
        songComment.setComment(commentForm.getComment());
        songComment.setUser(user);
        songComment.setSong(song);

        songCommentRepository.save(songComment);
    }
}
