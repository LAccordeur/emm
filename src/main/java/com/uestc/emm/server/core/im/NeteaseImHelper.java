package com.uestc.emm.server.core.im;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author : guoyang
 * @Description :
 * @Date : Created on 2017/11/22
 */
public class NeteaseImHelper {

    private static Logger logger = LoggerFactory.getLogger(NeteaseImHelper.class);

    //网易云app key
    private final static String appKey = "ea1c223beb81d1f9934fcef77b6178a3";

    //网易云密钥
    private final static String appSecret = "1c0e46125b0d";

    //url
    private final static String url = "https://api.netease.im/nimserver/user/create.action";


    public static String createToken(String userId, String userName) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        String result = null;

        String nonce = "57892437592475";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        //设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        //设置请求参数
        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("accid", userId));
        nvps.add(new BasicNameValuePair("name", userName));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonResult = EntityUtils.toString(responseEntity, "utf-8");
                logger.info(jsonResult);
                System.out.println(jsonResult);
                result = getTokenFromJson(jsonResult);
                EntityUtils.consume(responseEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
            HttpClientUtils.closeQuietly(response);
        }

        return result;
    }

    private static String getTokenFromJson(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONObject infoObject = jsonObject.getJSONObject("info");
        String result = (String) infoObject.get("token");
        return result;
    }


}
