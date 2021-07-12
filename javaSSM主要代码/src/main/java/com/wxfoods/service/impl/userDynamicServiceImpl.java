package com.wxfoods.service.impl;

import com.wxfoods.entity.userDynamicModel;
import com.wxfoods.mapper.userDynamicDao;
import com.wxfoods.service.userDynamicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class userDynamicServiceImpl implements userDynamicService {
    @Resource
    private userDynamicDao userDynamicDao;

    private String url = "http://localhost:8080/wxfood/";
    @Override
    public List<userDynamicModel> getUserDynamic(int currpage, int pagesize) {
        Map<String, Object> data = new HashMap<>();
        data.put("currpage", (currpage-1)*pagesize);
        data.put("pagesize", pagesize);
        List<userDynamicModel> userDynamicModels = userDynamicDao.getUserDynamic(data);
        Iterator<userDynamicModel> iter = userDynamicModels.iterator();
        while(iter.hasNext()){
            userDynamicModel userDynamicModel = iter.next();
            String image_path = userDynamicModel.getImagePath();
            userDynamicModel.setImagePath(url+image_path);
        }
        return userDynamicModels;
    }

    @Override
    public int getDynamicCount() {
        return userDynamicDao.getDynamicCount();
    }

    @Override
    public Map<String, Object> delUserDynamic(int dynamic_Id) {
        Map<String,Object> metaMap = new HashMap<>();
        Map<String,Object> backMap = new HashMap<>();
        int i = userDynamicDao.delUserReview(dynamic_Id);
        if(i!=0){
            int j = userDynamicDao.delUserDynamic(dynamic_Id);
            if(j!=0) {
                metaMap.put("msg", "删除用户动态成功");
                metaMap.put("status", 200);
                backMap.put("meta", metaMap);
            }
            else{
                metaMap.put("msg","删除用户动态失败");
                metaMap.put("status",400);
                backMap.put("meta",metaMap);
            }
        }
        else{
            metaMap.put("msg","删除用户动态失败");
            metaMap.put("status",400);
            backMap.put("meta",metaMap);
        }
        return backMap;
    }
}
