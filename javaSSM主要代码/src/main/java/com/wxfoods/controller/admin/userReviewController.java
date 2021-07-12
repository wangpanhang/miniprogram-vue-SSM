package com.wxfoods.controller.admin;

import com.wxfoods.entity.userReviewModel;
import com.wxfoods.service.userReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userreview")
@CrossOrigin //解决vue和SSM端口不一致的跨域问题
public class userReviewController {
    @Autowired
    private userReviewService userReviewService;

    @RequestMapping("getuserreview.do")
    @ResponseBody
    public List<userReviewModel> getUserReview(@RequestParam(value="currpage") int currpage, @RequestParam(value="pagesize") int pagesize){
        return userReviewService.getUserReview(currpage, pagesize);
    }

    @RequestMapping(value = "/getreviewcount.do")
    @ResponseBody
    public int getFoodCount(){
        return userReviewService.getReviewCount();
    }

    @RequestMapping(value = "/deluserreview.do")
    @ResponseBody
    public Map<String,Object> delUserReview(@RequestParam(value="review_Id") int review_Id){
        return userReviewService.delUserReview(review_Id);
    }
}
