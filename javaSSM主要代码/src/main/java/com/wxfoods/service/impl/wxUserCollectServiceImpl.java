package com.wxfoods.service.impl;

import com.wxfoods.entity.wxUserCollect;
import com.wxfoods.mapper.wxUserCollectDao;
import com.wxfoods.service.wxUserCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class wxUserCollectServiceImpl implements wxUserCollectService {
    @Resource
    private wxUserCollectDao wxUserCollectDao;
    private String statu = "error";

    @Override
    public int findWxUserCollect(int user_Id, int food_Id) {
        return wxUserCollectDao.findWxUserCollect(user_Id,food_Id);
    }

    @Override
    public String changeWxUserCollect(int user_Id, int food_Id, int collect) {
        wxUserCollect wxUserCollect = new wxUserCollect();
        wxUserCollect.setUser_Id(user_Id);
        wxUserCollect.setFood_Id(food_Id);
        if(collect!=0){
            if(wxUserCollectDao.deleteWxUserCollect(wxUserCollect)>0){
                statu = "delete";
            }
        }
        else{
            if(wxUserCollectDao.insertWxUserCollect(wxUserCollect)>0){
                statu = "insert";
            }
        }
        return statu;
    }
}
