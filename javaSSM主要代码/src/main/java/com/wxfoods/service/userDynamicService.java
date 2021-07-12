package com.wxfoods.service;

import com.wxfoods.entity.userDynamicModel;

import java.util.List;
import java.util.Map;

public interface userDynamicService {
    public List<userDynamicModel> getUserDynamic(int currpage, int pagesize);
    public int getDynamicCount();
    public Map<String,Object> delUserDynamic(int dynamic_Id);
}
