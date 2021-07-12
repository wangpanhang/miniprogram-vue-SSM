package com.wxfoods.service.impl;
import com.wxfoods.entity.foodInfoModel;
import com.wxfoods.entity.foodStepModel;
import com.wxfoods.mapper.foodDao;
import com.wxfoods.service.foodService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service
public class foodServiceImpl implements foodService {

    @Resource
    private foodDao foodDao;
    private String url = "http://localhost:8080/wxfood/";

    @Override
    public List<foodInfoModel> getFoodInfoList(int id) {
        List<foodInfoModel> foodInfoModels = foodDao.getFoodList(id);
        Iterator<foodInfoModel> iter = foodInfoModels.iterator();
        while(iter.hasNext()){
            foodInfoModel foodinfomodel = iter.next();
            String image_path = foodinfomodel.getFoodimage();
            foodinfomodel.setFoodimage(url+image_path);
        }
        return foodInfoModels;
    }

    @Override
    public List<foodStepModel> getFoodStep(int id) {
        List<foodStepModel> foodStepModels = foodDao.getFoodStep(id);
        Iterator<foodStepModel> iter = foodStepModels.iterator();
        while(iter.hasNext()){
            foodStepModel foodStepModel1 = iter.next();
            String image_path = foodStepModel1.getFoodimage();
            foodStepModel1.setFoodimage(url+image_path);
        }
        return foodStepModels;
    }

    @Override
    public List<foodInfoModel> getSearchList(String food) {
        List<foodInfoModel> foodInfoModels = foodDao.getSearchList(food);
        Iterator<foodInfoModel> iter = foodInfoModels.iterator();
        while(iter.hasNext()){
            foodInfoModel foodinfomodel = iter.next();
            String image_path = foodinfomodel.getFoodimage();
            foodinfomodel.setFoodimage(url+image_path);
        }
        return foodInfoModels;
    }

    @Override
    public List<foodInfoModel> getUserCollectList(int userId) {
        List<foodInfoModel> foodInfoModels = foodDao.getUserCollectList(userId);
        Iterator<foodInfoModel> iter = foodInfoModels.iterator();
        while(iter.hasNext()){
            foodInfoModel foodinfomodel = iter.next();
            String image_path = foodinfomodel.getFoodimage();
            foodinfomodel.setFoodimage(url+image_path);
        }
        return foodInfoModels;
    }
}
