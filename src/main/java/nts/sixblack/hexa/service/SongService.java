package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.SongForm;
import nts.sixblack.hexa.model.SongInfo;

import java.util.List;

public interface SongService {
    SongInfo newSong(SongForm songForm);
    SongInfo findSongById(long songId);
    List<SongInfo> findListSongByCategoryId(long categoryId);
    List<SongInfo> listSongOfUser(long userId);
}
