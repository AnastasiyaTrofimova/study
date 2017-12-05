package by.bldsoft.trofimova.service;

import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.entity.User;
import by.bldsoft.trofimova.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {

        List<Message> messages = user.getMessages();

        for (Message message : messages) {
            message.setUser(user);
        }

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
