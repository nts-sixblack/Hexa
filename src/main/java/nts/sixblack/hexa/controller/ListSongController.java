package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.form.ListForm;
import nts.sixblack.hexa.model.ListSongInfo;
import nts.sixblack.hexa.model.ListSongItemInfo;
import nts.sixblack.hexa.service.ListSongItemService;
import nts.sixblack.hexa.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("listSong")
public class ListSongController {
    @Autowired
    ListSongService listSongService;

    @Autowired
    ListSongItemService listSongItemService;

    @PostMapping("new")
    public ListSongInfo newList(@RequestBody ListForm listForm){
        return listSongService.newList(listForm);
    }

    @GetMapping("delete/{listSongId}")
    public void deleteListSong(@PathVariable("listSongId") long listSongId){
        listSongService.delete(listSongId)  ;
    }

    @GetMapping("newItem/{listSongId}/{songId}")
    public void newItem(@PathVariable("listSongId") long listSongId, @PathVariable("songId") long songId){
        listSongItemService.newListSongItem(listSongId, songId);
    }

    @GetMapping("deleteItem/{listSongItemId}")
    public void deleteItem(@PathVariable("listSongItemId") long listSongItemId){
        listSongItemService.delete(listSongItemId);
    }

    @GetMapping("{listSongId}")
    public ListSongInfo findListSong(@PathVariable("listSongId") long listSongId){
        return listSongService.findByListSongId(listSongId);
    }

    @GetMapping("user/{userId}")
    public List<ListSongInfo> findListByUser(@PathVariable("userId") long userId){
        return listSongService.findListByUserId(userId);
    }

}
