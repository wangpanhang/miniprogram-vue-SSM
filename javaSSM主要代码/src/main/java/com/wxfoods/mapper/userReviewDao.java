package com.wxfoods.mapper;

import com.wxfoods.entity.userReviewModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface userReviewDao {
    public List<userReviewModel> getUserReview(Map<String,Object> data);
    public int getReviewCount();
    public int delUserReview(@Param("reviewId") int review_Id);
}
