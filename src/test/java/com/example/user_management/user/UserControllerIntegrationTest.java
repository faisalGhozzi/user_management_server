package com.example.user_management.user;

import com.example.user_management.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:"+this.port;
    }

    @Test
    public void testGetAllUsers(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getBaseUrl() + "/users",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserById(){
        User user = testRestTemplate.getForObject(getBaseUrl() + "/users/1", User.class);
        assertNotNull(user);
    }

    @Test
    public void testCreateUser(){
        User user = new User(
                "Eric",
                "Clapton",
                "eric.clapton@gmail.com",
                LocalDate.of(1945, Month.MARCH, 30),
                LocalDate.now()
        );

        ResponseEntity<User> userResponseEntity = testRestTemplate.postForEntity(getBaseUrl() + "/users", user, User.class);
        assertNotNull(userResponseEntity);
        assertNotNull(userResponseEntity.getBody());
    }

    @Test
    public void testUpdateUser(){
        int id = 1;
        User user = testRestTemplate.getForObject(getBaseUrl() + "/users/" + id, User.class);
        user.setFirstname("Jimi");
        user.setLastname("Hendrix");
        user.setEmail("jimi.hendrix@gmail.com");
        user.setBirthday(LocalDate.of(1942, Month.NOVEMBER, 27));
        testRestTemplate.put(getBaseUrl() + "/users/" + id, user);
        User updatedUser = testRestTemplate.getForObject(getBaseUrl() + "/users/" + id, User.class);
        assertNotNull(updatedUser);
    }
}
