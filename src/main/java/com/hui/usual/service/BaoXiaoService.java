package com.hui.usual.service;

import com.github.pagehelper.PageInfo;
import com.hui.usual.bean.BaoXiao;

import java.util.Map;

public interface BaoXiaoService {

    void saveInfo(BaoXiao baoXiao);

    PageInfo<BaoXiao> getMyBaoXiaoList(Integer eid, Integer pageNum, Map<String, Object> paramrterMap);
}
