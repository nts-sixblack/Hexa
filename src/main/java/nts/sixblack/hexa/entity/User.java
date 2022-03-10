package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(columnDefinition = "ntext")
    private String firstName;
    @Column(columnDefinition = "ntext")
    private String lastName;
    @Column(columnDefinition = "varchar(255)")
    private String avatar;
    @Column(columnDefinition = "varchar(255)")
    private String background;
    private String email;
    private String password;
    @Column(columnDefinition = "ntext")
    private String name;
    private String phone;
    private boolean followStatus;

    public void setFollowStatus(boolean followStatus){
        this.followStatus = followStatus;
    }

    public boolean getFollowStatus(){
        return followStatus;
    }

    @OneToMany(mappedBy = "user")
    List<PostsUser> postsUserList;

    @OneToMany(mappedBy = "user")
    List<PostsFeel> postsFeelList;

    @OneToMany(mappedBy = "user")
    List<PostsComment> postsCommentList;

    @OneToMany(mappedBy = "user")
    List<UserChannel> userChannelList;

    @OneToMany(mappedBy = "user")
    List<Message> messageList;

    @OneToMany(mappedBy = "user")
    List<SongUser> songUserList;

    @OneToMany(mappedBy = "user")
    List<SongFeel> songFeelList;

    @OneToMany(mappedBy = "user")
    List<SongComment> songCommentList;

    @OneToMany(mappedBy = "userSender")
    List<Follow> userSenderList;

    @OneToMany(mappedBy = "userRecipient")
    List<Follow> userRecipientList;

    @OneToMany(mappedBy = "user1")
    List<Contact> contact1;

    @OneToMany(mappedBy = "user2")
    List<Contact> contact2;

    @OneToMany(mappedBy = "user")
    List<ListSong> listSongList;

}
