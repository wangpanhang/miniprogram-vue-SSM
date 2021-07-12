package com.wxfoods.service.impl;

import com.wxfoods.entity.oneManageModel;
import com.wxfoods.mapper.manageDao;
import com.wxfoods.service.manageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class manageServiceImpl implements manageService {
    @Resource
    private manageDao manageDao;

    @Override
    public Map<String, Object> getManage() {
        Map<String,Object> metaMap = new HashMap<>();
        Map<String,Object> manageMap = new HashMap<>();
        List<oneManageModel> managemodel = manageDao.getManageInfo();
        if(managemodel != null){
            manageMap.put("data",managemodel);
            metaMap.put("msg","成功获取管理信息");
            metaMap.put("status",200);
            manageMap.put("meta",metaMap);
        }
        else{
            manageMap.put("data",managemodel);
            metaMap.put("msg","获取管理信息失败");
            metaMap.put("status",400);
            manageMap.put("meta",metaMap);
        }
        return manageMap;
    }
}
