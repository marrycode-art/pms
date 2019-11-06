package com.hui.project.controller;

import com.hui.project.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
}
