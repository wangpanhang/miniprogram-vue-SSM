package com.wxfoods.controller.admin;


import com.wxfoods.service.manageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
@RequestMapping("/manage")
@CrossOrigin //解决vue和SSM端口不一致的跨域问题
public class manageController {

    @Autowired
    private manageService manageService;

    @RequestMapping("/getmanage.do")
    @ResponseBody
    public Map<String,Object> getManage(){
        return manageService.getManage();
    }
}
