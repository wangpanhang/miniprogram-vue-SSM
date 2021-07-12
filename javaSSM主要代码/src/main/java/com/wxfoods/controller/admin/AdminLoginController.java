package com.wxfoods.controller.admin;

import com.wxfoods.entity.adminModel;
import com.wxfoods.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/admin")
@CrossOrigin //解决vue和SSM端口不一致的跨域问题
public class AdminLoginController {

    @Autowired
    private adminService adminService;

    /***
     *
     * @param adminmodel
     * @return
     */
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestBody adminModel adminmodel) {
        System.out.println(adminmodel.getUsername());
        return adminService.checkAdmin(adminmodel);
    }


    @RequestMapping(value = "/alertpwd.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> alertPwd(@RequestParam(value="username") String username,@RequestParam(value="password") String password) {
        adminModel adminmodel = new adminModel();
        adminmodel.setUsername(username);
        adminmodel.setPassword(password);
        return  adminService.alertPwd(adminmodel);
    }
}
