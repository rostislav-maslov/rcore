package com.ub.vk.statparam;

public class AuthorizeVkStatic {
    public static final String AUTHORIZE_URL = "https://oauth.vk.com/authorize";
    public static final String ACCESS_TOKEN_URL = "https://oauth.vk.com/access_token";

    public static final String P_CLIENT_ID = "client_id";//	✔	Идентификатор Вашего приложения
    public static final String P_CLIENT_SECRET = "client_secret";
    public static final String P_CODE = "code";
    public static final String P_REDIRECT_URL = "redirect_uri"; //	✔	Адрес, на который будет переадресован пользователь после прохождения авторизации (домен указанного адреса должен соответствовать основному домену в настройках приложения). *
    public static final String P_DISPLAY = "display"; //	✔	Указывает тип отображения страницы авторизации. Поддерживаются следующие варианты:
    public static final String V_DISPLAY_PAGE = "page";// — форма авторизации в отдельном окне;
    public static final String V_DISPLAY_POPUP = "popup";// — всплывающее окно;
    public static final String V_DISPLAY_MOBILE = "mobile";// — авторизация для мобильных устройств (без использования Javascript)
    public static final String P_SCOPE = "scope";//		Битовая маска настроек доступа приложения, которые необходимо проверить при авторизации пользователя и запросить, в случае отсутствия необходимых.
    public static final String P_RESPONSE_TYPE = "response_type";
}
