package com.hui.project.controller;

import com.hui.project.bean.Analysis;
import com.hui.project.bean.Project;
import com.hui.project.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("project-need");
        List<Analysis> list = analysisService.list();
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView("project-need-add");
        List<Project> list = analysisService.searchProWithoutAnalysis();
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(Analysis analysis){
        analysisService.saveInfo(analysis);
        return "redirect:/analysis/list";
    }
}
