Integration Tests with Spock Framework in Spring Boot

### 1. Introduction

   Overview of Integration Testing

Definition: Testing the interaction between components or systems to ensure they work together as expected.
Importance: Verifies end-to-end scenarios and system behavior in a real-world environment.
Why Spock Framework?

Groovy-based testing framework.
Features: Highly expressive syntax, powerful mocking/stubbing, and built-in data-driven testing.
Spring Boot and Testing

Spring Boot’s testing support.
Benefits of integration testing in a Spring Boot application.

### 2. Introduction to Spock Framework

   What is Spock?

A Groovy-based testing framework.
Provides BDD (Behavior-Driven Development) style testing.
Key Features of Spock

Clear and expressive syntax.
Built-in support for mocking and stubbing.
Data-driven testing with data tables.
Easy setup and configuration.

### 3. Setting Up Spock with Spring Boot

   Dependencies

Add Spock and Groovy dependencies to your pom.xml.

**Maven Dependency Configuration**


     <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    <!-- https://mvnrepository.com/artifact/org.spockframework/spock-core -->
        <dependency>
            <groupId>org.spockframework</groupId>
            <artifactId>spock-core</artifactId>
            <version>2.4-M4-groovy-4.0</version>
            <scope>test</scope>
        </dependency>

        <!-- Spock Spring Extension (if you need Spring support in Spock) -->
        <dependency>
            <groupId>org.spockframework</groupId>
            <artifactId>spock-spring</artifactId>
            <version>2.4-M4-groovy-4.0</version> <!-- Match the Spock version here -->
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.codehaus.groovy</groupId>
            <artifactId>groovy</artifactId>
            <version>3.0.18</version>
            <scope>test</scope>
        </dependency>


**Maven plugin Configuration**

            <plugin>
                <groupId>org.codehaus.gmavenplus</groupId>
                <artifactId>gmavenplus-plugin</artifactId>
                <version>1.5</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>testCompile</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.2</version>
                <executions>
                    <execution>
                        <id>default-test</id>
                        <phase>test</phase>
                        <goals>
                            <goal>test</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

Spock configuration in src/test/resources or as part of Spring Boot test configuration.
Be Mindful about keeping the groovy tests under the directory src/test/groovy , usually we are keeping tests under src/test/java.

### 4. Writing Integration Tests with Spock

   Basic Test Structure


    @SpringBootTest
    @AutoConfigureMockMvc
    class SampleIntegrationTest extends Specification {
    
        @Autowired
        MockMvc mockMvc
    
        def "should return user details with id 1"() {
            given:
            def userId = 1
    
            when:
            def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/${userId}"))
                                   .andExpect(MockMvcResultMatchers.status().isOk())
                                   .andReturn()
    
            then:
            response.response.contentAsString == '{"id":1,"name":"John Doe"}'
        }
    }


Mocking and Stubbing

groovy

    @SpringBootTest
    @AutoConfigureMockMvc
    class SampleIntegrationTest extends Specification {
    
        @MockBean
        UserService userService
    
        @Autowired
        MockMvc mockMvc
    
        def "should return user details with id 1"() {
            given:
            userService.getUserById(1) >> new User(1, "John Doe")
    
            when:
            def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/1"))
                                   .andExpect(MockMvcResultMatchers.status().isOk())
                                   .andReturn()
    
            then:
            response.response.contentAsString == '{"id":1,"name":"John Doe"}'
        }
    }

Data-Driven Testing

groovy

    @SpringBootTest
    @AutoConfigureMockMvc
    class UserControllerTest extends Specification {
    
        @Autowired
        MockMvc mockMvc
    
        @Unroll
        def "should return user details for id #userId"() {
            given:
            mockMvc.perform(MockMvcRequestBuilders.get("/api/user/$userId"))
                   .andExpect(MockMvcResultMatchers.status().isOk())
                   .andExpect(MockMvcResultMatchers.jsonPath('$.name').value(name))
    
            where:
            userId | name
            1      | "John Doe"
            2      | "Jane Doe"
        }
    }

### 5. Advanced Topics

   Error Handling in Tests

Verifying exceptions and error responses.
groovy

    @SpringBootTest
    @AutoConfigureMockMvc
    class ErrorHandlingTest extends Specification {
    
        @Autowired
        MockMvc mockMvc
    
        def "should return 404 for non-existent user"() {
            when:
            def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/999"))
                                   .andExpect(MockMvcResultMatchers.status().isNotFound())
                                   .andReturn()
    
            then:
            response.response.contentAsString.contains("User not found")
        }
    }

Integration with Other Frameworks

Using Spock with databases, queues, etc.
Configuration for testing with @DataJpaTest, @WebMvcTest, and more.

### 6. Best Practices

   Organizing Tests

Structuring test classes and methods for readability and maintainability.
Mocking vs. Real Services

When to use mocks and when to use real implementations.
Handling Test Data

Using fixtures, data builders, or test containers.

### 7. Conclusion

   Summary of Benefits

Concise and readable test code.
Powerful mocking and stubbing capabilities.
Integrated data-driven testing support.
Q&A

Open the floor for questions and discussions.

### 8. Resources and References

   Documentation

Spock Framework Documentation : https://spockframework.org/spock/docs/2.3/index.html
Reference Articles : 
- https://www.baeldung.com/spring-spock-testing 
- https://medium.com/@ThomasBem/testing-spring-boot-apps-with-spock-and-groovy-part-1-5099f314f9f5
- https://www.baeldung.com/groovy-language
