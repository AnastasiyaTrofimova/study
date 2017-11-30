package by.bldsoft.trofimova.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Role")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, scope = Role.class, property = "roleId")
@EqualsAndHashCode(of = "roleId")
public class Role implements Serializable {
    @Id
    @Column(name = "roleId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role")
    private String role;

    @Column(name = "userId")
    private Long userId;

    @OneToOne(mappedBy = "role")
    //@JoinColumn(name = "user_id")
    public User user;
}
