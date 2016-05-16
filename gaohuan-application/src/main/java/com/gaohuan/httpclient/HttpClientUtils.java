package com.gaohuan.httpclient;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by gh on 2016/5/4 0004.
 */
public class HttpClientUtils {
    public static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static void main(String[] args) throws URISyntaxException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpGet = new HttpGet("http://localhost");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet, context);
            HttpHost target=context.getTargetHost();
            List<URI> redirectLocations=context.getRedirectLocations();
            URI location= URIUtils.resolve(httpGet.getURI(),target,redirectLocations);
            System.out.println(location.toASCIIString());
        } finally {
            if (response != null) {
                response.close();
            }
        }

    }
}
