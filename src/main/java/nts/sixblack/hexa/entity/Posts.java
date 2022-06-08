package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postsId;
//    @Column(columnDefinition = "ntext")
    private String caption;
    private String type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @OneToMany(mappedBy = "posts")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<PostsUser> postsUserList;

    @OneToMany(mappedBy = "posts")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<PostsImage> postsImageList;

    @OneToMany(mappedBy = "posts")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<PostsFeel> postsFeelList;

    @OneToMany(mappedBy = "posts")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<PostsComment> postsCommentList;
}
