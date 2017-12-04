package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.entity.User;
import by.bldsoft.trofimova.repository.MessageRepository;
import by.bldsoft.trofimova.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
       /* Message one = messageRepository.getOne(23);
        user.getMessages().add(one);*/
        return userRepository.save(user);
    }

    @Override
    public User findById(Long user_id) {
        return userRepository.findById(user_id);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }
}
