package nts.sixblack.hexa.service.impl;

import nts.sixblack.hexa.entity.SongCategory;
import nts.sixblack.hexa.model.SongCategoryInfo;
import nts.sixblack.hexa.repository.SongCategoryRepository;
import nts.sixblack.hexa.service.SongCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongCategoryServiceImpl implements SongCategoryService {
    @Autowired
    SongCategoryRepository songCategoryRepository;

    @Override
    public List<SongCategoryInfo> listCategory() {
        List<SongCategory> songCategoryList = songCategoryRepository.findAll();
        List<SongCategoryInfo> songCategoryInfoList = new ArrayList<SongCategoryInfo>();
        for (SongCategory songCategory:songCategoryList){
            SongCategoryInfo songCategoryInfo = new SongCategoryInfo();
            songCategoryInfo.setSongCategoryId(songCategory.getSongCategoryId());
            songCategoryInfo.setName(songCategory.getName());
            songCategoryInfoList.add(songCategoryInfo);
        }
        return songCategoryInfoList;
    }
}
