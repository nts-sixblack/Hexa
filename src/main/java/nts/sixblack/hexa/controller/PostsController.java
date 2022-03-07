package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.model.PostsInfo;
import nts.sixblack.hexa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("posts")
public class PostsController {

    @Autowired
    PostsService postsService;

    @Autowired
    PostsFeelService postsFeelService;

    @Autowired
    PostsCommentService postsCommentService;

    @PostMapping("new")
    public String newPosts(@RequestBody PostsForm postsForm){
        return postsForm.getCaption();
    }

    @GetMapping("uploadFile")
    public String newFile(Model model){
        model.addAttribute("postsForm",new PostsForm());
        return "abc";
    }

    @PostMapping("uploadFile")
    @ResponseBody
    public PostsInfo newFile(@ModelAttribute("postsForm") PostsForm postsForm){
        return postsService.newPosts(postsForm);
//        return "ok";
    }

    @PostMapping("like")
    @ResponseBody
    public void like(@RequestBody Like like){
        postsFeelService.like(like);
    }

    @PostMapping("comment")
    @ResponseBody
    public void comment(@RequestBody CommentForm commentForm){
        postsCommentService.comment(commentForm);
    }

    @GetMapping("comment/{postsCommentId}")
    @ResponseBody
    public void delete(@PathVariable("postsCommentId") long postsCommentId){
        postsCommentService.delete(postsCommentId);
    }



}
