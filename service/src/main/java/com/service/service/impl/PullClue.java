package com.service.service.impl;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;


public class PullClue {

    private String signature_key;
    private String token;

    public PullClue(String signature_key, String token) {
        this.signature_key = signature_key;
        this.token = token;
    }

    /**
     * 分页拉取crm线索数据，将签名相关的数据放入header供服务方校验
     *
     * @param startTime
     * @param endTime
     * @param page
     * @param pageSize
     * @return
     * @throws URISyntaxException
     */
    public String crmPullClues(String startTime, String endTime, int page, int pageSize) throws URISyntaxException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String timeStamp = String.valueOf(new Date().getTime() / 1000);
        String signature = getSignature(startTime, endTime, timeStamp);
        URI uri = new URIBuilder().setScheme("https")
                .setHost("feiyu.oceanengine.com")
                .setPath("/crm/v2/openapi/pull-clues/")
                .setParameter("start_time", startTime)
                .setParameter("end_time", endTime)
                .setParameter("page", String.valueOf(page))
                .setParameter("page_size", String.valueOf(pageSize))
                .build();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("signature", signature);
        httpGet.setHeader("Timestamp", timeStamp);
        httpGet.setHeader("Access-Token", token);
        httpGet.setHeader("Content-Type", "application/json");
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(80000)
                .setConnectTimeout(80000)
                .setSocketTimeout(80000)
                .build();
        httpGet.setConfig(config);
        String res = "";
        try {
            HttpResponse getAddrResp = httpClient.execute(httpGet);
            HttpEntity entity = getAddrResp.getEntity();
            if (entity != null) {
                res = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return res;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
                return res;
            }
        }
        return res;
    }

    /**
     * 生成CRM的签名数据
     *
     * @param startTime
     * @param endTime
     * @param timeStamp
     * @return
     */
    private String getSignature(String startTime, String endTime, String timeStamp) {
        String data = "/crm/v2/openapi/pull-clues/?start_time=" + startTime + "&end_time=" + endTime + " " + timeStamp;
        String signature = CrmSignature.generateSignature(data, this.signature_key);
        return signature;
    }
}
