package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.*;
import by.bldsoft.trofimova.repository.MessageRepository;
import by.bldsoft.trofimova.repository.TagsHomeRepository;
import by.bldsoft.trofimova.repository.TagsWorkRepository;
import by.bldsoft.trofimova.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagsHomeRepository tagsHomeRepository;

    @Autowired
    private TagsWorkRepository tagsWorkRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message save(Long userId, MessageDTO messageDTO) {
        Message message = new Message();

        message.setUser(userRepository.findById(userId));
        message.setDescription(messageDTO.getDescription());

        List<Long> tagsHomeIds = messageDTO.getTagsHome();
        List<TagHome> tagHomes = tagsHomeRepository.findAll(tagsHomeIds);
        message.setTagHome(tagHomes);

        List<Long> tagsWorkIds = messageDTO.getTagsWork();
        List<TagWork> tagWorks = tagsWorkRepository.findAll(tagsWorkIds);
        message.setTagWork(tagWorks);

        Message createdMessage = messageRepository.save(message);

//        createdMessage.setMessageId(message.getMessageId());
//        createdMessage.setDescription(message.getDescription());
//        createdMessage.setTagHome(message.getTagHome());
//        createdMessage.setTagWork(message.getTagWork());

        messageDTO.setMessageId(createdMessage.getMessageId());
        messageDTO.setDescription(createdMessage.getDescription());

        List<Long> ll = createdMessage.getTagHome().stream()
                .map(tagHome -> tagHome.getTagId())
                .collect(Collectors.toList());
        messageDTO.setTagsHome(ll);

        List<Long> lo = createdMessage.getTagWork().stream()
                .map(tagWork -> tagWork.getTagId())
                .collect(Collectors.toList());
         messageDTO.setTagsWork(lo);

        return message;
    }

    @Override
    public Message update(Long userId, MessageDTO messageDTO, Long messageId) {

        Message message = messageRepository.find(messageId, userId);

        message.setDescription(messageDTO.getDescription());

        List<Long> tagsHomeIds = messageDTO.getTagsHome();
        List<TagHome> tagHomes = tagsHomeRepository.findAll(tagsHomeIds);
        message.setTagHome(tagHomes);

        List<Long> tagsWorkIds = messageDTO.getTagsWork();
        List<TagWork> tagWorks = tagsWorkRepository.findAll(tagsWorkIds);
        message.setTagWork(tagWorks);

        Message createdMessage = messageRepository.save(message);

        messageDTO.setMessageId(createdMessage.getMessageId());
        messageDTO.setDescription(createdMessage.getDescription());

        List<Long> ll = createdMessage.getTagHome().stream()
                .map(tagHome -> tagHome.getTagId())
                .collect(Collectors.toList());
        messageDTO.setTagsHome(ll);

        List<Long> lo = createdMessage.getTagWork().stream()
                .map(tagWork -> tagWork.getTagId())
                .collect(Collectors.toList());
        messageDTO.setTagsWork(lo);

        return message;

    }


    @Override
    public Message findByMesId(Long messageId) {
        return messageRepository.findByMesId(messageId);
    }


    @Override
    public void delete(Long messageId) {
        messageRepository.delete(messageId);
    }

}
