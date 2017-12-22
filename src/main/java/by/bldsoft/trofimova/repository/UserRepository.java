package by.bldsoft.trofimova.repository;

import by.bldsoft.trofimova.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{

    @EntityGraph(value = "User.message", type = EntityGraph.EntityGraphType.FETCH)
    List<User> findAll();

    User save(User user);

    User saveAndFlush(User user);

    @Query(value = "select user from User user where user.id = :id")
    User findById(@Param("id") Long messageId);

    void delete(Long id);

    //User findByNameAndPassword (@Param("username") String username, @Param("password") String password);

    org.springframework.security.core.userdetails.User findByUsername(String username);
}


