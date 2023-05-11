package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.form.StoryForm;
import nts.sixblack.hexa.jwt.JwtTokenProvider;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.model.StoryInfo;
import nts.sixblack.hexa.service.StorageService;
import nts.sixblack.hexa.service.StoryCommentService;
import nts.sixblack.hexa.service.StoryFeelService;
import nts.sixblack.hexa.service.StoryService;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("story")
public class StoryController {

    @Autowired
    StoryService storyService;

    @Autowired
    StoryFeelService storyFeelService;

    @Autowired
    StoryCommentService storyCommentService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("new")
    public ResponseEntity<ResponseObject> newStory(@ModelAttribute("storyForm") StoryForm storyForm){
        storyForm.setUserId(getUserId());
        System.out.println(storyForm.toString());
        StoryInfo storyInfo = storyService.newPosts(storyForm);

        System.out.println("new story");

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đăng story thanh công", storyInfo)
        );
    }

    @GetMapping("like/{storyId}")
    public ResponseEntity<ResponseObject> like(@PathVariable("storyId") long tusId){

        long userId = getUserId();
        if (userId == 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ResponseObject("fail","Đăng nhập story", "")
            );
        }
        Like like = new Like(tusId, userId);

        storyFeelService.like(like);
        System.out.println("feel posts "+tusId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã like", "")
        );
    }

    @PostMapping("comment")
    public ResponseEntity<ResponseObject> comment(@RequestBody CommentForm commentForm){

        long userId = getUserId();
        commentForm.setUserId(userId);

        storyCommentService.comment(commentForm);
        System.out.println("comment story "+commentForm.getTusId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã comment", "")
        );
    }

    @GetMapping("show")
    public ResponseEntity<ResponseObject> historyAppointment(@RequestParam(required = false, name = "page", defaultValue = "0") int page){
        long userId = getUserId();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","List story", storyService.show(userId, page))
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
