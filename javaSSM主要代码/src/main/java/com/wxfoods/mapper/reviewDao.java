package com.wxfoods.mapper;

import com.wxfoods.entity.reviewModel;

import java.util.List;

public interface reviewDao {
    public int insertReview(reviewModel reviewModel);
    public List<reviewModel> getReview(Integer dynamic_Id);
}
