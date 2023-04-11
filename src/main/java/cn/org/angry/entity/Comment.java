package cn.org.angry.entity;

import cn.org.angry.entity.User;

import java.util.Date;
import java.util.List;

/**
 * 帖子评论
 */
public class Comment {
    private int id;
    private String content;
    private Date createTime;
    private int uid;
    private int pid;

    // 评论所属的用户信息
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Comment() {
    }

    public Comment(String content, Date createTime, int uid, int pid) {
        this.content = content;
        this.createTime = createTime;
        this.uid = uid;
        this.pid = pid;
    }

    public Comment(int id, String content, Date createTime, int uid, int pid) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.uid = uid;
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", uid=" + uid +
                ", pid=" + pid +
                '}';
    }
}
