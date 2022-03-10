package nts.sixblack.hexa.service;

import nts.sixblack.hexa.form.ListForm;
import nts.sixblack.hexa.model.ListSongInfo;

import java.util.List;

public interface ListSongService {
    ListSongInfo newList(ListForm listForm);
    void delete(long listSongId);
    ListSongInfo findByListSongId(long listSongId);
    List<ListSongInfo> findListByUserId(long userId);
}
