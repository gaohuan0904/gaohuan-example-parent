package com.gaohuan.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 网上抓取评论 手机号
 * <p>User: GaoHuan
 * <p>Date: 2016/8/9
 */
public class FetchData {

    /**
     * 拉取艺龙酒店的评论
     *
     * @throws IOException
     */
    public static void fetchComment() throws IOException {
        int hotelId = 90893715;
        int pageIndex = 1;
        while (true) {
            String url = "http://hotel.elong.com/ajax/detail/gethotelreviews?hotelId=" + hotelId + "&recommendedType=1&pageIndex=" + pageIndex + "&mainTagId=0&subTagId=0&_=1470719487692";
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            try {
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    String jsonStr = EntityUtils.toString(httpEntity);
                    JSONObject jsonObject = (JSONObject) JSON.parse(jsonStr);
                    JSONArray jsonArray = (JSONArray) jsonObject.get("contents");
                    if (jsonArray.size() == 0) {
                        pageIndex = 1;
                        hotelId++;
                    } else {
                        for (Object object : jsonArray) {
                            JSONObject jsonObj = (JSONObject) object;
                            String content = (String) jsonObj.get("content");
                            System.out.println(content);
                        }
                        pageIndex++;
                    }

                }
                response.close();
            } catch (IOException e) {
                pageIndex = 1;
                hotelId++;
            } finally {
                httpClient.close();
            }
        }

    }

    /**
     * 拉去手机号
     *
     * @throws IOException
     */
    public static void fetchMobile() throws IOException {
        int page = 1;
        while (true) {
            String url = "http://bj.9taohao.com/category.php?id=5&price_min=0&price_max=0&page=" + page + "&sort=goods_id&order=DESC";
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByTag("a");
            if (!elements.isEmpty()) {
                for (Element element : elements) {
                    Elements fonts = element.getElementsByTag("font");
                    if (!fonts.isEmpty()) {
                        String mobile = "";
                        for (Element font : fonts) {
                            mobile += font.text();
                        }
                        System.out.println(mobile);
                    }
                }
            }
            page++;
        }

    }

    public static void main(String[] args) throws IOException {
        fetchComment();
        fetchMobile();
    }

}
