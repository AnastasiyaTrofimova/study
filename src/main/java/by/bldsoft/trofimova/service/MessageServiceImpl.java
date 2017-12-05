package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message findByMesId(Long message_id) {
        return messageRepository.findByMesId(message_id);
    }

    @Override
    public void delete(Long id) {
        messageRepository.delete(id);
    }

}
