package com.backbase.learning.groovybasedtests.api

import com.backbase.learning.groovybasedtests.domain.User
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    def "setting up database with initial details"() {
        given:
        ObjectMapper objectMapper = new ObjectMapper();
        def userJson = objectMapper.writeValueAsString(new User(fname, lname, email))

        mockMvc.perform(post("/api/user")
                .contentType(APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk());

        where:
        fname   | lname       | email
        "Parth" | "Prajapati" | "lol@hex.com"
        "Yash"  | "Patel"     | "rofl@hex.com"
        "Ravi"  | "Agraval"   | "rofl1@hex.com"
        "Vikas" | "Jaiswal"   | "lol1@hex.com"
    }

    def "should return user details with id 1"() {

        expect:
        def response = mockMvc.perform(get("/api/user/1")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()

    }

    def "should return all the users in the database"() {
        def objectMapper = new ObjectMapper();
        def typeReference = new TypeReference<List<User>>() {}

        expect:
        def response = mockMvc.perform(get("/api/user")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect { content() != null }
                .andReturn()

        List<User> users = objectMapper.readValue(response.getResponse().getContentAsString(), typeReference)
        users.size() == 4
    }

    def "should update an existing user"() {
        given:
        def objectMapper = new ObjectMapper();
        def updatedUser = new User("Parth", "Prajapati", "actual@hex.com")
        def fetchResponse = mockMvc.perform(get("/api/user/byAttribute?email=lol@hex.com")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
        def userId = objectMapper.readValue(fetchResponse.getResponse().getContentAsString(), User.class).getId()

        when:
        def updateResponse = mockMvc.perform(put("/api/user/" + userId)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andReturn()

        then:
        updateResponse.getResponse().getContentAsString().contains("actual@hex.com")
    }

    def "should delete the existing user"() {
        given:
        def objectMapper = new ObjectMapper();

        def fetchResponse = mockMvc.perform(get("/api/user/byAttribute?email=actual@hex.com")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
        def userId = objectMapper.readValue(fetchResponse.getResponse().getContentAsString(), User.class).getId()


        expect:
        def deleteResponse = mockMvc.perform(delete("/api/user/" + userId))
                .andReturn()

        mockMvc.perform(get("/api/user/" + userId))
                .andExpect(status().isNotFound())
                .andReturn()

        deleteResponse.getResponse().getStatus() == HttpStatus.OK.value()
    }


    /*def "one plus one equals to two"() {
        expect:
        a + b == c

        where:
        a | b | c
        1 | 1 | 2
        1 | 2 | 3
    }*/
}
