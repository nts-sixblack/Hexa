package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
    @Column(columnDefinition = "ntext")
    private String caption;

    @OneToMany(mappedBy = "posts")
    List<PostsUser> postsUserList;

    @OneToMany(mappedBy = "posts")
    List<PostsImage> postsImageList;

    @OneToMany(mappedBy = "posts")
    List<PostsFeel> postsFeelList;

    @OneToMany(mappedBy = "posts")
    List<PostsComment> postsCommentList;
}
