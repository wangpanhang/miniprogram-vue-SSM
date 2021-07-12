package com.wxfoods.controller.admin;

import com.wxfoods.entity.foodClsModel;
import com.wxfoods.entity.foodModel;
import com.wxfoods.service.foodClsService;
import com.wxfoods.service.foodManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/foodmanage")
@CrossOrigin //解决vue和SSM端口不一致的跨域问题
public class FoodManageController {

    @Autowired
    private foodClsService foodClsService;

    @Autowired
    private foodManageService foodManageService;

    @RequestMapping("/getfoodcls.do")
    @ResponseBody
    public List<foodClsModel> getFoodCls(){
        return foodClsService.getFoodCls();
    }

    @RequestMapping(value = "/insertfood.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertFood(@RequestParam(value="file") MultipartFile file, @RequestParam(value="username") String username, @RequestParam(value="mainIngred") String mainIngred, @RequestParam(value="ingred") String ingred,@RequestParam(value="dosing") String dosing,@RequestParam(value="author") String author, @RequestParam(value="clsid") Integer clsid) throws Exception{
        return foodManageService.uploadfile(username, mainIngred, ingred, dosing, author, clsid, file);
    }

    @RequestMapping(value = "/getfoodlist.do")
    @ResponseBody
    public List<foodModel> getFoodList(@RequestParam(value="currpage") int currpage, @RequestParam(value="pagesize") int pagesize){
        return foodManageService.getFoodList(currpage, pagesize);
    }

    @RequestMapping(value = "/getfoodcount.do")
    @ResponseBody
    public int getFoodCount(){
        return foodManageService.getFoodCount();
    }

    @RequestMapping(value="/getsearchfood.do")
    @ResponseBody
    public List<foodModel> getFoodList(@RequestParam(value="query") String query){
        return foodManageService.getSearchFood(query);
    }

    @RequestMapping(value="/getfoodinfo.do")
    @ResponseBody
    public foodModel getFoodInfo(@RequestParam(value="food_Id") int food_Id){
        return foodManageService.getFoodInfo(food_Id);
    }

    @RequestMapping(value="/alertfoodinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> alertFoodInfo(@RequestParam(value="food_Id") int food_Id,@RequestParam(value="food_Name") String food_Name,@RequestParam(value="food_MainIngred") String food_MainIngred,@RequestParam(value="food_Ingred") String food_Ingred,@RequestParam(value="food_Dosing") String food_Dosing,@RequestParam(value="food_Author") String food_Author,@RequestParam(value="cls_Id") int cls_Id){
        foodModel foodModel = new foodModel();
        foodModel.setFood_Id(food_Id);
        foodModel.setFood_Name(food_Name);
        foodModel.setFood_MainIngred(food_MainIngred);
        foodModel.setFood_Ingred(food_Ingred);
        foodModel.setFood_Dosing(food_Dosing);
        foodModel.setFood_Author(food_Author);
        foodModel.setCls_Id(cls_Id);
        return foodManageService.alertFoodInfo(foodModel);
    }

    @RequestMapping(value="/delfoodinfo.do")
    @ResponseBody
    public Map<String,Object> delFoodInfo(@RequestParam(value="food_Id") int food_Id){
        return foodManageService.delFoodInfo(food_Id);
    }
}
