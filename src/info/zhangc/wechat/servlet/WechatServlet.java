package info.zhangc.wechat.servlet;

import info.zhangc.wechat.po.TextMessage;
import info.zhangc.wechat.util.CheckUtil;
import info.zhangc.wechat.util.MessageUtil;
import info.zhangc.wechat.util.WeixinUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.dom4j.DocumentException;

public class WechatServlet extends HttpServlet
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


    /**
     *
     * 消息接收与响应
     * @param req
     * @param resp
     * @throws IOException
     */


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        Map map ;
        try {
            map = MessageUtil.xmlToMap(req);


            String fromUserName = (String)map.get("FromUserName");
            String toUserName = (String)map.get("ToUserName");
            String msgType = (String)map.get("MsgType");
            String content = (String)map.get("Content");


            String message = null;

            if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
                if ("1".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
                } else if ("2".equals(content)){
                    message = MessageUtil.initNewsMessage(toUserName, fromUserName);
                } else if ("3".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
                }else if("?".equals(content) || "？".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());

                }else if(content.startsWith("翻译")){
                    String word = content.replaceAll("^翻译","").trim();
                    if("".equals(word)){
                        message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
                    }else{
                        message = MessageUtil.initText(toUserName, fromUserName, WeixinUtil.translateFull(word));
                    }
                }

            } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)){
//                String eventType = map.get("Event");
//                if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
//                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
//                }

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