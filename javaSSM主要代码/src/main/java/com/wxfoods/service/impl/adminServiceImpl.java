package com.wxfoods.service.impl;

import com.wxfoods.entity.adminModel;
import com.wxfoods.mapper.adminDao;
import com.wxfoods.service.adminService;
import com.wxfoods.utils.tokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class adminServiceImpl implements adminService {
    @Resource
    private adminDao adminDao;
    @Override
    public Map<String,Object> checkAdmin(adminModel adminmodel) {
        Map<String,Object> loginMap = new HashMap<>();
        Map<String,Object> dataMap = new HashMap<>();
        Map<String,Object> metaMap = new HashMap<>();
        adminModel admin = adminDao.checkAdmin(adminmodel);
        if(admin != null){
            //生成token
            String token = tokenUtils.sign(admin.getUsername(),admin.getPassword());
            dataMap.put("token",token);
            dataMap.put("user",admin.getUsername());
            loginMap.put("data",dataMap);
            metaMap.put("msg","登录成功");
            metaMap.put("status",200);
        }
        else{
            metaMap.put("msg","登录失败，用户名或密码错误");
            metaMap.put("status",400);
        }
        loginMap.put("meta",metaMap);
        return loginMap;
    }

    @Override
    public int insertAdmin(adminModel adminmodel) {
        return adminDao.inserAdmin(adminmodel);
    }

    @Override
    public Map<String, Object> alertPwd(adminModel adminmodel) {
        Map<String,Object> backMap = new HashMap<>();
        Map<String,Object> metaMap = new HashMap<>();
        int i = adminDao.alertPwd(adminmodel);
        if(i!=0){
            metaMap.put("status",200);
            metaMap.put("msg","修改密码成功，请重新登录");
        }
        else{
            metaMap.put("status",400);
            metaMap.put("msg","修改密码失败");
        }
        backMap.put("meta",metaMap);
        return backMap;
    }
}
