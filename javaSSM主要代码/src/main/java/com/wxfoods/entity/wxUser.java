package com.wxfoods.entity;

public class wxUser {
    private int user_Id;
    private String user_OpenId;
    private String user_NickName;
    private String user_AvatarUrl;
    public void setUserId(int userId){
        this.user_Id = userId;
    }

    public int getUserId(){
        return user_Id;
    }

    public void setUserOpenId(String userOpenId){
        this.user_OpenId = userOpenId;
    }

    public String getUserOpenId(){
        return user_OpenId;
    }

    public void setUserNickName(String userNickName){
        this.user_NickName = userNickName;
    }

    public String getUserNickName(){
        return user_NickName;
    }

    public void setUserAvatarUrl(String userAvatarUrl){
        this.user_AvatarUrl = userAvatarUrl;
    }

    public String getUserAvatarUrl(){
        return user_AvatarUrl;
    }
}
