package com.wxfoods.entity;


public class foodClsModel {
    private int cls_Id;
    private String cls_Name;
    public foodClsModel(){}
    public foodClsModel(int clsid,String clsname){
        this.cls_Id = clsid;
        this.cls_Name = clsname;
    }
    public void setClsid(int clsid1){
        this.cls_Id = clsid1;
    }
    public void setClsname(String clsname1){
        this.cls_Name = clsname1;
    }
    public int getClsid(){
        return cls_Id;
    }
    public String getClsname(){
        return cls_Name;
    }
}
