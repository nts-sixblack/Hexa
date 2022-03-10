package nts.sixblack.hexa.service;

import nts.sixblack.hexa.model.ListSongItemInfo;

import java.util.List;

public interface ListSongItemService {
    void newListSongItem(long listSongId, long songId);
    void delete(long listSongItemId);
    List<ListSongItemInfo> findItemByListId(long listSongId);
}
