//
//
//package com.example.demoresourcemanagement.service;
//
//import com.example.demoresourcemanagement.dao.ProjectDao;
//import com.example.demoresourcemanagement.dao.ResourceDao;
//import com.example.demoresourcemanagement.entity.Resource;
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
//public class ResourceServiceTest {
//
//    @TestConfiguration
//    static class ResourceServiceTestContextConfiguration {
//        @Bean
//        public ResourceService resourceService() {
//            return new ResourceService();
//        }
//    }
//
//    @Autowired
//    private ResourceService resourceService;
//
//    @MockBean
//    private ResourceDao resourceDao;
//
//    @MockBean
//    private ProjectDao projectDao;
//
//    @Before
//    public void setUp() {
//        Resource resource1 = new Resource();
//        resource1.setId(1);
//        resource1.setCode("0001");
//        resource1.setName("resource1");
//        Optional<Resource> resourceOptional1 = Optional.of(resource1);
//        Mockito.when(resourceDao.findById(resource1.getId())).thenReturn(resourceOptional1);
//
//        Resource resource2 = new Resource();
//        resource2.setId(2);
//        resource2.setCode("0002");
//        resource2.setName("resource2");
//        Optional<Resource> resourceOptional2 = Optional.of(resource2);
//        Mockito.when(resourceDao.findById(resource2.getId())).thenReturn(resourceOptional2);
//
//        List<Resource> resourceList = new ArrayList<>();
//        resourceList.add(resource1);
//        resourceList.add(resource2);
//        Mockito.when(resourceDao.findAll()).thenReturn(resourceList);
//    }
//
////    @Test
////    void addResource() {
////    }
////
////    @Test
////    void addResourceList() {
////    }
//
//    @Test
//    public void getAllResources() {
//        // Give
//        Resource resource1 = new Resource();
//        resource1.setId(1);
//        resource1.setCode("0001");
//        resource1.setName("resource1");
//        Resource resource2 = new Resource();
//        resource2.setId(2);
//        resource2.setCode("0002");
//        resource2.setName("resource2");
//
//        // When
//        ResponseEntity<?> entity = resourceService.getAllResources();
//        List<Resource> resourceList = (List<Resource>)entity.getBody();
//
//        // Actual
//        assertThat(resourceList.get(0).getId()).isEqualTo(resource1.getId());
//        assertThat(resourceList.get(1).getId()).isEqualTo(resource2.getId());
//        assertThat(resourceList.get(0).getName()).isEqualTo(resource1.getName());
//        assertThat(resourceList.get(1).getName()).isEqualTo(resource2.getName());
//        assertThat(resourceList.get(0).getCode()).isEqualTo(resource1.getCode());
//        assertThat(resourceList.get(1).getCode()).isEqualTo(resource2.getCode());
//        assertThat(resourceList.size()).isEqualTo(2);
//    }
//
//    @Test
//    public void getResourceById() {
//        /* Right case */
//        // Given
//        int id = 1;
//        String code = "0001";
//        String name = "resource1";
//        String code2 = "0002";
//        String name2 = "resource2";
//        // When
//        ResponseEntity<?> entity = resourceService.getResourceById(id);
//        Resource found = (Resource)entity.getBody();
//        // Actual
//        assertThat(found.getId()).isEqualTo(id);
//        assertThat(found.getCode()).isEqualTo(code);
//        assertThat(found.getName()).isEqualTo(name);
//
//        /* wrong case*/
//        // Given
//        id = 2;
//        // When
//        entity = resourceService.getResourceById(id);
//        found = (Resource)entity.getBody();
//        // Actual
//        assertThat(found.getId()).isEqualTo(2);
//        assertThat(found.getCode()).isNotEqualTo(code);
//        assertThat(found.getName()).isNotEqualTo(name);
//        assertThat(found.getCode()).isEqualTo(code2);
//        assertThat(found.getName()).isEqualTo(name2);
//
//        /* null case*/
//        id = 3;
//        entity = resourceService.getResourceById(id);
//        HttpStatus hs = entity.getStatusCode();
//        assertThat(hs).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    public void setResourceById() {
//        /* Right case */
//        // Given
//        int id = 1;
//        Resource resource1 = new Resource();
//        resource1.setCode("00301");
//        resource1.setName("resource new");
//        // When
//        ResponseEntity<?> entity = resourceService.setResourceById(resource1, id);
//        HttpStatus hs = entity.getStatusCode();
//        // Actual
//        assertThat(hs).isEqualTo(HttpStatus.OK);
//
//        /* null case */
//        // Given
//        int id1 = 3;
//        Resource resource2 = new Resource();
//        resource2.setCode("00301");
//        resource2.setName("resource new");
//        // When
//        ResponseEntity<?> entity1 = resourceService.setResourceById(resource2, id1);
//        HttpStatus hs1 = entity1.getStatusCode();
//        // Actual
//        assertThat(hs1).isEqualTo(HttpStatus.NOT_FOUND);
//
//    }
//
//    @Test
//    public void deleteResourceById() {
//        /* Right case */
//        // Given
//        int id = 1;
//        // When
//        ResponseEntity<?> entity = resourceService.deleteResourceById(id);
//        HttpStatus hs = entity.getStatusCode();
//        // Actual
//        assertThat(hs).isEqualTo(HttpStatus.OK);
//
//        /* null case */
//        // Given
//        int id1 = 3;
//        // When
//        ResponseEntity<?> entity1 = resourceService.deleteResourceById(id1);
//        HttpStatus hs1 = entity1.getStatusCode();
//        // Actual
//        assertThat(hs1).isEqualTo(HttpStatus.NOT_FOUND);
//    }
//
//    @Test
//    public void deleteAllResources() {
//        // When
//        ResponseEntity<?> entity = resourceService.deleteAllResources();
//        HttpStatus hs1 = entity.getStatusCode();
//        // Actual
//        assertThat(hs1).isEqualTo(HttpStatus.OK);
//
//    }
//
//    @Test
//    public void getResourcesByProjectId() {
//        // Give
//        int id = 1;
//        Resource resource1 = new Resource();
//        resource1.setId(1);
//        resource1.setCode("0001");
//        resource1.setName("resource1");
//        Resource resource2 = new Resource();
//        resource2.setId(2);
//        resource2.setCode("0002");
//        resource2.setName("resource2");
//
//        // When
//        ResponseEntity<?> entity = resourceService.getResourcesByProjectId(id);
//        List<Resource> resourceList = (List<Resource>)entity.getBody();
//
//        // Actual
//        /*we have some sql to define where projectId = ? so here will return empty one*/
//        assertThat(resourceList).isEqualTo(new ArrayList<>());
////        assertThat(resourceList.get(0).getId()).isEqualTo(resource1.getId());
////        assertThat(resourceList.get(1).getId()).isEqualTo(resource2.getId());
////        assertThat(resourceList.get(0).getName()).isEqualTo(resource1.getName());
////        assertThat(resourceList.get(1).getName()).isEqualTo(resource2.getName());
////        assertThat(resourceList.get(0).getCode()).isEqualTo(resource1.getCode());
////        assertThat(resourceList.get(1).getCode()).isEqualTo(resource2.getCode());
//    }
////
////    @Test
////    void addResourcesToProject() {
////    }
//}