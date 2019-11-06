package com.hui.project.service;

import com.hui.project.bean.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectList();

    Project searchInfo(Integer pid);

    void saveProject(Project project);

    void update(Project project);

    boolean batchDel(Integer ids);

    List<Project> conditionSearch(Integer cid, String keyword, Integer orderby);
}
