package com.wxfoods.controller.admin;

import com.wxfoods.entity.userDynamicModel;
import com.wxfoods.service.userDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userdynamic")
@CrossOrigin //解决vue和SSM端口不一致的跨域问题
public class userDynamicController {
    @Autowired
    private userDynamicService userDynamicService;

    @RequestMapping("getuserdynamic.do")
    @ResponseBody
    public List<userDynamicModel> getUserReview(@RequestParam(value="currpage") int currpage, @RequestParam(value="pagesize") int pagesize){
        return userDynamicService.getUserDynamic(currpage, pagesize);
    }

    @RequestMapping(value = "/getdynamiccount.do")
    @ResponseBody
    public int getFoodCount(){
        return userDynamicService.getDynamicCount();
    }

    @RequestMapping(value = "/deluserdynamic.do")
    @ResponseBody
    public Map<String,Object> delUserReview(@RequestParam(value="dynamic_Id") int dynamic_Id){
        return userDynamicService.delUserDynamic(dynamic_Id);
    }

}
