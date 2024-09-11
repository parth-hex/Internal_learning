package com.backbase.learning.groovybasedtests.api;

import com.backbase.learning.groovybasedtests.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spock.lang.Ignore;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@Transactional
@Ignore
public class SpringBasedIntegrationTest {


    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;


    @Test
    @DirtiesContext
    public void testFetchUserById() throws Exception {
        MockMvc mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createNewUser()))
                .andExpect(MockMvcResultMatchers.status().isOk());


        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


    }

    private String createNewUser() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setFName("Parth");
        user.setLName("Prajapati");
        user.setEmail("lol@Hex.com");
        return mapper.writeValueAsString(user);
    }
}
