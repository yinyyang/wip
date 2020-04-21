package com.springcloud.support;

import com.springcloud.support.model.auth.User;
import com.springcloud.support.web.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
class SupportApplicationTests {
    @Autowired
    private UserController userController;


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;



    @BeforeEach
    public void  setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void contextLoads() {

    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(formLogin("/user/login")
                        .user("username", "test@sph.com")
                        .password("password", "123456"));
    }

    @Test
    public void testUserController() throws Exception {
        User user = new User();
        user.setUsername("test@sph.com");
        user.setPassword("123456");
        user.setPasswordConfirm("123456");
        userController.registration(user);
    }

}
