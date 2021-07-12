package com.wxfoods.mapper;

import com.wxfoods.entity.userDynamicModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface userDynamicDao {
    public List<userDynamicModel> getUserDynamic(Map<String,Object> data);
    public int getDynamicCount();
    public int delUserDynamic(@Param("dynamicId") int dynamic_Id);
    public int delUserReview(@Param("dynamicId") int dynamic_Id);
}
