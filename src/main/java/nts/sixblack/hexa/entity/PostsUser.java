package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "postsUser")
public class PostsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postUserId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "postsId")
    Posts posts;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}
