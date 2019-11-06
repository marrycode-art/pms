package com.hui.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hui.common.StringUtils;;
import com.hui.usual.bean.Notice;
import com.hui.usual.bean.NoticeExample;
import com.hui.usual.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public void saveInfo(Notice notice) {
        notice.setNdate(new Date());
        noticeMapper.insert(notice);
    }

    @Override
    public PageInfo<Notice> getNoticeList(Integer pageNum, Map<String, Object> paramrterMap) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();

        Map<String, String> myBatisMap = StringUtils.parseParameterMapToMyBatisMap(paramrterMap);

        PageHelper.startPage(pageNum,3);

        List<Notice> notices = noticeMapper.selectByExample(example);
        PageInfo<Notice> page = new PageInfo<Notice>(notices,5);
        return page;
    }

    @Override
    public List<Notice> latestNotice() {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 3");

        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;
    }

    @Override
    public Notice showInfo(Integer nid) {
        Notice notice = noticeMapper.selectByPrimaryKey(nid);
        return notice;
    }
}


















