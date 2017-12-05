package by.bldsoft.trofimova.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Message")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
        scope = Message.class, property="messageId")
@EqualsAndHashCode(of = "messageId")
public class Message implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")

    private Long messageId;

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, updatable = false)
    public User user;

    @ManyToMany(mappedBy = "message", fetch = FetchType.LAZY)
    public List<TagHome> tagHome;

    @ManyToMany(mappedBy = "message", fetch = FetchType.LAZY)
    public List<TagWork> tagWork;

}






