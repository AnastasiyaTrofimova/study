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
@Table(name = "TagHome")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, scope = TagHome.class, property = "tagId")
@EqualsAndHashCode(of = "tagId")
    public class TagHome implements java.io.Serializable{
        @Id
        @Column(name = "tagId")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long  tagId;

        @Column(name = "tagSimple")
        private String tagSimple;

        @Column(name = "messageId")
        private Long messageId;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinColumn(name = "messageId", insertable = false, updatable = false)
        public List<Message> message;

}


