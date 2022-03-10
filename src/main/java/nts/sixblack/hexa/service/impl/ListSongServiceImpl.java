package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.ListSong;
import nts.sixblack.hexa.entity.ListSongItem;
import nts.sixblack.hexa.entity.User;
import nts.sixblack.hexa.form.ListForm;
import nts.sixblack.hexa.model.ListSongInfo;
import nts.sixblack.hexa.model.ListSongItemInfo;
import nts.sixblack.hexa.repository.ListSongRepository;
import nts.sixblack.hexa.service.ListSongItemService;
import nts.sixblack.hexa.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    ListSongRepository listSongRepository;

    @Autowired
    ListSongItemService listSongItemService;

    @Override
    public ListSongInfo newList(ListForm listForm) {
        User user = new User();
        user.setUserId(listForm.getUserId());

        ListSong listSong = new ListSong();
        listSong.setName(listForm.getName());
        listSong.setUser(user);
        listSong.setStatus(false);

        ListSong listSong1 = listSongRepository.save(listSong);


        ListSongInfo listSongInfo = new ListSongInfo();
        listSongInfo.setListSongId(listSong1.getListSongId());
        listSongInfo.setName(listSong1.getName());
        listSongInfo.setUserId(listSong1.getUser().getUserId());
        listSongInfo.setName(listSong1.getUser().getName());
        listSongInfo.setImage(listSong1.getUser().getAvatar());

        return listSongInfo;
    }

    @Override
    public void delete(long listSongId) {
        listSongRepository.deleteById(listSongId);
    }

    @Override
    public ListSongInfo findByListSongId(long listSongId) {
        ListSong listSong = listSongRepository.findByListSongId(listSongId);
        List<ListSongItemInfo> listSongItemInfoList = listSongItemService.findItemByListId(listSongId);

        ListSongInfo listSongInfo = new ListSongInfo();
        listSongInfo.setListSongId(listSong.getListSongId());
        listSongInfo.setNameOfList(listSong.getName());
        listSongInfo.setUserId(listSong.getUser().getUserId());
        listSongInfo.setName(listSong.getUser().getName());
        listSongInfo.setImage(listSong.getUser().getAvatar());
        listSongInfo.setListSongItemList(listSongItemInfoList);

        return listSongInfo;
    }

    @Override
    public List<ListSongInfo> findListByUserId(long userId) {
        User user = new User();
        user.setUserId(userId);
        List<ListSong> listSongList = listSongRepository.findByUser(user);
        List<ListSongInfo> listSongInfoList = new ArrayList<ListSongInfo>();
        for (ListSong listSong:listSongList){
            ListSongInfo listSongInfo = new ListSongInfo();
            listSongInfo.setListSongId(listSong.getListSongId());
            listSongInfo.setNameOfList(listSong.getName());
            listSongInfoList.add(listSongInfo);
        }
        return listSongInfoList;
    }
}
