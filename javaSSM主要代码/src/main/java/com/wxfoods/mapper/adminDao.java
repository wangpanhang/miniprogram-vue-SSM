package com.wxfoods.mapper;

import com.wxfoods.entity.adminModel;

public interface adminDao {
    public adminModel checkAdmin(adminModel adminmodel);
    public int inserAdmin(adminModel adminModel);
    public int alertPwd(adminModel adminmodel);
}
