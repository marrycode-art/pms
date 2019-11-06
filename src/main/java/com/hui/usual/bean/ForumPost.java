package com.hui.usual.bean;

import java.util.Date;

public class ForumPost {
    private Integer forumid;

    private String forumtitle;

    private Integer empFk3;

    private Date createtime;

    private Integer status;

    private String forumcontent;

    public Integer getForumid() {
        return forumid;
    }

    public void setForumid(Integer forumid) {
        this.forumid = forumid;
    }

    public String getForumtitle() {
        return forumtitle;
    }

    public void setForumtitle(String forumtitle) {
        this.forumtitle = forumtitle == null ? null : forumtitle.trim();
    }

    public Integer getEmpFk3() {
        return empFk3;
    }

    public void setEmpFk3(Integer empFk3) {
        this.empFk3 = empFk3;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getForumcontent() {
        return forumcontent;
    }

    public void setForumcontent(String forumcontent) {
        this.forumcontent = forumcontent == null ? null : forumcontent.trim();
    }

    @Override
    public String toString() {
        return "ForumPost{" +
                "forumid=" + forumid +
                ", forumtitle='" + forumtitle + '\'' +
                ", empFk3=" + empFk3 +
                ", createtime=" + createtime +
                ", status=" + status +
                ", forumcontent='" + forumcontent + '\'' +
                '}';
    }
}