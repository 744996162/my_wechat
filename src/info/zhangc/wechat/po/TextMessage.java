package info.zhangc.wechat.po;

public class TextMessage
{
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;

    public String getToUserName()
    {
        return this.ToUserName;
    }

    public void setToUserName(String toUserName) {
        this.ToUserName = toUserName;
    }

    public String getFromUserName() {
        return this.FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(String createTime) {
        this.CreateTime = createTime;
    }

    public String getMsgType() {
        return this.MsgType;
    }

    public void setMsgType(String msgType) {
        this.MsgType = msgType;
    }

    public String getContent() {
        return this.Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getMsgId() {
        return this.MsgId;
    }

    public void setMsgId(String msgId) {
        this.MsgId = msgId;
    }
}