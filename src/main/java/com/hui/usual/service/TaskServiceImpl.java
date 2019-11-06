package com.hui.usual.service;

import com.hui.project.bean.*;
import com.hui.project.mapper.AnalysisMapper;
import com.hui.project.mapper.FunctionMapper;
import com.hui.project.mapper.ModuleMapper;
import com.hui.project.mapper.ProjectMapper;
import com.hui.usual.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private AnalysisMapper analysisMapper;
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private FunctionMapper functionMapper;

    @Override
    public List<Project> addTask() {
        List<Project> projects = projectMapper.selectByExample(new ProjectExample());

        for (Project project : projects) {
            Integer pid = project.getPid();

            ModuleExample moduleExample = new ModuleExample();
            ModuleExample.Criteria criteria = moduleExample.createCriteria();
            criteria.andAnalysisFkEqualTo(pid);
            List<Module> modules = moduleMapper.selectByExample(moduleExample);

            Analysis analysis = analysisMapper.selectByPrimaryKey(pid);

            if (analysis != null) {
                analysis.setModules(modules);
            }

            project.setAnalysis(analysis);
        }

        for (Project project : projects) {
            Analysis analysis = project.getAnalysis();
            if(analysis != null) {
                List<Module> modules = analysis.getModules();
                for (Module module : modules) {
                    Integer id = module.getId();

                    FunctionExample functionExample = new FunctionExample();
                    FunctionExample.Criteria criteria = functionExample.createCriteria();
                    criteria.andModeleFkEqualTo(id);
                    List<Function> functions = functionMapper.selectByExample(functionExample);

                    module.setFunctions(functions);
                }
            }
        }
        return projects;
    }
}
