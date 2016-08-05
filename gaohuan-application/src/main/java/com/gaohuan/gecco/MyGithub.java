package com.gaohuan.gecco;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(pipelines = "consolePipeline")
public class MyGithub implements HtmlBean {

    private static final long serialVersionUID = -8870768223740844229L;

    @Request
    private HttpRequest request;

    @Text
    @HtmlField(cssPath = ".J_commentDetail")
    private String remark;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public static void main(String[] args) {
        GeccoEngine.create()
                .classpath("com.gaohuan.gecco")
                .start("http://hotels.ctrip.com/hotel/dianping/1669754.html")
                .interval(2000)
                .start();
    }


}