package com.hui.project.controller;

import com.hui.project.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/function")
public class FunctionController {

    @Autowired
    private FunctionService functionService;
}
