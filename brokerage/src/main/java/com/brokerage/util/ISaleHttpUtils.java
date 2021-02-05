package com.brokerage.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ISaleHttpUtils {

    private static final String APPLICATION_JSON = "application/json";

    private final int CONNECT_TIMEOUT = 100 * 1000;//链接超时10秒

    private final int CONNECTION_REQUEST_TIMEOUT = 100 * 1000;//请求超时10秒

    private final int SOCKET_TIMEOUT = 100 * 1000;//请求获取数据的超时时间

    private static final Logger logger = LoggerFactory.getLogger(ISaleHttpUtils.class);

    PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
    private CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager).setConnectionManagerShared(true).build();

    private RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();

    private String xAuthToken = "58d506ed-2db4-42a0-ade8-141ac34efa7d";
    private final String u_info = "4875eebb-7751-402c-8cc9-68630dbf1ba2";
    private String Cookies= String.format("SL_G_WPT_TO=zh; SL_GWPT_Show_Hide_tmp=1; SL_wptGlobTipTmp=1; X-Version=v1; X-Version-cpc=V1; X-Version-csrv=V1; X-Version-cpct=V1; X-Version-cequity=V1; X-Version-cpct-pre=V1; X-Version-operation=V1; X-Version-coop=V1; X-Version-cres=V1; X-Version-cequityp=V1; X-Version-jslog=V1; lg_st_tr=2; token=%s; u_info=%s",this.getxAuthToken(),this.u_info);

   // private String userCode = "18072892408";

    private String redirectUrl = "";

    private String channelNbr = "";

    private String custOrderToken = "4SaM7cxC8RNroUODwq5cH5";

//    public String getUserCode() {
//        return userCode;
//    }
//
//    public void setUserCode(String userCode) {
//        this.userCode = userCode;
//    }

    public String getxAuthToken() {
        return xAuthToken;
    }

    public void setxAuthToken(String xAuthToken) {
        this.xAuthToken = xAuthToken;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getChannelNbr() {
        return channelNbr;
    }

    public void setChannelNbr(String channelNbr) {
        this.channelNbr = channelNbr;
    }

    public String getCustOrderToken() {
        return custOrderToken;
    }

    public void setCustOrderToken(String custOrderToken) {
        this.custOrderToken = custOrderToken;
    }


    /**
     * 带cookies 请求 get模式
     *
     * @param url
     * @param
     * @return
     */
    public String getWithCookie(String url,Map map) {

        HttpGet httpGet= new HttpGet(url);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader(HTTP.USER_AGENT, getUserAgent());
        httpGet.setHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        httpGet.setHeader("Cookie",Cookies);
        httpGet.setHeader("x-auth-token", map.get("xAuthToken").toString());
        httpGet.setHeader("X-SysUserCode", map.get("usercode").toString());
        httpGet.setHeader("X-orgId","613856447");
        httpGet.setHeader("cust-order-token", custOrderToken);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            Header[] token = response.getHeaders("cust-order-token");
            if (token != null && token.length != 0) {
                this.custOrderToken = token[0].getValue();
            }
            return EntityUtils.toString(entity, "UTF-8");
        } catch (SocketTimeoutException e) {
            logger.error("post error,url=" + url + ",params=" , e);
        } catch (Throwable e) {
            logger.error("post error,url=" + url + ",params=" ,e);
        } finally {
            // 关闭连接,释放资源
            try {
                if (null != response) {
                    response.close();
                }

//                httpclient.close();
            } catch (IOException e) {
                logger.error("httpclient.close error,url=" + url + ",params=" , e);
            }
        }
        return "";
    }

    /**
     * 带cookies 请求 post模式
     *
     * @param url
     * @param params
     * @return
     */
    public String postWithCookieFormData(String url, Map<String, Object> params) {

        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        httppost.setHeader(HTTP.USER_AGENT, getUserAgent());
        httppost.setHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        httppost.setHeader("Cookie",Cookies);
        httppost.setHeader("x-auth-token", xAuthToken);
        httppost.setHeader("X-SysUserCode", params.get("usercode").toString());
        httppost.setHeader("X-orgId","613856447");
        httppost.setHeader("cust-order-token", custOrderToken);

        try {
            httppost.setEntity(new StringEntity(JSON.toJSONString(params), "UTF-8"));
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            Header[] token = response.getHeaders("cust-order-token");
            if (token != null && token.length != 0) {
                this.custOrderToken = token[0].getValue();
            }
            return EntityUtils.toString(entity, "UTF-8");
        } catch (SocketTimeoutException e) {
            logger.error("post error,url=" + url + ",params=" + params, e);
        } catch (Throwable e) {
            logger.error("post error,url=" + url + ",params=" + params, e);
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

    public String postWithCookieFormDatas(String url, Map<String, Object> params) {

        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        httppost.setHeader(HTTP.USER_AGENT, getUserAgent());
        httppost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httppost.setHeader("Cookie",Cookies);
        httppost.setHeader("x-auth-token", xAuthToken);
        httppost.setHeader("X-SysUserCode", params.get("usercode").toString());
        httppost.setHeader("X-orgId","613856447");
        httppost.setHeader("cust-order-token", custOrderToken);
        List<NameValuePair> pairs = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            pairs = new ArrayList(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String value = entry.getValue().toString();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }
        try {
            httppost.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            Header[] token = response.getHeaders("cust-order-token");
            if (token != null && token.length != 0) {
                this.custOrderToken = token[0].getValue();
            }
            return EntityUtils.toString(entity, "UTF-8");
        } catch (SocketTimeoutException e) {
            logger.error("post error,url=" + url + ",params=" + params, e);
        } catch (Throwable e) {
            logger.error("post error,url=" + url + ",params=" + params, e);
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


    private static String getUserAgent() {
        return "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";
    }
}
