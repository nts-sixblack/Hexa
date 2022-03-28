package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.entity.SongFeel;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.model.SongFeelInfo;
import nts.sixblack.hexa.model.SongInfo;
import nts.sixblack.hexa.repository.SongFeelRepository;
import nts.sixblack.hexa.service.SongFeelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SongFeelServiceImpl implements SongFeelService {
    @Autowired
    SongFeelRepository songFeelRepository;

    @Override
    public List<SongFeelInfo> findListFeelBySongId(long songId) {
        Song song = new Song();
        song.setSongId(songId);
        List<SongFeel> songFeelList = songFeelRepository.findBySong(song);
        List<SongFeelInfo> songFeelInfoList = new ArrayList<SongFeelInfo>();
        for (SongFeel songFeel:songFeelList){
            SongFeelInfo songFeelInfo = new SongFeelInfo();
            songFeelInfo.setSongFeelId(songFeel.getSongFeelId());
            songFeelInfo.setFeel(songFeel.isFeel());
            songFeelInfo.setUserId(songFeel.getUser().getUserId());
            songFeelInfo.setImage(songFeel.getUser().getAvatar());
            songFeelInfo.setName(songFeel.getUser().getName());
            songFeelInfo.setDateCreate(songFeel.getDateCreate());

            songFeelInfoList.add(songFeelInfo);
        }
        return songFeelInfoList;
    }

    @Override
    public void like(Like like) {
        long songFeelId = checkFeel(like.getTusId(), like.getUserId());
        if (songFeelId>0){
            delete(songFeelId);
        } else {
            User user = new User();
            user.setUserId(like.getUserId());
            Song song = new Song();
            song.setSongId(like.getTusId());

            SongFeel songFeel = new SongFeel();
            songFeel.setFeel(true);
            songFeel.setSong(song);
            songFeel.setUser(user);
            songFeel.setDateCreate(new Date());

            songFeelRepository.save(songFeel);
        }
    }

    @Override
    public long checkFeel(long songId, long userId) {
        Song song = new Song();
        song.setSongId(songId);
        User user = new User();
        user.setUserId(userId);

        SongFeel songFeel = songFeelRepository.findBySongAndUser(song, user);
        if (songFeel!=null){
            return songFeel.getSongFeelId();
        } else {
            return 0;
        }
    }

    @Override
    public void delete(long songFeelId) {
        SongFeel songFeel = new SongFeel();
        songFeel.setSongFeelId(songFeelId);
        songFeelRepository.delete(songFeel);
    }
}
