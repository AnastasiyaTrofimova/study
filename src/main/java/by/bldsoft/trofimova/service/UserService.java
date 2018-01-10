package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    List<User> findAll();
    User save(User user);
    User update(Long userId, User user);
    User findById(Long userId);
    User delete(Long userId);

}
