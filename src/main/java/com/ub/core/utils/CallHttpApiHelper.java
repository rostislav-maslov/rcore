package com.ub.core.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallHttpApiHelper {





    public HttpURLConnection getConnection(String url, String requestStr, String connectType) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(connectType);
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Content-Length", String.valueOf(requestStr.length()));

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(requestStr);
        wr.flush();
        wr.close();

        return con;
    }

    public StringBuffer getResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }
}
