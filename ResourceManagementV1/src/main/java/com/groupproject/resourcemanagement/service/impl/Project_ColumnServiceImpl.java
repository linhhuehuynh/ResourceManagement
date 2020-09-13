package com.groupproject.resourcemanagement.service.impl;

import com.groupproject.resourcemanagement.DAO.Project_ColumnRepository;
import com.groupproject.resourcemanagement.entity.ProjectColumnEntity;
import com.groupproject.resourcemanagement.service.Project_ColumnService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Project_ColumnServiceImpl implements Project_ColumnService {

    @Autowired
    private Project_ColumnRepository projectColumnRepository;


    @Override
    @Transactional
    public ProjectColumnEntity create(ProjectColumnEntity projectColumnEntity) {
        ProjectColumnEntity result = new ProjectColumnEntity();
        if(projectColumnEntity != null) {
            BeanUtils.copyProperties(projectColumnEntity,result);
            projectColumnRepository.createByProjectColumn(result);
        }
        return projectColumnEntity;
    }

    @Override
    @Transactional
    public ProjectColumnEntity findOne(Integer project_column_id) {
        ProjectColumnEntity result = new ProjectColumnEntity();
        ProjectColumnEntity PC1 = projectColumnRepository.findByProjectColumnId(project_column_id);
        if(PC1 != null) {
            BeanUtils.copyProperties(PC1,result);
        }
        return result;
    }

    @Override
    @Transactional
    public List<ProjectColumnEntity> findList(Integer project_id) {
        List<ProjectColumnEntity> PClist = (List<ProjectColumnEntity>) projectColumnRepository.findAllByProjectId(project_id);
        return PClist;
    }

    @Override
    @Transactional
    public boolean update(ProjectColumnEntity projectColumnEntity, Integer project_column_id) {
        boolean signal = false;
        if(projectColumnRepository.findByProjectColumnId(project_column_id) != null) {
            projectColumnEntity.setProjectColumnId(project_column_id);
            projectColumnRepository.updateByProjectColumnId(projectColumnEntity,project_column_id);
            signal = true;
        }
        return signal;
    }

    @Override
    @Transactional
    public boolean delete(Integer project_column_id) {
        if(projectColumnRepository.findByProjectColumnId(project_column_id) != null) {
            projectColumnRepository.removeByProjectColumnId(project_column_id);
            return true;
        }
        return false;
    }

}
