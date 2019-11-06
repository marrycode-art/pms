package com.hui.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hui.common.StringUtils;
import com.hui.usual.bean.BaoXiao;
import com.hui.usual.bean.BaoXiaoExample;
import com.hui.usual.mapper.BaoXiaoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BaoXiaoServiceImpl implements BaoXiaoService{

    @Resource
    private BaoXiaoMapper baoXiaoMapper;

    @Override
    public void saveInfo(BaoXiao baoXiao) {
        String bxid = UUID.randomUUID().toString().replaceAll("-", "");
        baoXiao.setBxid(bxid);
        baoXiao.setBxstatus(0);
        baoXiaoMapper.insert(baoXiao);
    }

    @Override
    public PageInfo<BaoXiao> getMyBaoXiaoList(Integer eid, Integer pageNum, Map<String, Object> paramrterMap) {
        BaoXiaoExample example = new BaoXiaoExample();
        BaoXiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);

        Map<String,String> myBatisMap = StringUtils.parseParameterMapToMyBatisMap(paramrterMap);

        String status = myBatisMap.get("status");
        String keyword = myBatisMap.get("keyword");
        if (status != null && status != "") {
            criteria.andBxstatusEqualTo(Integer.parseInt(status));
        }
        if (keyword != null && keyword != "") {
            criteria.andBxremarkLike(keyword);
        }

        PageHelper.startPage(pageNum,3);

        List<BaoXiao> list = baoXiaoMapper.selectByExample(example);

        PageInfo<BaoXiao> page = new PageInfo<BaoXiao>(list,5);

        return page;
    }
}


















