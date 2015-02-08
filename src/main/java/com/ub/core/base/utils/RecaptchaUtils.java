package com.ub.core.base.utils;

public class RecaptchaUtils {
    /**public static Boolean check() {
        HttpsUtils httpsUtils = new HttpsUtils("https://www.google.com/recaptcha/api/siteverify");
        httpsUtils.addParam("secret", "6LeKqgETAAAAAAcflWyHAC7SeDv-ktECKbzshQWQ");
        httpsUtils.addParam("response", grecaptcharesponse);
        String res = httpsUtils.sendGet();

        JsonElement root = new JsonParser().parse(res);
        String resSuc = root.getAsJsonObject().get("success").getAsString();

        if (resSuc.equals("true")) {
            return true;
        }
        return false;

    }**/
}
