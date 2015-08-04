package info.zhangc.wechat.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import info.zhangc.wechat.menu.ClickButton;
import info.zhangc.wechat.menu.Menu;
import info.zhangc.wechat.po.AccessToken;
import org.apache.commons.lang.ObjectUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.print.DocFlavor;


/**
 * Created by Administrator on 2015/8/3.
 */


public class WeixinUtil {

    private static final String APPID = "wxa047c5522e0c03ba";
    private static final String APPSECRET = "7b0804459ecb6bc304d45f791510a3c3";

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";


    /**
     * get请求
     * @param url
     * @return
     * @throws ParseException
     * @throws java.io.IOException
     */
    public static JSONObject doGetStr(String url) throws ParseException, IOException{
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        HttpResponse httpResponse = client.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.fromObject(result);
        }
        return jsonObject;
    }



    public static JSONObject doPostStr(String url, String outStr) throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost();
        JSONObject jsonObject;
        httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
        HttpResponse response = client.execute(httpPost);

        String result = EntityUtils.toString(response.getEntity(),"UTF-8");
        jsonObject = JSONObject.fromObject(result);
        return jsonObject;

    }




    public static AccessToken getAccessToken() throws IOException, ParseException {
        AccessToken token = new AccessToken();

        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET",APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject!=null){
            token.setToken(jsonObject.getString("access_token"));
            token.setExpiresIn(jsonObject.getInt("expires_in"));
        }
        return token;
    }

    public static String translateFull(String source) throws IOException, ParseException {
        String url = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=jNg0LPSBe691Il0CG5MwDupw&q=KEYWORD&from=auto&to=auto";
        url = url.replace("KEYWORD", URLEncoder.encode(source,"UTF-8"));
        JSONObject jsonObject = doGetStr(url);
        StringBuffer dst = new StringBuffer();
        List<Map> list = (List<Map>) jsonObject.get("trans_result");
        for (Map map : list){
            dst.append(map.get("dst"));
        }
        return dst.toString();
    }

    public static Menu initMenu(){
        Menu menu = new Menu();
        ClickButton button11 = new ClickButton();
        button11.setName("click菜单");
        button11.setType("click");
        button11.setKey("11");

        return menu;

    }

    public static String translate(String source) throws IOException, ParseException {
        String url = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=jNg0LPSBe691Il0CG5MwDupw&q=KEYWORD&from=auto&to=auto";
        url = url.replace("KEYWORD", URLEncoder.encode(source,"UTF-8"));
        JSONObject jsonObject = doGetStr(url);
        String errno = jsonObject.getString("errno");
        Object obj = jsonObject.get("data");
        StringBuffer dst = new StringBuffer();
//        if ("0".equals(errno) && !"[]".equals(obj.toString())){
//            TransResult
//        }
        return "";

    }




}
