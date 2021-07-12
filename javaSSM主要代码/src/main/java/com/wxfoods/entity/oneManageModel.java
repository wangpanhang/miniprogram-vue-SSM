package com.wxfoods.entity;

import java.util.List;

public class oneManageModel {
    private int m_Id;
    private String m_Name;
    private List<twoManageModel> twoManageModelList;

    public int getM_Id() {
        return m_Id;
    }

    public void setM_Id(int m_Id) {
        this.m_Id = m_Id;
    }

    public String getM_Name() {
        return m_Name;
    }

    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    public List<twoManageModel> getTwoManageModelList() {
        return twoManageModelList;
    }

    public void setTwoManageModelList(List<twoManageModel> twoManageModelList) {
        this.twoManageModelList = twoManageModelList;
    }
}
