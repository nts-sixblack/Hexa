package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long channelId;
//    @Column(columnDefinition = "ntext")
    private String name;
//    @Column(columnDefinition = "ntext")
    private String decription;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @OneToMany(mappedBy = "channel")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<UserChannel> userChannelList;

    @OneToMany(mappedBy = "channel")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    List<Message> messageList;

    @OneToOne(mappedBy = "channel")
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    Contact contact;
}
