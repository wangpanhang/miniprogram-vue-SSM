package com.wxfoods.entity;

public class userReviewModel {
    private int review_Id;
    private String reviewer; //评论者
    private String review_Content; //评论内容
    private String review_Time; //评论时间
    private String dynamicer; //发表动态者
    private String image_Path; //图片
    private String dynamic_Content; //动态内容
    private String dynamic_Time; //发表动态时间

    public int getReview_Id() {
        return review_Id;
    }

    public void setReview_Id(int review_Id) {
        this.review_Id = review_Id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReview_Content() {
        return review_Content;
    }

    public void setReview_Content(String review_Content) {
        this.review_Content = review_Content;
    }

    public String getReview_Time() {
        return review_Time;
    }

    public void setReview_Time(String review_Time) {
        this.review_Time = review_Time;
    }

    public String getDynamicer() {
        return dynamicer;
    }

    public void setDynamicer(String dynamicer) {
        this.dynamicer = dynamicer;
    }

    public String getImage_Path() {
        return image_Path;
    }

    public void setImage_Path(String image_Path) {
        this.image_Path = image_Path;
    }

    public String getDynamic_Content() {
        return dynamic_Content;
    }

    public void setDynamic_Content(String dynamic_Content) {
        this.dynamic_Content = dynamic_Content;
    }

    public String getDynamic_Time() {
        return dynamic_Time;
    }

    public void setDynamic_Time(String dynamic_Time) {
        this.dynamic_Time = dynamic_Time;
    }
}
