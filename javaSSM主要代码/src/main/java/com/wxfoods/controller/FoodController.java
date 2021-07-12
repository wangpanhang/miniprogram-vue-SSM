package com.wxfoods.controller;

import com.wxfoods.entity.foodInfoModel;
import com.wxfoods.entity.foodStepModel;
import com.wxfoods.service.foodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller//声明这是一个servlet类
@RequestMapping(value="/food")
public class FoodController {
    @Autowired
    private foodService foodService;

    @RequestMapping("/foodInfo.do")//为当前方法配置一个虚拟的访问路径
    @ResponseBody
    public List<foodInfoModel> getFoodList(@RequestParam(value = "id") int id){
        return foodService.getFoodInfoList(id);
    }

    @RequestMapping("/foodStep.do")
    @ResponseBody
    public List<foodStepModel> getFoodStep(@RequestParam(value = "id") int id){
        return foodService.getFoodStep(id);
    }

    @RequestMapping("/searchInfo.do")
    @ResponseBody
    public List<foodInfoModel> getSearchList(@RequestParam(value = "food") String food){
        return foodService.getSearchList(food);
    }
}
