package com.ub.facebook.statparam;

public class AuthorizeFbStatic {
    public static final String AUTHORIZE_URL = "https://www.facebook.com/dialog/oauth";
    public static final String ACCESS_TOKEN_URL = "https://graph.facebook.com/v2.3/oauth/access_token";
    public static final String ME_URL = "https://graph.facebook.com/me";
    public static final String GRAPH_URL = "https://graph.facebook.com/v2.4/";

    public static final String P_CLIENT_ID = "client_id";//	✔	Идентификатор Вашего приложения
    public static final String P_APP_ID = "app_id";//	✔	Идентификатор Вашего приложения
    public static final String P_CLIENT_SECRET = "client_secret";
    public static final String P_CODE = "code";
    public static final String P_REDIRECT_URL = "redirect_uri"; //	✔	Адрес, на который будет переадресован пользователь после прохождения авторизации (домен указанного адреса должен соответствовать основному домену в настройках приложения). *
    public static final String P_SCOPE = "scope";//		Битовая маска настроек доступа приложения, которые необходимо проверить при авторизации пользователя и запросить, в случае отсутствия необходимых.
    public static final String P_RESPONSE_TYPE = "response_type";
    public static final String P_ACCESS_TOKEN = "access_token";
}
