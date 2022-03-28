package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.config.TimeConfig;
import nts.sixblack.hexa.entity.ListSong;
import nts.sixblack.hexa.entity.ListSongItem;
import nts.sixblack.hexa.entity.Song;
import nts.sixblack.hexa.model.ListSongItemInfo;
import nts.sixblack.hexa.model.SongInfo;
import nts.sixblack.hexa.repository.ListSongItemRepository;
import nts.sixblack.hexa.service.ListSongItemService;
import nts.sixblack.hexa.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ListSongItemServiceImpl implements ListSongItemService {
    @Autowired
    ListSongItemRepository listSongItemRepository;

    @Autowired
    SongService songService;

    @Override
    public void newListSongItem(long listSongId, long songId) {
        ListSong listSong = new ListSong();
        listSong.setListSongId(listSongId);
        Song song = new Song();
        song.setSongId(songId);
        ListSongItem listSongItem = new ListSongItem();
        listSongItem.setListSong(listSong);
        listSongItem.setSong(song);
        listSongItem.setDateCreate(new Date());
        listSongItemRepository.save(listSongItem);
    }

    @Override
    public void delete(long listSongItemId) {
        listSongItemRepository.deleteById(listSongItemId);
    }

    @Override
    public List<ListSongItemInfo> findItemByListId(long listSongId) {
        ListSong listSong = new ListSong();
        listSong.setListSongId(listSongId);
//
        List<ListSongItem> listSongItemList = listSongItemRepository.findByListSong(listSong);
        List<ListSongItemInfo> listSongItemInfoList = new ArrayList<ListSongItemInfo>();
        for (ListSongItem listSongItem:listSongItemList){
            SongInfo songInfo = songService.findSongById(listSongItem.getSong().getSongId());

            ListSongItemInfo listSongItemInfo = new ListSongItemInfo();
            listSongItemInfo.setListSongItemId(listSongItem.getListSongItemId());
            listSongItemInfo.setSong(songInfo);
            listSongItemInfo.setDateCreate(listSongItem.getDateCreate());

            listSongItemInfoList.add(listSongItemInfo);
        }
        return listSongItemInfoList;
    }


}
