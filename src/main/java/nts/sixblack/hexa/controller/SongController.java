package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.entity.SongComment;
import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.form.SongForm;
import nts.sixblack.hexa.model.SongCategoryInfo;
import nts.sixblack.hexa.model.SongFeelInfo;
import nts.sixblack.hexa.model.SongInfo;
import nts.sixblack.hexa.service.SongCategoryService;
import nts.sixblack.hexa.service.SongCommentService;
import nts.sixblack.hexa.service.SongFeelService;
import nts.sixblack.hexa.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    @ResponseBody
    public List<SongCategoryInfo> listCategory(){
        return songCategoryService.listCategory();
    }

    @GetMapping("uploadFile")
    public String uploadFile(Model model){
        model.addAttribute("songForm", new SongForm());
        return "aaa";
    }

    @PostMapping("uploadFile")
    @ResponseBody
    public SongInfo uploadFile(@ModelAttribute("songForm") SongForm songForm){
        return songService.newSong(songForm);
    }

    @GetMapping("{songId}")
    @ResponseBody
    public SongInfo findSongById(@PathVariable("songId") long songId){
        return songService.findSongById(songId);
    }

    @GetMapping("list/{categoryId}")
    @ResponseBody
    public List<SongInfo> findSongByCategoryId(@PathVariable("categoryId") long categoryId){
        return songService.findListSongByCategoryId(categoryId);
    }

    @PostMapping("like")
    @ResponseBody
    public void like(@RequestBody Like like){
        songFeelService.like(like);
    }

    @PostMapping("comment")
    @ResponseBody
    public void comment(@RequestBody CommentForm commentForm){
        songCommentService.comment(commentForm);
    }
}
