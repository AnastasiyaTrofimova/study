package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.*;
import by.bldsoft.trofimova.repository.MessageRepository;
import by.bldsoft.trofimova.repository.TagsHomeRepository;
import by.bldsoft.trofimova.repository.TagsWorkRepository;
import by.bldsoft.trofimova.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        Set<Long> tagsHomeIds = messageDTO.getTagsHome();
        List<TagHome> tagHomes = tagsHomeRepository.findAll(tagsHomeIds);
        Set<TagHome> th = new HashSet<>(tagHomes);
        message.setTagHome(th);

        Set<Long> tagsWorkIds = messageDTO.getTagsWork();
        List<TagWork> tagWorks = tagsWorkRepository.findAll(tagsWorkIds);
        Set<TagWork> tw = new HashSet<>(tagWorks);
        message.setTagWork(tw);

        Message createdMessage = messageRepository.save(message);

        messageDTO.setMessageId(createdMessage.getMessageId());
        messageDTO.setDescription(createdMessage.getDescription());

        Set<Long> ll = createdMessage.getTagHome().stream()
                .map(tagHome -> tagHome.getTagId())
                .collect(Collectors.toSet());
        messageDTO.setTagsHome(ll);

        Set<Long> lo = createdMessage.getTagWork().stream()
                .map(tagWork -> tagWork.getTagId())
                .collect(Collectors.toSet());
        messageDTO.setTagsWork(lo);

        return message;
    }

    @Override
    @Transactional
    public Message saveAndFlush(Long userId, MessageDTO messageDTO, Long messageId) {

        Message message = new Message();

        Set<Long> tagsHomeIdsFromDTO = messageDTO.getTagsHome();
        List<TagHome> existingTagHomeFromDTO = tagsHomeRepository.findAll(tagsHomeIdsFromDTO);

        Message messageFromDB = messageRepository.find(messageId, userId);

        Set<TagHome> oldTagsHome = messageFromDB.getTagHome();

        Set<Long> existingTagHomeFromDTOIds = existingTagHomeFromDTO.stream()
                .map(tagHome -> tagHome.getTagId())
                .collect(Collectors.toSet());

        Set<Long> oldTagIdsHome = oldTagsHome.stream()
                .map(tagHome -> tagHome.getTagId())
                .collect(Collectors.toSet());

        HashSet<Long> newIdsHome = new HashSet<>(existingTagHomeFromDTOIds);
        newIdsHome.removeAll(oldTagIdsHome);

        HashSet<Long> deletedIdsHome = new HashSet<>(oldTagIdsHome);
        deletedIdsHome.removeAll(existingTagHomeFromDTOIds);

        messageFromDB.getTagHome().removeIf(tagHome -> deletedIdsHome.contains(tagHome.getTagId()));

        List<TagHome> newTagsHome = existingTagHomeFromDTO.stream()
                .filter(tagHome -> newIdsHome.contains(tagHome.getTagId()))
                .collect(Collectors.toList());

        messageFromDB.getTagHome().addAll(newTagsHome);

        Set<Long> tagsWorkIdsFromDTO = messageDTO.getTagsWork();
        List<TagWork> existingTagWorkFromDTO = tagsWorkRepository.findAll(tagsWorkIdsFromDTO);

        Set<TagWork> oldTags = messageFromDB.getTagWork();

        Set<Long> existingTagWorkFromDTOIds = existingTagWorkFromDTO.stream()
                .map(tagWork -> tagWork.getTagId())
                .collect(Collectors.toSet());

        Set<Long> oldTagIds = oldTags.stream()
                .map(tagWork -> tagWork.getTagId())
                .collect(Collectors.toSet());

        HashSet<Long> newIds = new HashSet<>(existingTagWorkFromDTOIds);
        newIds.removeAll(oldTagIds);

        HashSet<Long> deletedIds = new HashSet<>(oldTagIds);
        deletedIds.removeAll(existingTagWorkFromDTOIds);

        messageFromDB.getTagWork().removeIf(tagWork -> deletedIds.contains(tagWork.getTagId()));

        Set<TagWork> newTags = existingTagWorkFromDTO.stream()
                .filter(tagWork -> newIds.contains(tagWork.getTagId()))
                .collect(Collectors.toSet());

        messageFromDB.getTagWork().addAll(newTags);

        Message createdMessage = messageRepository.save(messageFromDB);

        messageDTO.setMessageId(createdMessage.getMessageId());
        messageDTO.setDescription(createdMessage.getDescription());

        Set<Long> tagHomeFinal = createdMessage.getTagHome().stream()
                .map(tagHome -> tagHome.getTagId())
                .collect(Collectors.toSet());
        messageDTO.setTagsHome(tagHomeFinal);

        Set<Long> tagWorkFinal = createdMessage.getTagWork().stream()
                .map(tagWork -> tagWork.getTagId())
                .collect(Collectors.toSet());
        messageDTO.setTagsWork(tagWorkFinal);

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
