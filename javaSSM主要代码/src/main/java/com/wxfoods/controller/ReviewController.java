package com.wxfoods.controller;

import com.wxfoods.entity.reviewModel;
import com.wxfoods.service.reviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private reviewService reviewService;

    @RequestMapping("insertReview.do")
    @ResponseBody
    public int insertReview(@RequestParam(value="dynamic_Id") Integer dynamic_Id,@RequestParam(value="user_Id") Integer user_Id,@RequestParam(value="userNickName") String user_NickName,@RequestParam(value="content") String content){
        return reviewService.insertReviewUser(dynamic_Id, user_Id, user_NickName, content);
    }

    @RequestMapping("getReview.do")
    @ResponseBody
    public List<reviewModel> getReview(@RequestParam(value="dynamic_Id") Integer dynamic_Id){
        return reviewService.getReviewUser(dynamic_Id);
    }

}
