package com.wxfoods.controller;

import com.wxfoods.entity.foodClsModel;
import com.wxfoods.service.foodClsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller  //声明这是一个servlet类
@RequestMapping(value = "/foodCls")
public class FoodClsController {
    @Autowired
    private foodClsService foodClsService;

    @RequestMapping("/classify.do")
    @ResponseBody
    public List<foodClsModel> buttonTest(){
        return foodClsService.getFoodCls();
    }

}
