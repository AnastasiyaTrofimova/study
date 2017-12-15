package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.entity.MessageDTO;
import by.bldsoft.trofimova.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MessageService {

    List<Message> findAll();
    Message save(Long userId, MessageDTO messageDTO);
    Message saveAndFlush(Long userId, MessageDTO messageDTO, Long messageId);
    Message findByMesId(Long messageId);
    void delete(Long messageId);


}
