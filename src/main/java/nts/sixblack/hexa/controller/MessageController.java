package nts.sixblack.hexa.controller;

import nts.sixblack.hexa.form.MessageForm;
import nts.sixblack.hexa.model.ResponseObject;
import nts.sixblack.hexa.service.ChannelService;
import nts.sixblack.hexa.service.ContactService;
import nts.sixblack.hexa.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    ContactService contactService;

    @Autowired
    MessageService messageService;

    @Autowired
    ChannelService channelService;

    @GetMapping("/user/{myUserId}/{userId}")
    public ResponseEntity<ResponseObject> newUser(@PathVariable("userId") long userId, @PathVariable("myUserId") long myUserId){
        long channelId = contactService.getChannelId(myUserId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseObject("ok","đã tìm thấy channelId", channelService.findChannel(channelId))
        );
    }

    @PostMapping("/new")
    public ResponseEntity<ResponseObject> newMessage(@RequestBody MessageForm messageForm){

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok","đã gửi", messageService.newMessage(messageForm).getMessageId())
        );
    }
}
