package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.form.StoryForm;
import nts.sixblack.hexa.jwt.JwtTokenProvider;
import nts.sixblack.hexa.model.PostsInfo;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.model.StoryInfo;
import nts.sixblack.hexa.service.SongCategoryService;
import nts.sixblack.hexa.service.StoryService;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("story")
public class StoryController {

    @Autowired
    StoryService storyService;

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

    private long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            String token = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
            return jwtTokenProvider.getUserId(token);
        }
        return 0;
    }
}
