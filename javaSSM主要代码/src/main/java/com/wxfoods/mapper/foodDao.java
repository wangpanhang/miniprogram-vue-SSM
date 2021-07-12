package com.wxfoods.mapper;

import com.wxfoods.entity.foodInfoModel;
import com.wxfoods.entity.foodStepModel;

import java.util.List;

public interface foodDao {
    public List<foodInfoModel> getFoodList(int id);
    public List<foodStepModel> getFoodStep(int id);
    public List<foodInfoModel> getSearchList(String food);
    public List<foodInfoModel> getUserCollectList(int user_Id);
}
