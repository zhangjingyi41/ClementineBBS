package cn.org.angry.entity;

import java.util.Date;

/**
 * 帖子
 */
public class Post {
    private int id;
    private String title;
    private String content;
    private Date createTime;
    private int uid;
    // 文章所属用户的信息
    private User user;

    public Post() {
    }

    public Post(String title, String content, Date createTime, int uid) {
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.uid = uid;
    }

    public Post(int id, String title, String content, Date createTime, int uid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", uid=" + uid +
                '}';
    }
}
