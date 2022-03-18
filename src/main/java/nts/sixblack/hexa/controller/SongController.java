package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.SongComment;
import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.form.SongForm;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.model.SongCategoryInfo;
import nts.sixblack.hexa.model.SongFeelInfo;
import nts.sixblack.hexa.model.SongInfo;
import nts.sixblack.hexa.service.SongCategoryService;
import nts.sixblack.hexa.service.SongCommentService;
import nts.sixblack.hexa.service.SongFeelService;
import nts.sixblack.hexa.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("categoryList")
    public ResponseEntity<ResponseObject> listCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách Category", songCategoryService.listCategory())
        );
    }

    @PostMapping("uploadFile")
    public ResponseEntity<ResponseObject> uploadFile(@ModelAttribute("songForm") SongForm songForm){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách Category", songService.newSong(songForm))
        );
    }

    @GetMapping("{songId}")
    public ResponseEntity<ResponseObject> findSongById(@PathVariable("songId") long songId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thông tin bài hát", songService.findSongById(songId))
        );
    }

    @GetMapping("list/{categoryId}")
    public ResponseEntity<ResponseObject> findSongByCategoryId(@PathVariable("categoryId") long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Danh sách bài hát theo thể loại", songService.findListSongByCategoryId(categoryId))
        );
    }

    @PostMapping("like")
    public ResponseEntity<ResponseObject> like(@RequestBody Like like){
        songFeelService.like(like);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã like", "" )
        );

    }

    @PostMapping("comment")
    public ResponseEntity<ResponseObject> comment(@RequestBody CommentForm commentForm){
        songCommentService.comment(commentForm);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã comment", "")
        );

    }
}
