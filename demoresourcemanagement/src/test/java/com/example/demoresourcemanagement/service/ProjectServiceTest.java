//package com.example.demoresourcemanagement.service;
//
//import com.example.demoresourcemanagement.dao.ProjectDao;
//import com.example.demoresourcemanagement.entity.Project;
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
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//public class ProjectServiceTest {
//
//    @TestConfiguration
//    static class ProjectServiceTestContextConfiguration {
//
//        @Bean
//        public ProjectService projectService() {
//            return new ProjectService();
//        }
//    }
//
//    @Autowired
//    private ProjectService projectService;
//
//    @MockBean
//    private ProjectDao projectDao;
//
//
//    @Before
//    public void setUp() {
//
//    //Set up for getAllProjectsTest
//        Project projectOne = new Project();
//        Project projectTwo = new Project();
//        projectOne.setId(1);
//        projectTwo.setId(2);
//        projectOne.setName("Project 1");
//        projectTwo.setName("Project 2");
//        List<Project> list = new ArrayList<>(Arrays.asList(projectOne, projectTwo));
//
//        Mockito.when(projectDao.findAll()).thenReturn(list);
//
//    //Set up for getProjectByIdTest
//        Project project = new Project();
//        project.setId(1);
//        project.setName("Project 1");
//
//        Optional<Project> projectOptional = Optional.of(project);
//        Mockito.when(projectDao.findById(project.getId())).thenReturn(projectOptional);
//    }
//
//    @Test
//    public void getAllProjectsTest1(){
//        //Given
//        Project projectOne = new Project();
//        Project projectTwo = new Project();
//        projectOne.setId(1);
//        projectTwo.setId(2);
//        projectOne.setName("Project 1");
//        projectTwo.setName("Project 2");
//
//        List<Project> list = new ArrayList<>(Arrays.asList(projectOne, projectTwo));
//
//        //When
//        List<Project> found = projectService.getAllProjects();
//
//        //Actual
//        assertThat(found.get(0).getId()).isEqualTo(projectOne.getId());
//        assertThat(found.get(1).getId()).isEqualTo(projectTwo.getId());
//        assertThat(found.get(0).getName()).isEqualTo(projectOne.getName());
//        assertThat(found.get(1).getName()).isEqualTo(projectTwo.getName());
//        assertThat(found.size()==list.size());
//    }
//
//    @Test
//    public void getAllProjectsTest2(){
//        //Given
//        Project projectOne = new Project();
//        List<Project> list = new ArrayList<>(Arrays.asList(projectOne));
//
//        //When
//        List<Project> found = projectService.getAllProjects();
//
//        //Actual
//        assertThat(found.size()).isNotEqualTo(list.size());
//    }
//
//    @Test
//    public void getProjectByIdTest1() {
//        //Given
//        int id = 1;
//        String name = "Project 1";
//
//        //When
//        ResponseEntity<?> entity = projectService.getProjectById(id);
//        Project found =(Project)entity.getBody();
//
//        //Then
//        assertThat(found.getId()).isEqualTo(id);
//        assertThat(found.getName()).isEqualTo(name);
//    }
//
//    @Test
//    public void getProjectByIdTest2() {
//        //Given
//        int id = 2;
//
//        //When
//        ResponseEntity<?> entity = projectService.getProjectById(id);
//
//        //Then
//        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test //Update Project Name
//    public void updateProjectTest1() {
//        //Given
//        int id = 1;
//        String newName = "NewName";
//        Project project = new Project();
//        project.setName(newName);
//
//        //When
//        ResponseEntity<?> result = projectService.updateProject(project, id);
//
//        //Then
//        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//    @Test //Project Not Found when updating
//    public void updateProjectTest2() {
//        //Given
//        int id = 2;
//        String newName = "NewName";
//        Project project = new Project();
//        project.setName(newName);
//
//        //When
//        ResponseEntity<?> result = projectService.updateProject(project, id);
//
//        //Then
//        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//
//    }
//
//    @Test
//    public void deleteProjectByIdTest1() {
//        // Given
//        int id = 1;
//
//        // When
//        ResponseEntity<?> actual = projectService.deleteProjectById(id);
//
//        // Then
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//    @Test
//    public void deleteProjectByIdTest2() {
//        // Given
//        int id = 2;
//
//        // When
//        ResponseEntity<?> actual = projectService.deleteProjectById(id);
//
//        // Then
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    public void deleteAllProjectsTest() {
//        String response = "Deleted All Projects Successfully!";
//
//        //When
//        ResponseEntity <?> actual = projectService.deleteAllProjects();
//
//        //Then
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(actual.getBody()).isEqualTo(response);
//    }
//
//}