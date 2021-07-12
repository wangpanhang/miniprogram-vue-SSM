package com.wxfoods.service.impl;

import com.wxfoods.entity.foodModel;
import com.wxfoods.entity.userReviewModel;
import com.wxfoods.mapper.userReviewDao;
import com.wxfoods.service.userReviewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class userReviewServiceImpl implements userReviewService {
    @Resource
    private userReviewDao userReviewDao;

    private String url = "http://localhost:8080/wxfood/";
    @Override
    public List<userReviewModel> getUserReview(int currpage, int pagesize) {
        Map<String, Object> data = new HashMap<>();
        data.put("currpage", (currpage-1)*pagesize);
        data.put("pagesize", pagesize);
        List<userReviewModel> userReviewModels = userReviewDao.getUserReview(data);
        Iterator<userReviewModel> iter = userReviewModels.iterator();
        while(iter.hasNext()){
            userReviewModel userReviewModel = iter.next();
            String image_path = userReviewModel.getImage_Path();
            userReviewModel.setImage_Path(url+image_path);
        }
        return userReviewModels;
    }

    @Override
    public int getReviewCount() {
        return userReviewDao.getReviewCount();
    }

    @Override
    public Map<String, Object> delUserReview(int review_Id) {
        Map<String,Object> metaMap = new HashMap<>();
        Map<String,Object> backMap = new HashMap<>();
        int i = userReviewDao.delUserReview(review_Id);
        if(i!=0){
            metaMap.put("msg","删除用户评论成功");
            metaMap.put("status",200);
            backMap.put("meta",metaMap);
        }
        else{
            metaMap.put("msg","删除用户评论失败");
            metaMap.put("status",400);
            backMap.put("meta",metaMap);
        }
        return backMap;
    }
}
