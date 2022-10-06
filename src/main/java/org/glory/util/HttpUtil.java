package org.glory.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

    private static final int CONNECTION_REQUEST_TIMEOUT = 10000;
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int SOCKET_TIMEOUT = 10000;

    public static String get(String uriString) throws IOException {
        HttpGet httpGet = new HttpGet();

        httpGet.addHeader("Accept", "application/json");
        httpGet.addHeader("Content-Type", "application/json");

        URI uri = null;
        try {
            uri = new URIBuilder(uriString).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        httpGet.setURI(uri);

        httpGet.setConfig(RequestConfig.custom()
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build());

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = httpClient.execute(httpGet);

        String resultJson = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(resultJson);

        return resultJson;
    }

    public static String post(String uriString, String body) throws IOException {
        HttpPost httpPost = new HttpPost();

        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");

        URI uri = null;
        try {
            uri = new URIBuilder(uriString).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        httpPost.setURI(uri);

        httpPost.setConfig(RequestConfig.custom()
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build());
        httpPost.setEntity(new StringEntity(body, "UTF-8"));

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = httpClient.execute(httpPost);

        String resultJson = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(resultJson);

        return resultJson;
    }

    public static String post(String uriString, String body, Map<String, String> headerMap)
        throws IOException {
        HttpPost httpPost = new HttpPost();

        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json");

        for (String key : headerMap.keySet()) {
            httpPost.addHeader(key, headerMap.get(key));
        }

        URI uri = null;
        try {
            uri = new URIBuilder(uriString).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        httpPost.setURI(uri);

        httpPost.setConfig(RequestConfig.custom()
            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
            .setConnectTimeout(CONNECT_TIMEOUT)
            .setSocketTimeout(SOCKET_TIMEOUT)
            .build());
        httpPost.setEntity(new StringEntity(body, "UTF-8"));

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = httpClient.execute(httpPost);

        String resultJson = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(resultJson);

        return resultJson;
    }

}
