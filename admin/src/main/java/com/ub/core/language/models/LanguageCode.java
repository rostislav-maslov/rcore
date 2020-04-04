package com.ub.core.language.models;

import com.ub.core.base.httpResponse.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
 */
public class LanguageCode {
    private String lang;
    private String langNative;
    private String code2;
    private String code3;


    public static final LanguageCode English = new LanguageCode("English", "English", "en", "eng");
    public static final LanguageCode Russian = new LanguageCode("Russian", "Русский", "ru", "rus");

    public static LanguageCode defaultLanguage = Russian;
    public static boolean isShowLanguageButton = true; // Показывать ли в админке выбор языка

    public static LanguageCode getLangbyCode2(String code2){
        for(LanguageCode languageCode : all()){
            if(languageCode.getCode2().equals(code2))
                return languageCode;
        }
        return null;
    }

    public static LanguageCode getLangbyCode2ThrowException(String code2){
        for(LanguageCode languageCode : all()){
            if(languageCode.getCode2().equals(code2))
                return languageCode;
        }
        throw new ResourceNotFoundException();
    }

    public static List<LanguageCode> all() {
        List<LanguageCode> all = new ArrayList<LanguageCode>();
        all.add(Russian);
        all.add(English);
        return all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguageCode that = (LanguageCode) o;

        if (!code2.equals(that.code2)) return false;
        if (!code3.equals(that.code3)) return false;
        if (!lang.equals(that.lang)) return false;
        if (!langNative.equals(that.langNative)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lang.hashCode();
        result = 31 * result + langNative.hashCode();
        result = 31 * result + code2.hashCode();
        result = 31 * result + code3.hashCode();
        return result;
    }

    public LanguageCode() {
    }

    public LanguageCode(String lang, String langNative, String code2, String code3) {
        this.lang = lang;
        this.langNative = langNative;
        this.code2 = code2;
        this.code3 = code3;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLangNative() {
        return langNative;
    }

    public void setLangNative(String langNative) {
        this.langNative = langNative;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getCode3() {
        return code3;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }
}
