package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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
//    @Column(columnDefinition = "ntext")
    private String firstName;
//    @Column(columnDefinition = "ntext")
    private String lastName;
    @Column(columnDefinition = "varchar(255)")
    private String avatar;
    @Column(columnDefinition = "varchar(255)")
    private String background;
    private String email;
    private String password;
//    @Column(columnDefinition = "ntext")
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
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<PostsUser> postsUserList;

    @OneToMany(mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<PostsFeel> postsFeelList;

    @OneToMany(mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<PostsComment> postsCommentList;

    @OneToMany(mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<UserChannel> userChannelList;

    @OneToMany(mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<Message> messageList;

    @OneToMany(mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<SongUser> songUserList;

    @OneToMany(mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<SongFeel> songFeelList;

    @OneToMany(mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<SongComment> songCommentList;

    @OneToMany(mappedBy = "userSender")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<Follow> userSenderList;

    @OneToMany(mappedBy = "userRecipient")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<Follow> userRecipientList;

    @OneToMany(mappedBy = "user1")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<Contact> contact1;

    @OneToMany(mappedBy = "user2")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<Contact> contact2;

    @OneToMany(mappedBy = "user")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<ListSong> listSongList;

}
