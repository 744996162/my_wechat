package info.zhangc.wechat.po;

/**
 * Created by Administrator on 2015/8/4.
 */
public class AccessToken {
    private String token;

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private int expiresIn;





}
