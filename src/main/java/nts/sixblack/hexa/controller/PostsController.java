package nts.sixblack.hexa.controller;

import io.jsonwebtoken.Jwts;
import nts.sixblack.hexa.entity.PostsImage;
import nts.sixblack.hexa.form.CommentForm;
import nts.sixblack.hexa.form.Like;
import nts.sixblack.hexa.form.PostsForm;
import nts.sixblack.hexa.jwt.JwtTokenProvider;
import nts.sixblack.hexa.jwt.JwtValue;
import nts.sixblack.hexa.model.PostsInfo;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.service.*;
import nts.sixblack.hexa.ultil.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("posts")
public class PostsController {

    @Autowired
    PostsService postsService;

    @Autowired
    PostsFeelService postsFeelService;

    @Autowired
    PostsCommentService postsCommentService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("new")
    public ResponseEntity<ResponseObject> newPosts(@RequestBody PostsForm postsForm){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","newPost", postsForm.getCaption())
        );
    }

    @PostMapping("uploadFile")
    public ResponseEntity<ResponseObject> newFile(@ModelAttribute("postsForm") PostsForm postsForm){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đăng posts thanh công", postsService.newPosts(postsForm))
        );
//        return "ok";
    }

    @PostMapping("like")
    public ResponseEntity<ResponseObject> like(@RequestBody Like like){
        postsFeelService.like(like);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã like", "")
        );
    }

    @PostMapping("comment")
    public ResponseEntity<ResponseObject> comment(@RequestBody CommentForm commentForm){
        postsCommentService.comment(commentForm);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã comment", "")
        );
    }

    @GetMapping("comment/{postsCommentId}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("postsCommentId") long postsCommentId){
        postsCommentService.delete(postsCommentId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đã xóa comment Posts", "")
        );
    }

    @GetMapping("{postsId}")
    public ResponseEntity<ResponseObject> findPostsByPostId(@PathVariable("postsId") long postsId){

        long userId = 0;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            String token = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
            System.out.println(token);
           userId = jwtTokenProvider.getUserId(token);
        }

        if (userId > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Thông tin bài đăng", postsService.findPostByUser(postsId, userId))
            );
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject("faul","Token faul", "")
            );
        }


    }

    @DeleteMapping("{postsId}")
    public ResponseEntity<ResponseObject> deletePosts(@PathVariable("postsId") long postsId){
        postsService.delete(postsId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Đẵ xóa", "")
        );
    }

    @GetMapping("myUserId/{userId}")
    public ResponseEntity<ResponseObject> findListsPostToShowByMyId(@PathVariable("userId") long userId){

        List<PostsInfo> list = postsService.listPostShow(userId);
        Collections.sort(list, new Comparator<PostsInfo>() {
            @Override
            public int compare(PostsInfo o1, PostsInfo o2) {
//                if (o1.getDateCreate().before(o2.getDateCreate())){
//                    return 1;
//                } else {
//                    return -1;
//                }
                return -o1.getDateCreate().compareTo(o2.getDateCreate());
            }
        });

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","List Posts Show", list)
        );
    }

    @GetMapping("listPosts/{userId}")
    public ResponseEntity<ResponseObject> findMyListPosts(@PathVariable("userId") long userId){
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Danh sách các bài đăng của tôi", postsService.findListPostsByUserId(userId))
        );
    }

    @GetMapping("listComment/{postsId}")
    public ResponseEntity<ResponseObject> listCommentByPostsId(@PathVariable("postsId") long postsId){
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "list comment ", postsCommentService.findListCommentByPostsId(postsId))
        );
    }
}
