package com.wxfoods.mapper;

import com.wxfoods.entity.userDynamicInfoModel;
import com.wxfoods.entity.userDynamicModel;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface dynamicDao {
    public int insertUserDynamic(userDynamicModel userDynamicModel);
    public List<userDynamicInfoModel> getDynamicDao();
    public List<userDynamicInfoModel> getSelfDynamicDao(@Param("user_Id") Integer user_Id);
}
