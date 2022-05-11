package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.model.UserInfo;
import nts.sixblack.hexa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("find")
public class FindController {

    @Autowired
    UserService userService;

    @GetMapping("/{myUserId}/{userId}")
    public ResponseEntity<ResponseObject> moreInformation(@PathVariable("myUserId") long myUserId, @PathVariable("userId") long userId){
        UserInfo userInfo = userService.moreInformation(myUserId, userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thông tin người dùng", userInfo)
        );
    }
}
