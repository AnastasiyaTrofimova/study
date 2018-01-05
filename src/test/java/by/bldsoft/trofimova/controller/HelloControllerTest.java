package by.bldsoft.trofimova.controller;

import by.bldsoft.trofimova.config.DBConfig;
import by.bldsoft.trofimova.config.PersistenceConfig;
import by.bldsoft.trofimova.config.SecurityConfig;
import by.bldsoft.trofimova.config.WebConfig;
import by.bldsoft.trofimova.entity.User;
import by.bldsoft.trofimova.service.UserService;
import config.TestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = {TestConfig.class, PersistenceConfig.class, SecurityConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
@WebAppConfiguration
public class HelloControllerTest {

//    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

//    EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
//            .setType(EmbeddedDatabaseType.H2)
//            .setScriptEncoding("UTF-8")
//            .ignoreFailedDrops(true)
//            .build();

//    @Before
//    public void setUp() {
//
//       /* db = new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
//                .addDefaultScripts()
//                .build();  ////????*/
//    }

    @Test
    public void findAll() throws Exception {
        List<User> all = userService.findAll();
        System.out.println(all);

//        UserService userService = mock(UserService.class);

//        mockMvc.perform(get("/"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("user", hasProperty("userId")))
//                .andExpect(model().attribute("user", hasProperty("username")))
//                .andExpect(model().attribute("user", hasProperty("surname")))
//                .andExpect(model().attribute("user", hasProperty("phone")));
    }


//    @After
//    public void tearDown() {
//        db.shutdown();
//    }

}