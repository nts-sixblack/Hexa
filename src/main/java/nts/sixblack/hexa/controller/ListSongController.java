package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.form.ListForm;
import nts.sixblack.hexa.jwt.JwtTokenProvider;
import nts.sixblack.hexa.model.ListSongInfo;
import nts.sixblack.hexa.model.ListSongItemInfo;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.service.ListSongItemService;
import nts.sixblack.hexa.service.ListSongService;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("listSong")
public class ListSongController {
    @Autowired
    ListSongService listSongService;

    @Autowired
    ListSongItemService listSongItemService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("new")
    public ResponseEntity<ResponseObject> newList(@RequestBody ListForm listForm){
        listForm.setUserId(getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thêm mới 1 list nhạc", listSongService.newList(listForm))
        );
    }

    @GetMapping("delete/{listSongId}")
    public ResponseEntity<ResponseObject> deleteListSong(@PathVariable("listSongId") long listSongId){

        listSongService.delete(listSongId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Xóa 1 list song", "")
        );
    }

    @GetMapping("newItem/{listSongId}/{songId}")
    public ResponseEntity<ResponseObject> newItem(@PathVariable("listSongId") long listSongId, @PathVariable("songId") long songId){
        listSongItemService.newListSongItem(listSongId, songId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thêm mới 1 bài hát vào list song", "")
        );
    }

    @GetMapping("deleteItem/{listSongItemId}")
    public ResponseEntity<ResponseObject> deleteItem(@PathVariable("listSongItemId") long listSongItemId){
        listSongItemService.delete(listSongItemId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Xóa 1 bìa hát khỏi list", "")
        );
    }

    @GetMapping("{listSongId}")
    public ResponseEntity<ResponseObject> findListSong(@PathVariable("listSongId") long listSongId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thông tin của list song", listSongService.findByListSongId(listSongId))
        );
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<ResponseObject> findListByUser(@PathVariable("userId") long userId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thông tin của list song", listSongService.findListByUserId(userId))
        );
    }

    private long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            String token = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
            return jwtTokenProvider.getUserId(token);
        }
        return 0;
    }
}
