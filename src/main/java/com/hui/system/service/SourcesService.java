package com.hui.system.service;

import com.hui.system.bean.Sources;

import java.util.List;

public interface SourcesService {

    List<Sources> getSourcesListByPid(int i);

    boolean saveInfo(Sources sources);

    Sources getOneById(Integer id);

    Boolean updateInfo(Sources sources);

    boolean delete(Integer id);

    List<Sources> getSourcesListByEid(Integer eid);
}
