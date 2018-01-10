package by.bldsoft.trofimova.controller;

import by.bldsoft.trofimova.config.PersistenceConfig;
import by.bldsoft.trofimova.config.SecurityConfig;
import by.bldsoft.trofimova.config.WebConfig;
import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.entity.User;
import by.bldsoft.trofimova.repository.UserRepository;
import by.bldsoft.trofimova.service.UserService;
import config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = {TestConfig.class, PersistenceConfig.class, SecurityConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
@WebAppConfiguration
public class HelloControllerTest {

    public MockMvc mockMvc;

    @InjectMocks
    UserService forUserService;

    @Mock
    UserRepository forUserRepository;

    @Autowired
    public WebApplicationContext wc;

    @Autowired
    private UserService userService;

    @Test
    public void crud() throws Exception {

        String username = "ivan";
        String surname = "Ivanov";

        User u = new User();

        u.setUsername(username);
        u.setSurname(surname);

        User savedUser = userService.save(u);

        Long userId = savedUser.getUserId();

        Assert.assertEquals(username, savedUser.getUsername());
        Assert.assertEquals(surname, savedUser.getSurname());

        User userFromDB = userService.findById(userId);

        Assert.assertEquals(username, userFromDB.getUsername());
        Assert.assertEquals(surname, userFromDB.getSurname());

        User updaterUser = userService.update(userId, userFromDB);

        String usernameNew = "Semen";
        String surnameNew = "Semenov";

        updaterUser.setUsername(usernameNew);
        updaterUser.setSurname(surnameNew);

        Assert.assertEquals(usernameNew, updaterUser.getUsername());
        Assert.assertEquals(surnameNew, updaterUser.getSurname());

        userService.save(updaterUser);

        userService.delete(userId);
        User deleted = userService.findById(userId);
        Assert.assertNull("null",deleted);
    }

    @Test
    public void save() throws Exception {

        User use = mock(User.class);
        Message mes = mock(Message.class);
        when(use.getUsername()).thenReturn("ivan");
        when(use.getSurname()).thenReturn("Ivanov");
        when(mes.getDescription()).thenReturn("Hello");
        String finish = use.getUsername() + use.getSurname() + mes.getDescription();
        assertEquals("ivanIvanovHello", finish);
    }

    @Before
    public void before(){
       mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }

    @Test
    public void findAllS() throws Exception{

        User first = new User.Builder()
                .builder("Ivan", "Ivanov")
                .build();
        User second = new User.Builder()
                .builder("Petr", "Petrov")
                .build();
        User third = new User.Builder()
                .builder("Semen", "Semenov")
                .build();
        User fourth = new User.Builder()
                .builder("Vasya", "Vasilev")
                .build();

        userService.save(first);
        userService.save(second);
        userService.save(third);
        userService.save(fourth);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(4)))
//                .andExpect(jsonPath("$[0].userId", is(1)))
//                .andExpect(jsonPath("$[0].username", is("Ivan")))
//                .andExpect(jsonPath("$[0].surname", is("Ivanov")))
//                .andExpect(jsonPath("$[1].userId", is(2)))
//                .andExpect(jsonPath("$[1].username", is("Petr")))
//                .andExpect(jsonPath("$[1].surname", is("Petrov")))
//                .andExpect(jsonPath("$[2].userId", is(3)))
//                .andExpect(jsonPath("$[2].username", is("Semen")))
//                .andExpect(jsonPath("$[2].surname", is("Semenov")))
//                .andExpect(jsonPath("$[3].userId", is(4)))
//                .andExpect(jsonPath("$[3].username", is("Vasya")))
//                .andExpect(jsonPath("$[3].surname", is("Vasilev")))
                  .andReturn().getResponse().getContentAsString();
        verify(userService, times(1)).findAll();
    }

}
