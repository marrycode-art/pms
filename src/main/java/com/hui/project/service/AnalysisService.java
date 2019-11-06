package com.hui.project.service;

import com.hui.project.bean.Analysis;
import com.hui.project.bean.Project;

import java.util.List;

public interface AnalysisService {

    List<Analysis> list();

    List<Project> searchProWithoutAnalysis();

    void saveInfo(Analysis analysis);
}
