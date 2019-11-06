package com.hui.project.service;

import com.hui.project.bean.Analysis;
import com.hui.project.bean.AnalysisExample;
import com.hui.project.bean.Project;
import com.hui.project.bean.ProjectExample;
import com.hui.project.mapper.AnalysisMapper;
import com.hui.project.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private AnalysisMapper analysisMapper;
    @Resource
    private ProjectMapper projectMapper;

    @Override
    public List<Analysis> list() {
        List<Analysis> list = analysisMapper.selectByExample(new AnalysisExample());
        return list;
    }

    @Override
    public List<Project> searchProWithoutAnalysis() {
        List<Analysis> analyses = analysisMapper.selectByExample(new AnalysisExample());
        List<Integer> list = new ArrayList<Integer>();
        for(Analysis analysis : analyses){
            Integer id = analysis.getId();
            list.add(id);
        }
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria exampleCriteria = projectExample.createCriteria();
        exampleCriteria.andPidNotIn(list);
        List<Project> projects = projectMapper.selectByExample(projectExample);
        return projects;
    }

    @Override
    public void saveInfo(Analysis analysis) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPnameEqualTo(analysis.getProname());
        List<Project> projects = projectMapper.selectByExample(example);
        Integer pid = null;
        for (Project project : projects) {
            pid = project.getPid();
        }
        analysis.setId(pid);
        analysis.setAddtime(new Date());
        analysisMapper.insert(analysis);
    }
}
