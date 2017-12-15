package by.bldsoft.trofimova.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TagHome")
@NamedEntityGraph(name = "TagHome.message", attributeNodes = {@NamedAttributeNode("message")})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
        scope = TagHome.class, property = "tagId")
@EqualsAndHashCode(of = "tagId")
    public class TagHome implements java.io.Serializable{
        @Id
        @Column(name = "tagId")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long  tagId;

        @Column(name = "tagSimple")
        private String tagSimple;

        @ManyToMany(mappedBy = "tagHome", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        public List<Message> message;

}


