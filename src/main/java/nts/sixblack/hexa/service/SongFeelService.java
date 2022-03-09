package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.model.SongFeelInfo;
import nts.sixblack.hexa.model.SongInfo;

import java.util.List;

public interface SongFeelService {
    List<SongFeelInfo> findListFeelBySongId(long songId);
    void like(Like like);
    long checkFeel(long songId, long userId);
    void delete(long songFeelId);
}
