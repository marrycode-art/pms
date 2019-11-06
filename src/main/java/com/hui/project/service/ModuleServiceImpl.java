package com.hui.project.service;

import com.hui.project.mapper.ModuleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleMapper moduleMapper;
}
