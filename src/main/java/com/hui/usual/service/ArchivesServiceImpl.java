package com.hui.usual.service;

import com.hui.usual.bean.Archives;
import com.hui.usual.bean.ArchivesExample;
import com.hui.usual.mapper.ArchivesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService {

    @Resource
    private ArchivesMapper archivesMapper;

    @Override
    public List<Archives> jsonList() {
        List<Archives> archives = archivesMapper.selectByExample(new ArchivesExample());
        return archives;
    }
}
