package com.wxfoods.service.impl;

import com.google.gson.Gson;
import com.wxfoods.entity.wxInfo;
import com.wxfoods.entity.wxOpenId;
import com.wxfoods.entity.wxUser;
import com.wxfoods.mapper.wxUserDao;
import com.wxfoods.service.wxUserService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class wxUserServiceImpl implements wxUserService {
    private wxInfo wxInfo = new wxInfo();

    @Resource
    private wxUserDao wxUserDao;

    @Override
    public wxOpenId getOpenId(String code) {
        wxOpenId wxOpenId = null;
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+wxInfo.getAppId()+"&secret="+wxInfo.getSecret()+"&js_code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,null,String.class);
        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String SessionData = responseEntity.getBody();
            Gson gson = new Gson();
            wxOpenId = gson.fromJson(SessionData,wxOpenId.class);
            return wxOpenId;
        }
        return wxOpenId;
    }

    @Override
    public wxUser checkWxUser(String name, String image, String code) {
        wxOpenId wxOpenId = getOpenId(code);
        wxUser wxUser = new wxUser();
        int total = wxUserDao.findWxUser(wxOpenId.getOpenId());
        if(total==0){
            wxUser.setUserOpenId(wxOpenId.getOpenId());
            wxUser.setUserNickName(name);
            wxUser.setUserAvatarUrl(image);
            wxUserDao.createWxUser(wxUser);
        }
        else{
            wxUser.setUserId(total);
            wxUser.setUserNickName(name);
            wxUser.setUserAvatarUrl(image);
            if(wxUserDao.refreshWxUser(wxUser)!=0){
                return wxUser;
            }
        }
        return getWxUserInfo(wxOpenId.getOpenId());
    }

    @Override
    public wxUser getWxUserInfo(String openId) {
        return wxUserDao.getWxUser(openId);
    }

}
