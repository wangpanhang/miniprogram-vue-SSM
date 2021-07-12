package com.wxfoods.service.impl;

import com.wxfoods.entity.foodInfoModel;
import com.wxfoods.entity.foodModel;
import com.wxfoods.mapper.foodManageDao;
import com.wxfoods.service.foodManageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class FoodManageServiceImpl implements foodManageService {
    @Resource
    private foodManageDao foodManageDao;

    private String url = "http://localhost:8080/wxfood/";
    private String filepath = "E://wxfood/target/wxfood/images/";

    /***
     *  获取菜谱列表
     * @param currpage
     * @param pagesize
     * @return
     */
    @Override
    public List<foodModel> getFoodList(int currpage, int pagesize) {
        Map<String, Object> data = new HashMap<>();
        data.put("currpage", (currpage-1)*pagesize);
        data.put("pagesize", pagesize);
        List<foodModel> foodModels = foodManageDao.getFoodList(data);
        Iterator<foodModel> iter = foodModels.iterator();
        while(iter.hasNext()){
            foodModel foodmodel = iter.next();
            String image_path = foodmodel.getFood_Image();
            foodmodel.setFood_Image(url+image_path);
        }
        return foodModels;
    }

    /***
     * 获取菜谱数量
     * @return
     */
    @Override
    public int getFoodCount() {
        return foodManageDao.getFoodCount();
    }

    /***
     * 删除选中的菜谱
     * @param food_Id
     * @return
     */
    @Override
    public Map<String, Object> delFoodInfo(int food_Id) {
        Map<String,Object> metaMap = new HashMap<>();
        Map<String,Object> backMap = new HashMap<>();
        int i = foodManageDao.delFoodInfo(food_Id);
        if(i!=0){
            metaMap.put("msg","删除菜谱信息成功");
            metaMap.put("status",200);
            backMap.put("meta",metaMap);
        }
        else{
            metaMap.put("msg","删除菜谱信息失败");
            metaMap.put("status",400);
            backMap.put("meta",metaMap);
        }
        return backMap;
    }

    @Override
    public Map<String,Object> alertFoodInfo(foodModel foodModel) {
        Map<String,Object> metaMap = new HashMap<>();
        Map<String,Object> backMap = new HashMap<>();
        int i = foodManageDao.alertFoodInfo(foodModel);
        if(i!=0){
            metaMap.put("msg","修改菜谱信息成功");
            metaMap.put("status",200);
            backMap.put("meta",metaMap);
        }
        else{
            metaMap.put("msg","修改菜谱信息失败");
            metaMap.put("status",400);
            backMap.put("meta",metaMap);
        }
        return backMap;
    }

    @Override
    public List<foodModel> getSearchFood(String query) {
        List<foodModel> foodModels = foodManageDao.getSearchFood(query);
        Iterator<foodModel> iter = foodModels.iterator();
        while(iter.hasNext()){
            foodModel foodmodel = iter.next();
            String image_path = foodmodel.getFood_Image();
            foodmodel.setFood_Image(url+image_path);
        }
        return foodModels;
    }

    @Override
    public foodModel getFoodInfo(int food_Id) {
        foodModel foodModel = foodManageDao.getFoodInfo(food_Id);
        String image_path = foodModel.getFood_Image();
        foodModel.setFood_Image(url+image_path);
        return foodModel;
    }

    @Override
    public Map<String,Object> uploadfile(String username, String mainIngred, String ingred, String dosing, String author, Integer clsid, MultipartFile file) throws IOException {
        Map<String,Object> backMap = new HashMap<>();
        Map<String,Object> metaMap = new HashMap<>();
        if(!file.isEmpty()){
            String filename = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = filename.indexOf(".")!=-1?filename.substring(filename.lastIndexOf(".")+1,filename.length()):null;
            if(type!=null){
                if("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())||"JPEG".equals(type.toUpperCase())){
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + "."+type;
                    path = filepath+"insertfood/"+trueFileName;
                    File dest = new File(path);
                    if(!dest.getParentFile().exists()){
                        dest.getParentFile().mkdir();
                    }
                    file.transferTo(dest);
                    backMap =  this.insertfoodInfo(username,mainIngred,ingred,dosing,author,clsid,trueFileName);
                    return backMap;
                }
                metaMap.put("status",400);
                metaMap.put("msg","上传图片的类型错误");
                backMap.put("meta",metaMap);
                return backMap;
            }
            metaMap.put("status",400);
            metaMap.put("msg","上传图片的类型错误");
            backMap.put("meta",metaMap);
            return backMap;
        }
        metaMap.put("status",400);
        metaMap.put("msg","你未上传图片");
        backMap.put("meta",metaMap);
        return backMap;
    }

    public Map<String,Object> insertfoodInfo(String username, String mainIngred, String ingred, String dosing, String author, Integer clsid, String trueFileName) {
        Map<String,Object> backMap = new HashMap<>();
        Map<String,Object> metaMap = new HashMap<>();
        foodInfoModel foodInfoModel = new foodInfoModel();
        String imgPath = "images/insertfood/"+trueFileName;
        foodInfoModel.setFoodname(username);
        foodInfoModel.setFoodmainingred(mainIngred);
        foodInfoModel.setFoodingred(ingred);
        foodInfoModel.setFooddosing(dosing);
        foodInfoModel.setFoodauthor(author);
        foodInfoModel.setFood_ClsId(clsid);
        foodInfoModel.setFoodimage(imgPath);
        foodInfoModel.setFoodcount(2000);
        int i = foodManageDao.foodInsert(foodInfoModel);
        if(i!=0){
            metaMap.put("status",200);
            metaMap.put("msg","添加菜谱成功");
        }else{
            metaMap.put("status",400);
            metaMap.put("msg","添加菜谱失败");
        }
        backMap.put("meta",metaMap);
        return backMap;
    }
}
