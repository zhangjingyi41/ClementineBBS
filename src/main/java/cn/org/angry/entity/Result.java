package cn.org.angry.entity;

/**
 * 响应结果实体类
 */
public class Result {
    private boolean ok;
    private String message;
    private Object data;

    public Result() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
