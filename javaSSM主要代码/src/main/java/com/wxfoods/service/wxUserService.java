package com.wxfoods.service;

import com.wxfoods.entity.wxOpenId;
import com.wxfoods.entity.wxUser;

public interface wxUserService {
    public wxOpenId getOpenId(String code);

    public wxUser checkWxUser(String name, String image, String code);

    public wxUser getWxUserInfo(String openId);

}
