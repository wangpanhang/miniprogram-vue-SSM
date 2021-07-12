package com.wxfoods.mapper;

import com.wxfoods.entity.foodInfoModel;
import com.wxfoods.entity.foodModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface foodManageDao {
    public int foodInsert(foodInfoModel foodInfoModel);
    public List<foodModel> getFoodList(Map<String,Object> data);
    public int getFoodCount();
    public int alertFoodInfo(foodModel foodModel);
    public int delFoodInfo(@Param("foodId") int food_Id);
    public foodModel getFoodInfo(@Param("foodId") int food_Id);
    public List<foodModel> getSearchFood(@Param("query") String query);
}
