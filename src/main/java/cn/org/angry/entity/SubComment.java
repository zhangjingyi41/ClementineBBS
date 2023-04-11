package cn.org.angry.entity;


import java.util.Date;

/**
 * 评论的评论（回复）
 */
public class SubComment {
    private int id;
    private String content;
    private Date createTime;
    // 所属用户
    private int uid;
    // 父层评论
    private int cid;
    // 回复的对象id（-1为直属于父层评论，没有回复对象。大于0为回复对象的id，即子评论之间的相互回复）
    private int scid;

    // 子评论所属的用户
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubComment() {
    }

    public SubComment(String content, Date createTime, int uid, int cid, int scid) {
        this.content = content;
        this.createTime = createTime;
        this.uid = uid;
        this.cid = cid;
        this.scid = scid;
    }

    public SubComment(int id, String content, Date createTime, int uid, int cid, int scid) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.uid = uid;
        this.cid = cid;
        this.scid = scid;
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }
}
