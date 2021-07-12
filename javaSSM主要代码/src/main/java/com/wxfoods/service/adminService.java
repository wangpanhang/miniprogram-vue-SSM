package com.wxfoods.service;

import com.wxfoods.entity.adminModel;

import java.util.Map;

public interface adminService {
    public Map<String,Object> checkAdmin(adminModel adminmodel);
    public int insertAdmin(adminModel adminmodel);
    public Map<String,Object> alertPwd(adminModel adminmodel);
}
