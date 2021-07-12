package com.wxfoods.service;

import com.wxfoods.entity.userReviewModel;

import java.util.List;
import java.util.Map;

public interface userReviewService {
    public List<userReviewModel> getUserReview(int currpage, int pagesize);
    public int getReviewCount();
    public Map<String,Object> delUserReview(int review_Id);
}
