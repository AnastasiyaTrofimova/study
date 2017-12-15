package by.bldsoft.trofimova.repository;

import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.entity.User;
import liquibase.datatype.core.VarcharType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAll();

    Message save(Message message);

    Message saveAndFlush(Message message);

    @Query(value = "select message from Message message where message.id = :id")
    Message findByMesId(@Param("id") Long messageId);

    @Query(value = "select message from Message message where message.messageId=:messageId and message.user.userId=:userId")
    Message find(@Param ("messageId") Long messageId, @Param ("userId") Long userId);

    void delete(Long id);
}
