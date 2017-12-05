package by.bldsoft.trofimova.controller;

import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.entity.User;
import by.bldsoft.trofimova.service.MessageService;
import by.bldsoft.trofimova.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class HelloController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;


    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Void> newUser(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @GetMapping("/messages")
    public List<Message> findAllMes(){
        return messageService.findAll();
    }

    @GetMapping("/{id}/messages")
    public Message findByMesId(@PathVariable Long id){
        return messageService.findByMesId(id);
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> newMessage(@RequestBody Message message){
        messageService.save(message);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/messages")
    public ResponseEntity<Void> updateMessage(@RequestBody Message message){
        messageService.save(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/messages")
    public void deleteMessage(@PathVariable Long id){
        messageService.delete(id);
    }

}


