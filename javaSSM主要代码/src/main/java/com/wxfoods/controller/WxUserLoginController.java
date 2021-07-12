package com.wxfoods.controller;

import com.wxfoods.entity.foodInfoModel;
import com.wxfoods.entity.wxUser;
import com.wxfoods.service.foodService;
import com.wxfoods.service.wxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@RequestMapping(value="wxLogin")
@Controller
public class WxUserLoginController {
    @Autowired
    private wxUserService wxUserService;

    @Autowired
    private foodService foodService;

    @RequestMapping("wxCode.do")
    @ResponseBody
    public wxUser getOpenId(@RequestParam(value="name") String name, @RequestParam(value="image") String image, @RequestParam(value="code") String code){
        wxUser total = wxUserService.checkWxUser(name,image,code);
        return total;
    }

    @RequestMapping("wxCollectInfo.do")
    @ResponseBody
    public List<foodInfoModel> getFoodList(@RequestParam(value = "user_Id") int userId){
        return foodService.getUserCollectList(userId);
    }

}
