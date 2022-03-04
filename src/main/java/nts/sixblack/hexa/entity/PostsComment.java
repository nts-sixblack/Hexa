package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "postsComment")
public class PostsComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postsCommentId;
    @Column(columnDefinition = "ntext")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "postsId")
    Posts posts;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}
