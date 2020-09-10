package com.example.demoresourcemanagement.service;

import com.example.demoresourcemanagement.dao.UserCredentialDao;
import com.example.demoresourcemanagement.entity.User;
import com.example.demoresourcemanagement.entity.UserCredential;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserCredentialServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Bean
        public UserCredentialService userCredentialService() {
            return new UserCredentialService();
        }
    }

    @Autowired
    private UserCredentialService userCredentialService;

    @MockBean
    private UserCredentialDao userCredentialDao;

    @Before
    public void setUp() {
        User sean = new User("pic1", "manager");

        UserCredential user1 = new UserCredential();
        user1.setId(1);
        user1.setUsername("Sean");
        user1.setPassword("password");
        user1.setUser(sean);
        Optional<UserCredential> userOptional1 = Optional.of(user1);
        Mockito.when(userCredentialDao.findById(user1.getId())).thenReturn(userOptional1);

        User dan = new User("pic2", "staff");

        UserCredential user2 = new UserCredential();
        user2.setId(2);
        user2.setUsername("Dan");
        user2.setPassword("anotherPassword");
        user2.setUser(dan);
        Optional<UserCredential> userOptional2 = Optional.of(user2);
        Mockito.when(userCredentialDao.findById(user2.getId())).thenReturn(userOptional2);
    }

    @Test
    public void getUserCredentialById() {
        User sean = new User("pic1", "manager");
        User dan = new User("pic2", "staff");
        /* right case*/

        int id = 1;
        String username = "Sean";
        String password = "password";
        ResponseEntity<?> entity = userCredentialService.getUserCredential(id);
        UserCredential found = (UserCredential)entity.getBody();
        assertThat(found.getId()).isEqualTo(id);
        assertThat(found.getUsername()).isEqualTo(username);
        assertThat(found.getPassword()).isEqualTo(password);

        assertThat(found.getUser().getProfilePic()).isEqualTo(sean.getProfilePic());
        assertThat(found.getUser().getRole()).isEqualTo(sean.getRole());

        /* wrong case*/
        id = 2;
        entity = userCredentialService.getUserCredential(id);
        found = (UserCredential)entity.getBody();
        assertThat(found.getId()).isEqualTo(2);
        assertThat(found.getUsername()).isNotEqualTo(username);
        assertThat(found.getPassword()).isNotEqualTo(password);

        assertThat(found.getUser().getProfilePic()).isEqualTo(dan.getProfilePic());
        assertThat(found.getUser().getRole()).isEqualTo(dan.getRole());
        assertThat(found.getUser().getProfilePic()).isNotEqualTo(sean.getProfilePic());
        assertThat(found.getUser().getRole()).isNotEqualTo(sean.getRole());

    }

//    @Test
//    public void setUserCredential() {
//
//    }
}
