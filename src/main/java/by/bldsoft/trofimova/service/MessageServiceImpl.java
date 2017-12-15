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
        List<TagHome> th = new ArrayList<>(tagHomes);
        message.setTagHome(th);

        List<Long> tagsWorkIds = messageDTO.getTagsWork();
        List<TagWork> tagWorks = tagsWorkRepository.findAll(tagsWorkIds);
        List<TagWork> tw = new ArrayList<>(tagWorks);
        message.setTagWork(tw);

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
    public Message saveAndFlush(Long userId, MessageDTO messageDTO, Long messageId) {

        Message message = messageRepository.find(messageId, userId);

        message.setDescription(messageDTO.getDescription());

        List<Long> tagsHomeIds = messageDTO.getTagsHome();
        List<TagHome> tagHomes = tagsHomeRepository.findAll(tagsHomeIds);
       /* Set<TagHome> th = new HashSet<>(tagHomes);
        message.setTagHome(th);*/

        List<Long> ww = new ArrayList<>(tagsHomeIds);
        List<TagHome> qq = new ArrayList<>(tagHomes);
        ww.retainAll(qq);
        message.setTagHome(qq);

        List<Long> tagsWorkIds = messageDTO.getTagsWork();
        List<TagWork> tagWorks = tagsWorkRepository.findAll(tagsWorkIds);
       /* Set<TagWork> tw = new HashSet<>(tagWorks);
        message.setTagWork(tw);*/

        List<Long> ee = new ArrayList<>(tagsWorkIds);
        List<TagWork> rr = new ArrayList<>(tagWorks);
        ee.retainAll(rr);
        message.setTagWork(rr);

        Message createdMessage = messageRepository.saveAndFlush(message);

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
