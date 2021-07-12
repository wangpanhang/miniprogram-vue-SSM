package com.wxfoods.entity;


public class wxOpenId {
    private String openid;
    private String sessionkey;
    public void setOpenId(String openId){
        this.openid = openId;
    }
    public void setSessionKey(String sessionKey){
        this.sessionkey = sessionKey;
    }

    public String getOpenId(){
        return openid;
    }

    public String getSessionKey(){
        return sessionkey;
    }
}
