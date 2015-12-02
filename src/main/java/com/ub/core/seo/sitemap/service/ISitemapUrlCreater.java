package com.ub.core.seo.sitemap.service;

public abstract class ISitemapUrlCreater {
    protected String host;

    abstract public String loc();
    abstract public String priority();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
