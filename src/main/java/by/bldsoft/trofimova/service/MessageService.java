package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<Message> findAll();
    Message save(Message message);
    Message findByMesId(Long message_id);
    void delete(Long id);


}
