package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.entity.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<Message> findAll();
    Message save(Long userId, MessageDTO messageDTO);
    Message saveAndFlush(Long userId, MessageDTO messageDTO, Long messageId);
    Message findByMesId(Long messageId);
    void delete(Long messageId);


}
