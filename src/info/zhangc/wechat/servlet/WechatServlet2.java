package info.zhangc.wechat.servlet;

import info.zhangc.wechat.po.TextMessage;
import info.zhangc.wechat.util.CheckUtil;
import info.zhangc.wechat.util.MessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
//import org.dom4j.DocumentException;

public class WechatServlet2 extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce))
            out.print(echostr);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        Map map = null;
        try {
            map = MessageUtil.xmlToMap(req);


            String fromUserName = (String)map.get("FromUserName");
            String toUserName = (String)map.get("ToUserName");
            String msgType = (String)map.get("MsgType");
            String content = (String)map.get("Content");

            String message = null;

            if ("text".equals(msgType)) {
                TextMessage text = new TextMessage();
                text.setFromUserName(toUserName);
                text.setToUserName(fromUserName);
                text.setMsgType("text");
                text.setCreateTime(new Date().toString());
                text.setContent("您发送的消息为:" + content);
                message = MessageUtil.textMessageToXml(text);
            } else if ("event".equals(msgType)) {
                String eventType = (String)map.get("Event");
                if ("subscribe".equals(eventType)) {
                    message = MessageUtil.initText(toUserName, fromUserName, content);
                }

            }

            out.print(message);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}