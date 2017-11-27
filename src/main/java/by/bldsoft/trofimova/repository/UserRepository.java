package by.bldsoft.trofimova.repository;

import by.bldsoft.trofimova.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findAll();

    User save(User user);

    @Query(value = "select user from User user where user.id = :id")
    User findById(@Param("id") Long id);

    void delete(Long id);
}
