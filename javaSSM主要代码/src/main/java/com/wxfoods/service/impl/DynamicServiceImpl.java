package com.wxfoods.service.impl;

import com.wxfoods.entity.userDynamicInfoModel;
import com.wxfoods.entity.userDynamicModel;
import com.wxfoods.mapper.dynamicDao;
import com.wxfoods.service.dynamicService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class DynamicServiceImpl implements dynamicService {
    @Resource
    private dynamicDao dynamicDao;

    private String url = "http://localhost:8080/wxfood/";
    private String filepath = "E://wxfood/target/wxfood/images/dynamic/";
    @Override
    public String uploadfile(Integer user_Id, String value, MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            String filename = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = filename.indexOf(".")!=-1?filename.substring(filename.lastIndexOf(".")+1,filename.length()):null;
            if(type!=null){
                if("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())||"JPEG".equals(type.toUpperCase())){
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + "."+type;
                    path = filepath+"/ss"+user_Id+"ss/"+trueFileName;
                    File dest = new File(path);
                    if(!dest.getParentFile().exists()){
                        dest.getParentFile().mkdir();
                    }
                    file.transferTo(dest);
                    this.insertUserDynamic(user_Id,trueFileName,value);
                    return "success";
                }
                return "typeerror";
            }
            return "notfilerror";
        }
        return "notfileerror";
    }

    /**
     *
     * @param user_Id
     * @param trueFileName
     * @param value
     * @return
     */
    @Override
    public int insertUserDynamic(Integer user_Id, String trueFileName, String value) {
        userDynamicModel userDynamicModel = new userDynamicModel();
        String imgPath = "images/dynamic/ss"+user_Id+"ss/"+trueFileName;
        Date nowTime = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat SDF = new SimpleDateFormat(pattern);
        String timePattern = SDF.format(nowTime);
        userDynamicModel.setUser_Id(user_Id);
        userDynamicModel.setImagePath(imgPath);
        userDynamicModel.setContent(value);
        userDynamicModel.setUpLoadDate(timePattern);
        return dynamicDao.insertUserDynamic(userDynamicModel);
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public List<userDynamicInfoModel> getUserDynamicInfoList() {
        List<userDynamicInfoModel> userDynamicInfoModels = dynamicDao.getDynamicDao();
        Iterator<userDynamicInfoModel> iter = userDynamicInfoModels.iterator();
        while(iter.hasNext()){
            userDynamicInfoModel userDynamicInfoModel = iter.next();
            String image_path = userDynamicInfoModel.getImagePath();
            userDynamicInfoModel.setImagePath(url+image_path);
        }
        return userDynamicInfoModels;
    }

    @Override
    public List<userDynamicInfoModel> getSelfDynamicInfoList(Integer userId) {
        List<userDynamicInfoModel> userDynamicInfoModels = dynamicDao.getSelfDynamicDao(userId);
        Iterator<userDynamicInfoModel> iter = userDynamicInfoModels.iterator();
        while(iter.hasNext()){
            userDynamicInfoModel userDynamicInfoModel = iter.next();
            String image_path = userDynamicInfoModel.getImagePath();
            userDynamicInfoModel.setImagePath(url+image_path);
        }
        return userDynamicInfoModels;
    }
}
