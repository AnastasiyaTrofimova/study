package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    List<User> findAll();
    User save(User user);
    User saveAndFlush(Long userId, User user);
    User findById(Long userId);
    void delete(Long userId);
    org.springframework.security.core.userdetails.User findByUsername(String username);

}
