package com.ub.core.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class HttpsUtils {

    private Map<String, String> params = new HashMap<String, String>();
    private String url = "";

    public HttpsUtils(String utl) {
        this.url = utl;
    }

    public void addParam(String name, String value) {
        params.put(name, value);
    }

    public String sendGet() throws Exception {
        String url = this.url;

        StringBuffer stringBuffer = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        for (int i = 0; i < keys.size(); i++) {
            stringBuffer.append(keys.get(i));
            stringBuffer.append("=");
            stringBuffer.append(URLEncoder.encode(params.get(keys.get(i)), "UTF-8"));
            if (i < keys.size() - 1)
                stringBuffer.append("&");
        }
        String urlParameters = stringBuffer.toString();
        if (urlParameters.length() > 0) {
            url += "?" + stringBuffer.toString();
        }

        URL urlObj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) urlObj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new String(response.toString().getBytes(), "UTF-8");
    }
}
