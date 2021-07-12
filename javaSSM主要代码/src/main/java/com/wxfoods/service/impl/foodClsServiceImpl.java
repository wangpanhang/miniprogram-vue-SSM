package com.wxfoods.service.impl;

import com.wxfoods.entity.foodClsModel;
import com.wxfoods.mapper.foodClsDao;
import com.wxfoods.service.foodClsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class foodClsServiceImpl implements foodClsService {
    @Resource
    private foodClsDao foodClsDao;

    @Override
    public List<foodClsModel> getFoodCls() {
        return foodClsDao.foodClsInfo();
    }
}
