package by.bldsoft.trofimova.controller;

import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.entity.MessageDTO;
import by.bldsoft.trofimova.entity.User;
import by.bldsoft.trofimova.service.MessageService;
import by.bldsoft.trofimova.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @PostMapping
    public ResponseEntity<Void> newUser(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user){
        userService.update(userId, user);
        return ResponseEntity.ok().body(user);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId){
        userService.delete(userId);
    }

    @GetMapping("/{userId}/messages")
    public List<Message> findAllMes(){
        return messageService.findAll();
    }

    @GetMapping("/{userId}/messages/{messageId}")
    public Message findByMesId(@PathVariable Long messageId){
        return messageService.findByMesId(messageId);
    }

    @PostMapping("/{userId}/messages")
    public ResponseEntity<MessageDTO> newMessage(@PathVariable Long userId, @RequestBody MessageDTO messageDTO){
        messageService.save(userId, messageDTO);
        return ResponseEntity.ok().body(messageDTO);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{userId}/messages/{messageId}")
    public ResponseEntity<MessageDTO> updateMessage(@PathVariable Long userId, @RequestBody MessageDTO messageDTO, @PathVariable Long messageId){
        messageService.saveAndFlush(userId, messageDTO, messageId);
        return ResponseEntity.ok().body(messageDTO);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{userId}/messages/{messageId}")
    public void deleteMessage(@PathVariable Long messageId){
        messageService.delete(messageId);
    }

}


