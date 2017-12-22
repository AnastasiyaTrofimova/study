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

    public User saveAndFlush(Long userId, User user) {
        User use = new User();
        use.setUserId(userId);
        use.setUsername(user.getUsername());
        use.setSurname(user.getSurname());
        use.setPhone(user.getPhone());
        use.setRole(user.getRole());
        use.setMessages(user.getMessages());

        user.setUserId(use.getUserId());
        user.setUsername(use.getUsername());
        user.setSurname(use.getSurname());
        user.setPhone(use.getPhone());
        user.setRole(use.getRole());
        user.setMessages(use.getMessages());

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    public void delete(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    public org.springframework.security.core.userdetails.User findByUsername(String username) {
        return userRepository.findByUsername(username);


        //findByNameAndPassword(username,password);
        //findByUsername(username);

    }
}
