package com.service.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
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

    private static final Logger logger = LoggerFactory.getLogger(com.service.util.HttpClientUtil.class);

//    private static String serverIp = "http://122.229.29.27:8002/ZJ114MallExchangeServ_New_Test/ExchangeService.asmx";

    /**
     * post soap 请求
     *
     * @param url
     * @param requestXML
     * @return
     */
    public static String postSoap(String url, String requestXML) {
        CloseableHttpClient httpclient = HttpClients.custom().build();
        HttpPost httppost = new HttpPost(url);

        //setConnectTimeout：设置连接超时时间，单位毫秒.setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间.setSocketTimeout：请求获取数据的超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();

        httppost.setConfig(requestConfig);

        httppost.setHeader(HTTP.USER_AGENT, getUserAgent());

        CloseableHttpResponse response = null;


        try {
            StringEntity stringEntity = new StringEntity(requestXML, "UTF-8");
            httppost.addHeader("Content-Type", "text/xml; charset=UTF-8");
            httppost.setEntity(stringEntity);
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Throwable e) {
            logger.error("post error,url=" + url + ",requestXML=" + requestXML, e);
        } finally {
            // 关闭连接,释放资源
            try {
                if (null != response) {
                    response.close();
                }

                httpclient.close();
            } catch (IOException e) {
                logger.error("httpclient.close error,url=" + url + ",requestXML=" + requestXML, e);
            }
        }
        return "";
    }

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
