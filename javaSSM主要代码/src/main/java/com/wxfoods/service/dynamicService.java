package com.wxfoods.service;

import com.wxfoods.entity.userDynamicInfoModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface dynamicService {
    public String uploadfile(Integer user_Id, String value, MultipartFile file) throws IOException;
    public int insertUserDynamic(Integer user_Id,String trueFileName,String value);
    public List<userDynamicInfoModel> getUserDynamicInfoList();
    public List<userDynamicInfoModel> getSelfDynamicInfoList(Integer userId);
}
