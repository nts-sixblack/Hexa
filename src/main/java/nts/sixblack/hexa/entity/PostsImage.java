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
@Table(name = "postsImage")
public class PostsImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postsImageId;
    @Column(columnDefinition = "varchar(255)")
    private String image;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "postsId")
    Posts posts;
}
