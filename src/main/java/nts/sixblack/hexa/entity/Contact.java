package nts.sixblack.hexa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contactId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @OneToOne
    @JoinColumn(name = "channelId")
    Channel channel;

    @ManyToOne
    @JoinColumn(name = "userId1")
    User user1;

    @ManyToOne
    @JoinColumn(name = "userId2")
    User user2;

}
