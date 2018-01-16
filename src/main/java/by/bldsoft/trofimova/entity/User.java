package by.bldsoft.trofimova.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "User.message", attributeNodes = {@NamedAttributeNode("messages")}),
        @NamedEntityGraph(name = "User.role", attributeNodes = {@NamedAttributeNode("role")})
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
        scope = User.class, property = "userId")
@EqualsAndHashCode(of = "userId")
public class User implements java.io.Serializable{

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @JsonProperty("username")
    @Column(name = "username")
    private String username;

    @JsonProperty("surname")
    @Column(name = "surname")
    private String surname;

    @JsonProperty("phone")
    @Column(name = "phone")
    private String phone;

    @JsonProperty("password")
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    public List<Message> messages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    public Role role;

    public static class Builder {

        private User built;

        public Builder builder(String username, String surname) {
            built = new User();
            built.username = username;
            built.surname = surname;
            return this;
        }

        public User build() {
            return built;
        }
    }
}



