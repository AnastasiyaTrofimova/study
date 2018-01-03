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
@Table(name = "Role")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,
        scope = Role.class, property = "roleId")
@EqualsAndHashCode(of = "roleId")
public class Role implements java.io.Serializable{
    @Id
    @Column(name = "roleId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "role")
    public List<User> users;
}
