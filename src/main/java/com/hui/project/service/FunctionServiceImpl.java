package com.hui.project.service;

import com.hui.project.mapper.FunctionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FunctionServiceImpl implements FunctionService {

    @Resource
    private FunctionMapper functionMapper;
}
