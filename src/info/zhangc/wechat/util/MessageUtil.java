package info.zhangc.wechat.util;

import com.thoughtworks.xstream.XStream;
import info.zhangc.wechat.po.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil
{
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";

    public static Map<String, String> xmlToMap(HttpServletRequest request)
            throws Exception
    {
        Map<String, String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();

        List<Element> list = root.elements();

        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }

        ins.close();
        return map;
    }


    /**
     * 组装文本消息
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public static String initText(String toUserName, String fromUserName, String content)
    {
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType("text");
        text.setCreateTime(new Date().toString());
        text.setContent(content);
        return textMessageToXml(text);
    }


    /**
     * 将文本消息对象转为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());

        return xStream.toXML(textMessage);
    }


    public static String menuText()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注！，如有意见，请直接回复！");
        return sb.toString();
    }

    public static String firstMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("firstMenu");
        return sb.toString();
    }


    public static String secondMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("firstMenu");
        return sb.toString();
    }

    public static String threeMenu(){
        StringBuffer sb = new StringBuffer();
        sb.append("firstMenu");
        return sb.toString();
    }


    /**
     * 图文消息转为xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 图片消息转为xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }


    /**
     * 音乐消息转为xml
     * @param musicMessage
     * @return
     */
    public static String musicMessageToXml(MusicMessage musicMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }


    /**
     * 图文消息的组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewsMessage(String toUserName,String fromUserName){
        String message;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("本微信号介绍");
        news.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。");
        news.setPicUrl("http://zapper.tunnel.mobi/Weixin/image/imooc.jpg");
        news.setUrl("www.imooc.com");

        newsList.add(news);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMessageToXml(newsMessage);
        return message;
    }




}























