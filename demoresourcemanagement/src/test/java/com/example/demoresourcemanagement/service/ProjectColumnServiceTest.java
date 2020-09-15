//package com.example.demoresourcemanagement.service;
//
//import com.example.demoresourcemanagement.dao.ProjectColumnDao;
//import com.example.demoresourcemanagement.dao.ProjectDao;
//import com.example.demoresourcemanagement.entity.Project;
//import com.example.demoresourcemanagement.entity.ProjectColumn;
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
//public class ProjectColumnServiceTest {
//
//    @TestConfiguration
//    static class ProjectColumnServiceTextContextConfiguration {
//        @Bean
//        public ProjectColumnService projectColumnService() {
//            return new ProjectColumnService();
//        }
//    }
//
//    @Autowired
//    private ProjectColumnService projectColumnService;
//
//    @MockBean
//    private ProjectColumnDao projectColumnDao;
//
//    @MockBean
//    private ProjectDao projectDao;
//
//    @Before
//    public void setUp() {
//
//        //Set up for getAllColumnsByProjectIdTest
//        ProjectColumn columnOne = new ProjectColumn();
//        ProjectColumn columnTwo = new ProjectColumn();
//        List<ProjectColumn> list = new ArrayList<>(Arrays.asList(columnOne, columnTwo));
//
//        Project project = new Project();
//        project.setId(1);
//        project.setProjectColumns(list);
//
//        Optional<List<ProjectColumn>> optionalProjectColumnList = Optional.of(list);
//        Mockito.when((projectDao.findById(project.getId()))).thenReturn(Optional.of(project));
//        Mockito.when(projectColumnDao.getAllByProjectId(project.getId())).thenReturn(optionalProjectColumnList);
//
//
//        //Set up for getProjectColumnByIdTest
//        ProjectColumn projectColumn = new ProjectColumn();
//        projectColumn.setId(1);
//        projectColumn.setColumnType("Text");
//        projectColumn.setProjectColumnName("Location");
//
//        Optional<ProjectColumn> projectColumnOptional = Optional.of(projectColumn);
//        Mockito.when(projectColumnDao.findById(projectColumn.getId())).thenReturn(projectColumnOptional);
//
//    }
//
//
//    @Test
//    public void getAllColumnsByProjectIdTest1(){
//        int id = 1;
//
//        ResponseEntity<?> found = projectColumnService.getAllColumnsByProjectId(id);
//
//        assertThat(found.hasBody());
//        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//    @Test
//    public void getAllColumnsByProjectIdTest2(){
//        int id = 2;
//        ResponseEntity<?> found = projectColumnService.getAllColumnsByProjectId(id);
//
//        assertThat(found.hasBody());
//        assertThat(found.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    public void getProjectColumnByIdTest1() {
//        //Given
//        int id = 1;
//        String type = "Text";
//        String name = "Location";
//
//        //When
//        ResponseEntity<?> column = projectColumnService.getProjectColumnById(id);
//        ProjectColumn found = (ProjectColumn)column.getBody();
//
//        //Then
//        assertThat(found.getId()).isEqualTo(id);
//        assertThat(found.getColumnType()).isEqualTo(type);
//        assertThat(found.getProjectColumnName()).isEqualTo(name);
//    }
//
//
//    @Test
//    public void getProjectColumnByIdTest2() {
//        //Given
//        int id = 2;
//
//        //When
//        ResponseEntity<?> entity = projectColumnService.getProjectColumnById(id);
//
//        //Then
//        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test //Update Column Name
//    public void updateTestColumn1() {
//        //Given
//        int id = 1;
//        String newName = "NewName";
//        ProjectColumn column = new ProjectColumn();
//        column.setProjectColumnName(newName);
//
//        //When
//        ResponseEntity<?> result = projectColumnService.updateColumn(column, id);
//        ProjectColumn actual = (ProjectColumn)result.getBody();
//
//        //Then
//        assertThat(actual.getProjectColumnName()).isEqualTo(newName);
//
//    }
//
//    @Test //Column Not Found when updating
//    public void updateTestColumn2() {
//        //Given
//        int id = 2;
//        String newName = "NewName";
//        ProjectColumn column = new ProjectColumn();
//        column.setProjectColumnName(newName);
//
//        //When
//        ResponseEntity<?> result = projectColumnService.updateColumn(column, id);
//
//        //Then
//        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//
//    }
//
//
//    @Test
//    public void deleteColumnById1() {
//        // Given
//        int id = 1;
//        String response = "Deleted Column Successfully!";
//
//        // When
//        ResponseEntity<?> actual = projectColumnService.deleteColumnById(id);
//
//        // Then
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(actual.getBody()).isEqualTo(response);
//    }
//
//    @Test
//    public void deleteColumnById2() {
//        // Given
//        int id = 2;
//        String response = "Column Not Found!";
//
//        // When
//        ResponseEntity<?> actual = projectColumnService.deleteColumnById(id);
//
//        // Then
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//        assertThat(actual.getBody()).isEqualTo(response);
//    }
//
//    @Test
//    public void deleteAllColumnsByProjectId1() {
//        //Given
//        int id = 1;
//        String response = "Deleted All Columns!";
//
//        //When
//        ResponseEntity<?> actual = projectColumnService.deleteAllColumnsByProjectId(id);
//
//        //Then
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(actual.getBody()).isEqualTo(response);
//    }
//
//    @Test
//    public void deleteAllColumnsByProjectId2() {
//        //Given
//        int id = 2;
//        String response = "Project OR Columns Not Found!";
//
//        //When
//        ResponseEntity<?> actual = projectColumnService.deleteAllColumnsByProjectId(id);
//
//        //Then
//        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//        assertThat(actual.getBody()).isEqualTo(response);
//    }
//}
//
