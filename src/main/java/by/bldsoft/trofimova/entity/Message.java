package by.bldsoft.trofimova.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, scope = Message.class, property="messageId")
//@ToString
@EqualsAndHashCode(of = "messageId")
public class Message implements java.io.Serializable{

//    @JsonProperty("message_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
   // @JsonSerialize(using = ToStringSerializer.class)
    private Long messageId;

  /*  @JsonProperty("user_id")
    @Column(name = "user_id")
    private Long user_id;*/

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    public User user;

}






