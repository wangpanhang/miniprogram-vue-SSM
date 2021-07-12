package com.wxfoods.mapper;

import com.wxfoods.entity.wxUser;

public interface wxUserDao {
    public int findWxUser(String openId);
    public int createWxUser(wxUser wxUser);
    public wxUser getWxUser(String openId);
    public int refreshWxUser(wxUser wxUser);
}
