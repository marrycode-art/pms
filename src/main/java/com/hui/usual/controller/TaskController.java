package com.hui.usual.controller;

import com.hui.project.bean.Project;
import com.hui.usual.bean.Task;
import com.hui.usual.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/addTask", method = RequestMethod.GET)
    public ModelAndView addTask(){
        ModelAndView modelAndView = new ModelAndView("task-add");

        List<Project> projects = taskService.addTask();

        System.err.println(Arrays.asList(projects));

        modelAndView.addObject("projects", projects);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("task");

        return  modelAndView;
    }

    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(Task task){

        return "redirect:/task/list";
    }
}
