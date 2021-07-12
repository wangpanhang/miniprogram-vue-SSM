package com.wxfoods.entity;

public class userDynamicModel {
    private Integer dynamic_Id;
    private Integer user_Id;
    private String user_NickName;
    private String imagePath;
    private String content;
    private String upLoadDate;

    public Integer getDynamic_Id() {
        return dynamic_Id;
    }

    public void setDynamic_Id(Integer dynamic_Id) {
        this.dynamic_Id = dynamic_Id;
    }

    public void setUser_Id(Integer userId){
        this.user_Id = userId;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImagePath(){
        return imagePath;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void setUpLoadDate(String upLoadDate){
        this.upLoadDate = upLoadDate;
    }

    public String getUpLoadDate(){
        return upLoadDate;
    }

    public String getUser_NickName() {
        return user_NickName;
    }

    public void setUser_NickName(String user_NickName) {
        this.user_NickName = user_NickName;
    }
}
