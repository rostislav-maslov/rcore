package com.ub.core.security.session;

import org.bson.types.ObjectId;

import javax.servlet.http.HttpSession;

public class SessionModel {
    public static final String ID_USER = "ID_U";
    public static final String TOKEN = "TOKEN";
    public static final String TYPE = "TYPE";

    private ObjectId idUser;
    private String token;
    private SessionType type;

    public SessionModel() {

    }


    public HttpSession fillSession(HttpSession httpSession) {
        httpSession.setAttribute(ID_USER, idUser.toString());
        httpSession.setAttribute(TOKEN, token);

        if (type.equals(SessionType.EMAIL))
            httpSession.setAttribute(TYPE, "E");
        if (type.equals(SessionType.VK))
            httpSession.setAttribute(TYPE, "V");
        if (type.equals(SessionType.GOOGLE))
            httpSession.setAttribute(TYPE, "G");
        if (type.equals(SessionType.OK))
            httpSession.setAttribute(TYPE, "O");
        if (type.equals(SessionType.TWITTER))
            httpSession.setAttribute(TYPE, "T");
        if (type.equals(SessionType.LINKEDIN))
            httpSession.setAttribute(TYPE, "L");
        if (type.equals(SessionType.FB))
            httpSession.setAttribute(TYPE, "F");

        return httpSession;
    }

    public SessionModel(HttpSession httpSession) {
        if ((String) httpSession.getAttribute(ID_USER) != null) {
            idUser = new ObjectId((String) httpSession.getAttribute(ID_USER));
        }
        if ((String) httpSession.getAttribute(TOKEN) != null) {
            token = ((String) httpSession.getAttribute(TOKEN));
        }
        if ((String) httpSession.getAttribute(TYPE) != null) {
            String t = ((String) httpSession.getAttribute(TYPE));
            if (t.equals("E"))
                type = SessionType.EMAIL;
            if (t.equals("V"))
                type = SessionType.VK;
            if (t.equals("G"))
                type = SessionType.GOOGLE;
            if (t.equals("O"))
                type = SessionType.OK;
            if (t.equals("T"))
                type = SessionType.TWITTER;
            if (t.equals("L"))
                type = SessionType.LINKEDIN;
            if (t.equals("F"))
                type = SessionType.FB;
        }
    }

    public ObjectId getIdUser() {
        return idUser;
    }

    public void setIdUser(ObjectId idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SessionType getType() {
        return type;
    }

    public void setType(SessionType type) {
        this.type = type;
    }
}
