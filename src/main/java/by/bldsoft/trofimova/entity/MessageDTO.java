package by.bldsoft.trofimova.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Long messageId;

    private String description;

    private Set<Long> tagsHome;

    private Set<Long> tagsWork;

}


