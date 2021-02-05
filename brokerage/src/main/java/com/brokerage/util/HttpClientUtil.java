package com.brokerage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * 调用http接口的工具类
 */
    public class HttpClientUtil {

    private static final String APPLICATION_JSON = "application/json";

    public final static int CONNECT_TIMEOUT = 3000 * 1000;//链接超时1秒

    public final static int CONNECTION_REQUEST_TIMEOUT = 3000 * 1000;//请求超时1秒

    public final static int SOCKET_TIMEOUT = 3000 * 1000;//请求获取数据的超时时间

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * json格式提交post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String jsonPost(String url, Object params) {
        CloseableHttpClient httpclient = HttpClients.custom().build();
        HttpPost httppost = new HttpPost(url);
        //setConnectTimeout：设置连接超时时间，单位毫秒.setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间.setSocketTimeout：请求获取数据的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(100 * 1000).setConnectionRequestTimeout(100 * 1000).setSocketTimeout(100 * 1000).build();
        httppost.setConfig(requestConfig);

        httppost.setHeader(HTTP.USER_AGENT, getUserAgent());

        CloseableHttpResponse response = null;

        httppost.setHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        httppost.setHeader("Cookie","X-Version=v1; X-Version-cpc=V1; X-Version-csrv=V1; X-Version-cpct=V1; X-Version-cequity=V1; X-Version-cpct-pre=V1; X-Version-operation=V1; X-Version-coop=V1; X-Version-cres=V1; X-Version-cequityp=V1; X-Version-jslog=V1; lg_st_tr=2; u_info=dfd18394-1fee-4573-995d-ccf2803afbeb; token=c9ce07db-e0d5-4337-a322-2cf08d3635dd");
        httppost.setHeader("x-auth-token","c9ce07db-e0d5-4337-a322-2cf08d3635dd");
        httppost.setHeader("X-orgId","613856447");
        httppost.setHeader("X-SysUserCode","18072892408");
        httppost.setHeader("cust-order-token","4krTdZe3YLgLGh7qRfNdu6");
        //数据类型转换
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(params), "UTF-8");
        httppost.setEntity(stringEntity);
        try {
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
//            Header[] token = response.getHeaders("cust-order-token");
//            if (token != null && token.length != 0) {
//                System.out.println(token[0].getValue());
//            }
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Throwable e) {
            logger.error("jsonPost error,url=" + url + ",params=" + params, e);
        } finally {
            // 关闭连接,释放资源
            try {
                if (null != response) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                logger.error("httpclient.close error,url=" + url + ",params=" + params, e);
            }
        }
        return "";
    }

    public static String jsonGet(String url) {
        CloseableHttpClient httpclient = HttpClients.custom().build();
        HttpGet httpget = new HttpGet(url);
        //setConnectTimeout：设置连接超时时间，单位毫秒.setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间.setSocketTimeout：请求获取数据的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(100 * 1000).setConnectionRequestTimeout(100 * 1000).setSocketTimeout(100 * 1000).build();
        httpget.setConfig(requestConfig);

        httpget.setHeader(HTTP.USER_AGENT, getUserAgent());

        CloseableHttpResponse response = null;

        httpget.setHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        httpget.setHeader("Cookie","X-Version=v1; X-Version-cpc=V1; X-Version-csrv=V1; X-Version-cpct=V1; X-Version-cequity=V1; X-Version-cpct-pre=V1; X-Version-operation=V1; X-Version-coop=V1; X-Version-cres=V1; X-Version-cequityp=V1; X-Version-jslog=V1; lg_st_tr=2; u_info=dfd18394-1fee-4573-995d-ccf2803afbeb; token=c9ce07db-e0d5-4337-a322-2cf08d3635dd");
        httpget.setHeader("x-auth-token","c9ce07db-e0d5-4337-a322-2cf08d3635dd");
        httpget.setHeader("X-orgId","613856447");
        httpget.setHeader("X-SysUserCode","18072892408");
        httpget.setHeader("cust-order-token","4krTdZe3YLgLGh7qRfNdu6");
        //数据类型转换
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Throwable e) {
            logger.error("jsonPost error,url=" + url + ",params=" + e);
        } finally {
            // 关闭连接,释放资源
            try {
                if (null != response) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                logger.error("httpclient.close error,url=" + url + ",params=" + e);
            }
        }
        return "";
    }

    public static String jsonPosts(String url, Object params) {
        CloseableHttpClient httpclient = HttpClients.custom().build();
        HttpPost httppost = new HttpPost(url);
        //setConnectTimeout：设置连接超时时间，单位毫秒.setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间.setSocketTimeout：请求获取数据的超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(100 * 1000).setConnectionRequestTimeout(100 * 1000).setSocketTimeout(100 * 1000).build();
        httppost.setConfig(requestConfig);

        httppost.setHeader(HTTP.USER_AGENT, getUserAgent());

        CloseableHttpResponse response = null;

        httppost.setHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        httppost.setHeader("Cookie","X-Version=v1; X-Version-cpc=V1; X-Version-csrv=V1; X-Version-cpct=V1; X-Version-cequity=V1; X-Version-cpct-pre=V1; X-Version-operation=V1; X-Version-coop=V1; X-Version-cres=V1; X-Version-cequityp=V1; X-Version-jslog=V1; lg_st_tr=2; token=50d8ad5a-d5e1-491f-b645-0d64ac12ce8c; u_info=4e10edad-cd39-4183-a658-f7c40b26d4f6");
        httppost.setHeader("x-auth-token","50d8ad5a-d5e1-491f-b645-0d64ac12ce8c");
        httppost.setHeader("X-orgId","613856447");
        httppost.setHeader("X-SysUserCode","18072892408");
        httppost.setHeader("cust-order-token","5Mt7cTOwa9kWB8iFFD5ek7");
        //数据类型转换
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(params), "UTF-8");
        httppost.setEntity(stringEntity);
        try {
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Throwable e) {
            logger.error("jsonPost error,url=" + url + ",params=" + params, e);
        } finally {
            // 关闭连接,释放资源
            try {
                if (null != response) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                logger.error("httpclient.close error,url=" + url + ",params=" + params, e);
            }
        }
        return "";
    }

    private static void closeHttpClient(CloseableHttpClient client) throws IOException {
        if (client != null) {
            client.close();
        }
    }

    private static String getUserAgent() {
        return "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";
    }

}
