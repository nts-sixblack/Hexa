package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.entity.SongUser;
import nts.sixblack.hexa.model.SongUserInfo;
import nts.sixblack.hexa.repository.SongUserRepository;
import nts.sixblack.hexa.service.SongUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongUserServiceImpl implements SongUserService {
    @Autowired
    SongUserRepository songUserRepository;

    @Override
    public List<SongUserInfo> findUserBySongId(long songId) {
        Song song = new Song();
        song.setSongId(songId);
        List<SongUser> songUserList = songUserRepository.findBySong(song);
        List<SongUserInfo> songUserInfoList = new ArrayList<SongUserInfo>();

        for(SongUser songUser:songUserList){
            SongUserInfo songUserInfo = new SongUserInfo();
            songUserInfo.setSongUserId(songUser.getSongUserId());
            songUserInfo.setUserId(songUser.getUser().getUserId());
            songUserInfo.setImage(songUser.getUser().getAvatar());
            songUserInfo.setName(songUser.getUser().getName());

            songUserInfoList.add(songUserInfo);
        }
        return songUserInfoList;

    }
}
