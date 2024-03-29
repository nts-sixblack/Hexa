package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.SongComment;
import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.form.SongForm;
import nts.sixblack.hexa.jwt.JwtTokenProvider;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.model.SongCategoryInfo;
import nts.sixblack.hexa.model.SongFeelInfo;
import nts.sixblack.hexa.model.SongInfo;
import nts.sixblack.hexa.service.SongCategoryService;
import nts.sixblack.hexa.service.SongCommentService;
import nts.sixblack.hexa.service.SongFeelService;
import nts.sixblack.hexa.service.SongService;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("song")
public class SongController {
    @Autowired
    SongCategoryService songCategoryService;

    @Autowired
    SongService songService;

    @Autowired
    SongFeelService songFeelService;

    @Autowired
    SongCommentService songCommentService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("categoryList")
    public ResponseEntity<ResponseObject> listCategory(){
        System.out.println("list category ");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách Category", songCategoryService.listCategory())
        );
    }

    @PostMapping("uploadFile")
    public ResponseEntity<ResponseObject> uploadFile(@ModelAttribute("songForm") SongForm songForm){
        songForm.setUserId(getUserId());
        System.out.println("new song");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã thêm mới 1 bài hát", songService.newSong(songForm))
        );
    }

    @GetMapping("{songId}")
    public ResponseEntity<ResponseObject> findSongById(@PathVariable("songId") long songId){
        System.out.println("information song "+songId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thông tin bài hát", songService.findSongById(songId))
        );
    }

    @GetMapping("list/{categoryId}")
    public ResponseEntity<ResponseObject> findSongByCategoryId(@PathVariable("categoryId") long categoryId){
        System.out.println("list song of category "+categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách bài hát theo thể loại", songService.findListSongByCategoryId(categoryId))
        );
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<ResponseObject> listSongOfUser(@PathVariable("userId") long userId){
        System.out.println("list song of user "+userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách bài hát của 1 người", songService.listSongOfUser(userId))
        );
    }

    @PostMapping("like")
    public ResponseEntity<ResponseObject> like(@RequestBody Like like){
        songFeelService.like(like);
        System.out.println("feel song");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã like", "" )
        );

    }

    @PostMapping("comment")
    public ResponseEntity<ResponseObject> comment(@RequestBody CommentForm commentForm){
        songCommentService.comment(commentForm);
        System.out.println("comment song");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã comment", "")
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
