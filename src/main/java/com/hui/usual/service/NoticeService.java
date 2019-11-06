package com.hui.usual.service;

import com.github.pagehelper.PageInfo;
import com.hui.usual.bean.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    void saveInfo(Notice notice);

    PageInfo<Notice> getNoticeList(Integer pageNum, Map<String, Object> paramrterMap);

    List<Notice> latestNotice();

    Notice showInfo(Integer nid);
}
