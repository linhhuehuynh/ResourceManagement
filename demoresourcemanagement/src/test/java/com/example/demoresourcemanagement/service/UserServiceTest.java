//package com.example.demoresourcemanagement.service;
//
//import com.example.demoresourcemanagement.dao.UserDao;
//import com.example.demoresourcemanagement.entity.User;
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
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//public class UserServiceTest {
//
//    @TestConfiguration
//    static class UserServiceTestContextConfiguration {
//        @Bean
//        public UserService userService() {
//            return new UserService();
//        }
//    }
//
//    @Autowired
//    private UserService userService;
//
//    @MockBean
//    private UserDao userDao;
//
//    @Before
//    public void setUp() {
//        User user1 = new User();
//        user1.setId(1);
//        user1.setProfilePic("myProfile.jpg");
//        user1.setRole("manager");
//        Optional<User> userOptional1 = Optional.of(user1);
//        Mockito.when(userDao.findById(user1.getId())).thenReturn(userOptional1);
//
//        User user2 = new User();
//        user2.setId(2);
//        user2.setProfilePic("YourProfile.jpg");
//        user2.setRole("staff");
//        Optional<User> userOptional2 = Optional.of(user2);
//        Mockito.when(userDao.findById(user2.getId())).thenReturn(userOptional2);
//
//        List<User> userList = new ArrayList<>();
//        userList.add(user1);
//        userList.add(user2);
//        Mockito.when((userDao.findAll())).thenReturn(userList);
//    }
//
//    @Test
//    public void getUserById() {
//        /* right case*/
//        int id = 1;
//        String prof = "myProfile.jpg";
//        String role = "manager";
//        String prof2 = "YourProfile.jpg";
//        String role2 = "staff";
//        ResponseEntity<?> entity = userService.getUserById(id);
//        User found = (User)entity.getBody();
//        assertThat(found.getId()).isEqualTo(id);
//        assertThat(found.getProfilePic()).isEqualTo(prof);
//        assertThat(found.getRole()).isEqualTo(role);
//
//        /* wrong case*/
//        id = 2;
//        entity = userService.getUserById(id);
//        found = (User)entity.getBody();
//        assertThat(found.getId()).isEqualTo(2);
//        assertThat(found.getProfilePic()).isNotEqualTo(prof);
//        assertThat(found.getRole()).isNotEqualTo(role);
//        assertThat(found.getProfilePic()).isEqualTo(prof2);
//        assertThat(found.getRole()).isEqualTo(role2);
//
//        /* null case*/
//        id = 3;
//        entity = userService.getUserById(id);
//        HttpStatus hs = entity.getStatusCode();
//        assertThat(hs).isEqualTo(HttpStatus.NOT_FOUND);
//
//
//    }
//
//    @Test
//    public void setUserById() {
//        /* Right case */
//        // Given
//        int id = 1;
//        User user1 = new User();
//        user1.setProfilePic("myProfile.jpg");
//        user1.setRole("manager");
//        // When
//        ResponseEntity<?> entity = userService.setUserById(id, user1);
//        HttpStatus hs = entity.getStatusCode();
//        // Actual
//        assertThat(hs).isEqualTo(HttpStatus.OK);
//
//        /* null case */
//        // Given
//        int id1 = 3;
//        User user2 = new User();
//        user2.setProfilePic("YourProfile.jpg");
//        user2.setRole("staff");
//        // When
//        ResponseEntity<?> entity1 = userService.setUserById(id1, user2);
//        HttpStatus hs1 = entity1.getStatusCode();
//        // Actual
//        assertThat(hs1).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//
//    @Test
//    public void deleteUserById() {
//        /* Right case */
//        // Given
//        int id = 1;
//        // When
//        ResponseEntity<?> entity = userService.deleteUserById(id);
//        HttpStatus hs = entity.getStatusCode();
//        // Actual
//        assertThat(hs).isEqualTo(HttpStatus.OK);
//
//        /* null case */
//        // Given
//        int id1 = 3;
//        // When
//        ResponseEntity<?> entity1 = userService.deleteUserById(id1);
//        HttpStatus hs1 = entity1.getStatusCode();
//        // Actual
//        assertThat(hs1).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    public void getUserAll() {
//        // Give
//        User user1 = new User();
//        user1.setId(1);
//        user1.setProfilePic("myProfile.jpg");
//        user1.setRole("manager");
//        User user2 = new User();
//        user2.setId(2);
//        user2.setProfilePic("YourProfile.jpg");
//        user2.setRole("staff");
//
//        // When
//        ResponseEntity<?> entity = userService.getUserAll();
//        List<User> userList = (List<User>) entity.getBody();
//
//        // Actual
//        assertThat(userList.get(0).getId()).isEqualTo(user1.getId());
//        assertThat(userList.get(1).getId()).isEqualTo(user2.getId());
//        assertThat(userList.get(0).getProfilePic()).isEqualTo(user1.getProfilePic());
//        assertThat(userList.get(1).getProfilePic()).isEqualTo(user2.getProfilePic());
//        assertThat(userList.get(0).getRole()).isEqualTo(user1.getRole());
//        assertThat(userList.get(1).getRole()).isEqualTo(user2.getRole());
//        assertThat(userList.size()).isEqualTo(2);
//    }
//}
