package by.bldsoft.trofimova.controller;

import by.bldsoft.trofimova.config.PersistenceConfig;
import by.bldsoft.trofimova.config.SecurityConfig;
import by.bldsoft.trofimova.config.WebConfig;
import by.bldsoft.trofimova.entity.Message;
import by.bldsoft.trofimova.entity.User;
import by.bldsoft.trofimova.service.UserService;
import config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static config.TestConfig.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = {TestConfig.class, PersistenceConfig.class, SecurityConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
@WebAppConfiguration
public class HelloControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public WebApplicationContext wc;

    @Autowired
    private UserService userService;

    @Test
    public void crud() throws Exception {

        String username = "ivan";
        String surname = "Ivanov";
        String mess = "hello";

        User u = new User();
        Message m = new Message();

        m.setDescription(mess);
        m.setUser(u);

        u.setUsername(username);
        u.setSurname(surname);
        u.getMessages().add(m);

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
        Assert.assertNull(deleted);
    }

    @Test
    public void save() throws Exception {

        userService = mock(UserService.class);

            String username = "petr";
            String surname = "Petrov";
            String mess = "hello";

            User u = new User();
            Message m = new Message();

            m.setDescription(mess);
            m.setUser(u);

            u.setUsername(username);
            u.setSurname(surname);
            u.getMessages().add(m);

        when(userService.save(any(User.class))).thenReturn(u);
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wc)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "ivan", password = "1234")
    public void findAll() throws Exception {

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
        mockMvc.perform(post("/users")
                .content(asJsonString(first))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();


        mockMvc.perform(post("/users")
                .content(asJsonString(second))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();


        mockMvc.perform(post("/users")
                .content(asJsonString(third))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();


        mockMvc.perform(post("/users")
                .content(asJsonString(fourth))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
        System.out.println(fourth);


        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
        System.out.println(fourth);
    }
}
