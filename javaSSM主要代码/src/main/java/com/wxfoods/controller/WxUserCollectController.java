package com.wxfoods.controller;

import com.wxfoods.service.wxUserCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="wxUserCollect")
public class WxUserCollectController {
    @Autowired
    private wxUserCollectService wxUserCollectService;

    /**
     * '
     * @param user_Id 用户编号
     * @param food_Id 菜谱编号
     * @return 如果是1则代表查询成功，否则为0
     */
    @RequestMapping("collect.do")
    @ResponseBody
    public int findWxUserCollect(@RequestParam(value="user_Id") int user_Id,@RequestParam(value="food_Id") int food_Id){
        return wxUserCollectService.findWxUserCollect(user_Id,food_Id);
    }

    /**
     *
     * @param user_Id
     * @param food_Id
     * @param collect
     * @return
     */
    @RequestMapping("addcollect.do")
    @ResponseBody
    public String addWxUserCollect(@RequestParam(value="user_Id") int user_Id,@RequestParam(value="food_Id") int food_Id,@RequestParam(value="collect") int collect){
        return wxUserCollectService.changeWxUserCollect(user_Id,food_Id,collect);
    }
}
