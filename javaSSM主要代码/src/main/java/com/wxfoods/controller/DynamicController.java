package com.wxfoods.controller;

import com.wxfoods.entity.userDynamicInfoModel;
import com.wxfoods.service.dynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="dynamic")
public class DynamicController {
    @Autowired
    private dynamicService dynamicService;

    @RequestMapping("getfile.do")
    @ResponseBody
    public String upload(@RequestParam(value="user_Id") Integer user_Id,@RequestParam(value="value") String value, @RequestParam(value="file",required = false) MultipartFile file) throws IOException{
        return dynamicService.uploadfile(user_Id,value,file);
    }

    @RequestMapping("getdynamic.do")
    @ResponseBody
    public List<userDynamicInfoModel> getDynamic(){
        return dynamicService.getUserDynamicInfoList();
    }

    @RequestMapping("getuserdynamic.do")
    @ResponseBody
    public List<userDynamicInfoModel> getuserDynamic(@RequestParam(value="user_Id",required=false) Integer user_Id){
        return dynamicService.getSelfDynamicInfoList(user_Id);
    }
}
