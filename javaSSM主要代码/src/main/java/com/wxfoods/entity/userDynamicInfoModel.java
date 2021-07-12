package com.wxfoods.entity;

import java.util.List;

public class userDynamicInfoModel {
    private Integer dynamic_Id;
    private String imagePath;
    private String content;
    private String upLoadDate;
    private String user_NickName;
    private String user_AvatarUrl;
    private List<reviewModel> reviewModelList;

    public Integer getDynamic_Id() {
        return dynamic_Id;
    }

    public void setDynamic_Id(Integer dynamic_Id) {
        this.dynamic_Id = dynamic_Id;
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

    public List<reviewModel> getReviewModelList() {
        return reviewModelList;
    }

    public void setReviewModelList(List<reviewModel> reviewModelList) {
        this.reviewModelList = reviewModelList;
    }
}
