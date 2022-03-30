package nts.sixblack.hexa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private long userId;
    private String firstName;
    private String lastName;
    private String avatar;
    private String background;
    private String email;
    private String password;
    private String name;
    private String phone;
    private boolean followStatus;
    private int numberOfPosts;
    private int numberOfFollower;
    private int numberOfFollowing;
    private String token;
    private Date dateCreate;

    //    private List<PostsUserInfo> postsUserList;
//    private List<PostsFeelInfo> postsFeelList;
//    private List<PostsCommentInfo> postsCommentList;
//    private List<UserChannelInfo> userChannelList;
//    private List<MessageInfo> messageList;
//    private List<SongUserInfo> songUserList;
//    private List<SongFeelInfo> songFeelList;
//    private List<SongCommentInfo> songCommentList;
    private List<FollowInfo> userSender;
    private List<FollowInfo> userRecipient;
    private List<PostsInfo> postsList;
    private List<SongInfo> songList;
    private List<ListSongInfo> listSongInfoList;
}
