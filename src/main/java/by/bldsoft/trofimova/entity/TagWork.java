package by.bldsoft.trofimova.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TagWork")
@NamedEntityGraph(name = "TagWork.message", attributeNodes = {@NamedAttributeNode("message")})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
        scope = TagWork.class, property = "tagId")
@EqualsAndHashCode(of = "tagId")
    public class TagWork implements java.io.Serializable{
        @Id
        @Column(name = "tagId")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long  tagId;

        @Column(name = "tagImportant")
        private String tagImportant;

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "MessageTagWork",
                joinColumns = @JoinColumn(name = "tagId"),
                inverseJoinColumns = @JoinColumn(name = "messageId"))
        public List<Message> message;

}
