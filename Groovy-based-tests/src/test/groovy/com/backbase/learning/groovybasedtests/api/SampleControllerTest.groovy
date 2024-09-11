package com.backbase.learning.groovybasedtests.api

import com.backbase.learning.groovybasedtests.domain.User
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@Transactional
class SampleControllerTest extends Specification {


    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    def MockMvc mockMvc;

    def setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }


     def "one plus one equals to two"() {
         expect:
         a + b == c

         where:
         a | b | c
         1 | 1 | 2
         1 | 2 | 3
     }


    def "should return user details with id 1"() {

        ObjectMapper objectMapper = new ObjectMapper();

        given:
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createNewUser()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        when:
        ResponseEntity<?> response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()


        then:
        response.getStatusCode() == HttpStatus.OK
        def userFromDB = objectMapper.readValue(response.getBody().toString(), User.class)
        userFromDB.getEmail() == 'lol1@hex.com'

    }

    String createNewUser() {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setFName("Parth");
        user.setLName("Prajapati");
        user.setEmail("lol@Hex.com");
        return mapper.writeValueAsString(user);
    }


}
