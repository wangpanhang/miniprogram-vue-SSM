package com.wxfoods.service.impl;

import com.wxfoods.entity.reviewModel;
import com.wxfoods.mapper.reviewDao;
import com.wxfoods.service.reviewService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class reviewServiceImpl implements reviewService {
    private reviewModel reviewModel = new reviewModel();

    @Resource
    private reviewDao reviewDao;

    @Override
    public int insertReviewUser(Integer dynamic_Id, Integer user_Id, String user_NickName, String content) {
        Date date = new Date();
        SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String time = SDF.format(date);
        reviewModel.setDynamic_Id(dynamic_Id);
        reviewModel.setUser_Id(user_Id);
        reviewModel.setUser_NickName(user_NickName);
        reviewModel.setContent(content);
        reviewModel.setReviewDate(time);
        return reviewDao.insertReview(reviewModel);
    }

    @Override
    public List<reviewModel> getReviewUser(Integer dynamic_Id) {
        return reviewDao.getReview(dynamic_Id);
    }
}
