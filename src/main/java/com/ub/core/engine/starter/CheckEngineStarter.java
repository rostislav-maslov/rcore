package com.ub.core.engine.starter;

import com.ub.core.base.starter.ACoreStarter;
import com.ub.core.utils.HttpUtils;

import java.net.InetAddress;

public class CheckEngineStarter extends ACoreStarter {
    @Override
    protected void onStart() {
        try {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        String url = "http://www.unitbean.com/api/v1/engine/check";
                        HttpUtils httpUtils = new HttpUtils(url);
                        httpUtils.addParam("hostName", getHostName());
                        httpUtils.addParam("hostAddress", getHostAddress());
                        httpUtils.sendGet();
                    }catch(Exception e){

                    }
                }
            });
            thread.start();
        } catch (Exception e) {
        }
    }

    private String getHostAddress() {
        try {
            InetAddress inetAddress;
            inetAddress = InetAddress.getLocalHost();
            String hostName = inetAddress.getHostAddress();
            if (hostName == null)
                hostName = "";
            return hostName;
        } catch (Exception e) {
        }
        return "";
    }

    private String getHostName() {
        try {
            InetAddress inetAddress;
            inetAddress = InetAddress.getLocalHost();
            String hostName = inetAddress.getHostName();
            if (hostName == null)
                hostName = "";
            return hostName;
        } catch (Exception e) {
        }
        return "";
    }
}
