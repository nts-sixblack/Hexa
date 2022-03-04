package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "postsId")
    Posts posts;
}
