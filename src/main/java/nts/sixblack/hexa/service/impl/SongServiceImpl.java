package nts.sixblack.hexa.service.impl;

import com.cloudinary.Cloudinary;
import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.entity.SongCategory;
import nts.sixblack.hexa.entity.SongUser;
import nts.sixblack.hexa.entity.User;
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
import java.util.Date;
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

    @Autowired
    private CloudinaryService cloudinaryService;

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
//        song.setImage("https://"+bucketName+".s3."+region+".amazonaws.com/"+storageService.uploadFile(songForm.getImage()));
//        song.setSong("https://"+bucketName+".s3."+region+".amazonaws.com/"+storageService.uploadFile(songForm.getSong()));
        song.setImage(cloudinaryService.uploadFile(songForm.getImage()));
        song.setSong(cloudinaryService.uploadFile(songForm.getSong()));
        song.setSongCategory(songCategory);
        song.setDateCreate(new Date());

        Song songValue = songRepository.save(song);

        SongInfo songInfo = new SongInfo();
        songInfo.setSongId(songValue.getSongId());
        songInfo.setName(song.getName());
        songInfo.setImage(song.getImage());
        songInfo.setSong(song.getSong());

        User user = new User();
        user.setUserId(songForm.getUserId());

        SongUser songUser = new SongUser();
        songUser.setUser(user);
        songUser.setSong(songValue);
        songUser.setDateCreate(new Date());
        songUserService.newSongUser(songUser);

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
        songInfo.setDateCreate(TimeConfig.getTime(song.getDateCreate()));
        songInfo.setUserId(song.getSongUserList().get(0).getUser().getUserId());
        songInfo.setAvatar(song.getSongUserList().get(0).getUser().getAvatar());
        songInfo.setUserName(song.getSongUserList().get(0).getUser().getName());

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

    @Override
    public List<SongInfo> listSongOfUser(long userId) {
        List<Long> list = songUserService.listSongOfUser(userId);
        List<SongInfo> songInfoList = new ArrayList<SongInfo>();
        for (long songId:list){
            SongInfo songInfo = findSongById(songId);
            songInfoList.add(songInfo);
        }
        return songInfoList;
    }
}
