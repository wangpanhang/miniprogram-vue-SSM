package com.wxfoods.service;

public interface wxUserCollectService {
    public int findWxUserCollect(int user_Id,int food_Id);

    public String changeWxUserCollect(int user_Id,int food_Id,int collect);
}
