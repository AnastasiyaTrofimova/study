package by.bldsoft.trofimova.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

   // @JsonProperty("messageId")
    private Long messageId;

   // @JsonProperty("description")
    private String description;

   // @JsonProperty("tagsHome")
    private List <Long> tagsHome;

   // @JsonProperty("tagsWork")
    private List <Long> tagsWork;



}


