package by.bldsoft.trofimova.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Long messageId;

    private String description;

    private List<Long> tagsHome;

    private List<Long> tagsWork;

}


