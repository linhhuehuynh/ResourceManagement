//package com.example.demoresourcemanagement.service;
//
//import com.example.demoresourcemanagement.dao.UserCredentialDao;
//import com.example.demoresourcemanagement.entity.User;
//import com.example.demoresourcemanagement.entity.UserCredential;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//public class UserCredentialServiceTest {
//
//    @TestConfiguration
//    static class UserServiceTestContextConfiguration {
//        @Bean
//        public UserCredentialService userCredentialService() {
//            return new UserCredentialService();
//        }
//    }
//
//    @Autowired
//    private UserCredentialService userCredentialService;
//
//    @MockBean
//    private UserCredentialDao userCredentialDao;
//
//    @Before
//    public void setUp() {
//        User sean = new User("pic1", "manager");
//        UserCredential user1 = new UserCredential();
//        user1.setId(1);
//        user1.setUsername("Sean");
//        user1.setPassword("password");
//        user1.setUser(sean);
//        Optional<UserCredential> userOptional1 = Optional.of(user1);
//        Mockito.when(userCredentialDao.findById(user1.getId())).thenReturn(userOptional1);
//
//        User dan = new User("pic2", "staff");
//        UserCredential user2 = new UserCredential();
//        user2.setId(2);
//        user2.setUsername("Dan");
//        user2.setPassword("anotherPassword");
//        user2.setUser(dan);
//        Optional<UserCredential> userOptional2 = Optional.of(user2);
//        Mockito.when(userCredentialDao.findById(user2.getId())).thenReturn(userOptional2);
//    }
//
//    @Test
//    public void getUserCredentialById() {
//        User sean = new User("pic1", "manager");
//        User dan = new User("pic2", "staff");
//        /* right case*/
//
//        int id = 1;
//        String username = "Sean";
//        String password = "password";
//        ResponseEntity<?> entity = userCredentialService.getUserCredential(id);
//        UserCredential found = (UserCredential)entity.getBody();
//        assertThat(found.getId()).isEqualTo(id);
//        assertThat(found.getUsername()).isEqualTo(username);
//        assertThat(found.getPassword()).isEqualTo(password);
//
//        assertThat(found.getUser().getProfilePic()).isEqualTo(sean.getProfilePic());
//        assertThat(found.getUser().getRole()).isEqualTo(sean.getRole());
//
//        /* wrong case*/
//        id = 2;
//        entity = userCredentialService.getUserCredential(id);
//        found = (UserCredential)entity.getBody();
//        assertThat(found.getId()).isEqualTo(2);
//        assertThat(found.getUsername()).isNotEqualTo(username);
//        assertThat(found.getPassword()).isNotEqualTo(password);
//
//        assertThat(found.getUser().getProfilePic()).isEqualTo(dan.getProfilePic());
//        assertThat(found.getUser().getRole()).isEqualTo(dan.getRole());
//        assertThat(found.getUser().getProfilePic()).isNotEqualTo(sean.getProfilePic());
//        assertThat(found.getUser().getRole()).isNotEqualTo(sean.getRole());
//
//        /* null case*/
//        id = 3;
//        entity = userCredentialService.getUserCredential(id);
//        HttpStatus hs = entity.getStatusCode();
//        assertThat(hs).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    public void setUserCredential() {
//        /* Right case */
//        // Given
//        int id = 1;
//        User sean = new User("pic1", "manager");
//        UserCredential user1 = new UserCredential();
//        user1.setId(1);
//        user1.setUsername("Sean new");
//        user1.setPassword("password new");
//        user1.setUser(sean);
//        // When
//        ResponseEntity<?> entity = userCredentialService.setUserCredential(id, user1);
//        HttpStatus hs = entity.getStatusCode();
//        // Actual
//        assertThat(hs).isEqualTo(HttpStatus.OK);
//
//        /* null case */
//        // Given
//        int id1 = 3;
//        User dan = new User("pic2", "staff");
//        UserCredential user2 = new UserCredential();
//        user2.setId(2);
//        user2.setUsername("Dan");
//        user2.setPassword("anotherPassword");
//        user2.setUser(dan);
//        // When
//        ResponseEntity<?> entity1 = userCredentialService.setUserCredential(id1, user2);
//        HttpStatus hs1 = entity1.getStatusCode();
//        // Actual
//        assertThat(hs1).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//}
