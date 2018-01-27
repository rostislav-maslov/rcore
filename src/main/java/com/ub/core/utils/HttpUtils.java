package com.ub.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private Map<String, String> params = new HashMap<String, String>();
    private Map<String, String> headers = new HashMap<String, String>();
    private String url = "";

    public HttpUtils(String utl) {
        this.url = utl;
    }

    public void addParam(String name, String value) {
        params.put(name, value);
    }

    public String getUrl() {
        String url = this.url;

        StringBuffer stringBuffer = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        for (int i = 0; i < keys.size(); i++) {
            try {
                stringBuffer.append(keys.get(i));
                stringBuffer.append("=");
                stringBuffer.append(URLEncoder.encode(params.get(keys.get(i)), "UTF-8"));
                if (i < keys.size() - 1)
                    stringBuffer.append("&");
            }catch (Exception e){

            }
        }
        String urlParameters = stringBuffer.toString();
        if (urlParameters.length() > 0) {
            url += "?" + stringBuffer.toString();
        }
        return url;
    }

    public String sendGet() throws IOException{
        String url = getUrl();

        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(300);


//        if(this.getHeaders().size() > 0){
//            CookieManager cookieManager = new CookieManager();
//            CookieHandler.setDefault(cookieManager);
//        }
        for(String key: this.headers.keySet()){
            con.setRequestProperty(key, this.getHeaders().get(key));
        }


        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new String(response.toString().getBytes(),"UTF-8");
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
