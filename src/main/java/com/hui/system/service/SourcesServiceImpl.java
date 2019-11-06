package com.hui.system.service;

import com.hui.system.bean.Sources;
import com.hui.system.bean.SourcesExample;
import com.hui.system.mapper.SourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService {

    @Resource
    private SourcesMapper sourcesMapper;

    @Override
    public List<Sources> getSourcesListByPid(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> list = sourcesMapper.selectByExample(example);
        return list;
    }

    @Override
    public boolean saveInfo(Sources sources) {
        int row = sourcesMapper.insert(sources);
        return row > 0;
    }

    @Override
    public Sources getOneById(Integer id) {
        return sourcesMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateInfo(Sources sources) {
        int row = sourcesMapper.updateByPrimaryKeySelective(sources);
        return row > 0;
    }

    @Override
    public boolean delete(Integer id) {
        int row = sourcesMapper.deleteByPrimaryKey(id);
        return row > 0;
    }

    @Override
    public List<Sources> getSourcesListByEid(Integer eid) {
        List<Sources> sources = sourcesMapper.getSourcesListByEid(eid,1);
        for (Sources source : sources) {
            Integer id = source.getId();
            List<Sources> list = sourcesMapper.getSourcesListByEid(eid, id);
            source.setChildren(list);
        }
        return sources;
    }
}
















