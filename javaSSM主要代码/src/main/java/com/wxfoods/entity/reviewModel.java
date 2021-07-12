package com.wxfoods.entity;

public class reviewModel {
    private Integer review_Id;
    private Integer dynamic_Id;
    private Integer user_Id;
    private String user_NickName;
    private String content;
    private String reviewDate;

    public Integer getReview_Id() {
        return review_Id;
    }

    public Integer getDynamic_Id() {
        return dynamic_Id;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public String getUser_NickName() {
        return user_NickName;
    }

    public String getContent() {
        return content;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReview_Id(Integer review_Id) {
        this.review_Id = review_Id;
    }

    public void setDynamic_Id(Integer dynamic_Id) {
        this.dynamic_Id = dynamic_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public void setUser_NickName(String user_NickName) {
        this.user_NickName = user_NickName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
