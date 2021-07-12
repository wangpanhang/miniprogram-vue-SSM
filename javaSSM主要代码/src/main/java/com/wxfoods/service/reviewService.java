package com.wxfoods.service;

import com.wxfoods.entity.reviewModel;

import java.util.List;

public interface reviewService {
    public int insertReviewUser(Integer dynamic_Id,Integer user_Id,String user_NickName,String content);
    public List<reviewModel> getReviewUser(Integer dynamic_Id);
}
