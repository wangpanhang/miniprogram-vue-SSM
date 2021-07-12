package com.wxfoods.service;

import com.wxfoods.entity.foodInfoModel;
import com.wxfoods.entity.foodStepModel;

import java.util.List;

public interface foodService {
    public List<foodInfoModel> getFoodInfoList(int id);

    public List<foodStepModel> getFoodStep(int id);

    public List<foodInfoModel> getSearchList(String food);

    public List<foodInfoModel> getUserCollectList(int userId);
}
