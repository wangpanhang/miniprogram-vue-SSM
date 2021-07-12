package com.wxfoods.service;

import com.wxfoods.entity.foodModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface foodManageService {
    public List<foodModel> getFoodList(int currpage, int pagesize);
    public int getFoodCount();
    public Map<String,Object> delFoodInfo(int food_Id);
    public Map<String,Object> alertFoodInfo(foodModel foodModel);
    public List<foodModel> getSearchFood(String query);
    public foodModel getFoodInfo(int food_Id);
    public Map<String,Object> uploadfile(String username, String mainIngred, String ingred, String dosing, String author, Integer clsid, MultipartFile file) throws IOException;
}
