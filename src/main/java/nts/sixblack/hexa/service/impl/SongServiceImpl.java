package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.entity.SongCategory;
import nts.sixblack.hexa.form.SongForm;
import nts.sixblack.hexa.model.SongCommentInfo;
import nts.sixblack.hexa.model.SongFeelInfo;
import nts.sixblack.hexa.model.SongInfo;
import nts.sixblack.hexa.model.SongUserInfo;
import nts.sixblack.hexa.repository.SongRepository;
import nts.sixblack.hexa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository songRepository;

    @Autowired
    SongFeelService songFeelService;

    @Autowired
    SongCommentService songCommentService;

    @Autowired
    SongUserService songUserService;

    @Autowired
    StorageService storageService;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public SongInfo newSong(SongForm songForm) {
        SongCategory songCategory = new SongCategory();
        songCategory.setSongCategoryId(songForm.getSongCategoryId());
        Song song = new Song();
        song.setName(songForm.getName());
        song.setImage("https://"+bucketName+".s3."+region+".amazonaws.com/"+storageService.uploadFile(songForm.getImage()));
        song.setSong("https://"+bucketName+".s3."+region+".amazonaws.com/"+storageService.uploadFile(songForm.getSong()));
        song.setSongCategory(songCategory);

        SongInfo songInfo = new SongInfo();
        songInfo.setSongId(songRepository.save(song).getSongId());
        songInfo.setName(song.getName());
        songInfo.setImage(song.getImage());
        songInfo.setSong(song.getSong());

        return songInfo;
    }

    @Override
    public SongInfo findSongById(long songId) {
        Song song = songRepository.findBySongId(songId);

        SongInfo songInfo = new SongInfo();
        songInfo.setSongId(song.getSongId());
        songInfo.setName(song.getName());
        songInfo.setImage(song.getImage());
        songInfo.setSong(song.getSong());

        List<SongFeelInfo> songFeelInfoList = songFeelService.findListFeelBySongId(songId);
        songInfo.setSongFeelList(songFeelInfoList);

        List<SongCommentInfo> songCommentInfoList = songCommentService.findListCommentBySongId(songId);
        songInfo.setSongCommentList(songCommentInfoList);

        List<SongUserInfo> songUserInfoList = songUserService.findUserBySongId(songId);
        songInfo.setSongUserList(songUserInfoList);

        return songInfo;
    }

    @Override
    public List<SongInfo> findListSongByCategoryId(long categoryId) {
        SongCategory songCategory = new SongCategory();
        songCategory.setSongCategoryId(categoryId);
        List<Song> songList = songRepository.findBySongCategory(songCategory);
        List<SongInfo> songInfoList = new ArrayList<SongInfo>();
        for (Song song:songList){
            SongInfo songInfo = findSongById(song.getSongId());
            songInfoList.add(songInfo);
        }
        return songInfoList;
    }
}
