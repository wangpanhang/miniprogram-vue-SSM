package com.wxfoods.mapper;

import com.wxfoods.entity.wxUserCollect;

public interface wxUserCollectDao {
    public int findWxUserCollect(int user_Id,int food_Id);
    public int insertWxUserCollect(wxUserCollect wxUserCollect);
    public int deleteWxUserCollect(wxUserCollect wxUserCollect);
}
