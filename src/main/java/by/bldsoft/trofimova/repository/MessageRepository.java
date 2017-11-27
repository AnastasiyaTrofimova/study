package by.bldsoft.trofimova.repository;

import by.bldsoft.trofimova.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAll();

    Message save(Message message);

    @Query(value = "select message from Message message where message.id = :id")
    Message findByMesId(@Param("id") Long message_id);

    void delete(Long id);
}
