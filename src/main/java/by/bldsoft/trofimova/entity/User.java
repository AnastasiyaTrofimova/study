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
@Table(name = "user")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, scope = User.class, property = "userId")
//@ToString
@EqualsAndHashCode(of = "userId")
public class User implements java.io.Serializable{

//    @JsonProperty("user_id")
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @JsonProperty("surname")
    @Column(name = "surname")
    private String surname;

    @JsonProperty("phone")
    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, orphanRemoval = true/*, cascade = CascadeType.ALL*/)
    public List<Message> messages;

    @OneToOne
    public Role role;

}


