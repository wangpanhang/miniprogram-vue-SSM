package com.wxfoods.entity;

public class foodInfoModel {
    private int food_Id;
    private String food_Name;
    private String food_Image;
    private String food_MainIngred;
    private String food_Ingred;
    private String food_Dosing;
    private String food_Author;
    private Integer food_ClsId;
    private String food_ClsName;
    private Integer food_Count;

    public void setFoodid(int foodid){
        this.food_Id = foodid;
    }

    public void setFoodname(String foodname){
        this.food_Name = foodname;
    }

    public void setFoodimage(String foodimage){
        this.food_Image = foodimage;
    }

    public void setFoodmainingred(String foodmainingred){
        this.food_MainIngred = foodmainingred;
    }

    public void setFoodingred(String foodingred){ this.food_Ingred = foodingred; }

    public void setFooddosing(String fooddosing){ this.food_Dosing = fooddosing; }

    public void setFoodauthor(String foodauthor){
        this.food_Author = foodauthor;
    }

    public void setFoodcount(Integer foodcount){
        this.food_Count = foodcount;
    }

    public int getFoodid(){
        return food_Id;
    }

    public String getFoodname(){
        return food_Name;
    }

    public String getFoodimage(){
        return food_Image;
    }

    public String getFoodmainingred(){
        return food_MainIngred;
    }

    public String getFoodingred(){ return food_Ingred; }

    public String getFooddosing(){ return food_Dosing; }

    public String getFoodauthor(){
        return food_Author;
    }

    public Integer getFoodcount(){
        return food_Count;
    }

    public Integer getFood_ClsId() {
        return food_ClsId;
    }

    public void setFood_ClsId(Integer food_ClsId) {
        this.food_ClsId = food_ClsId;
    }

    public String getFood_ClsName() {
        return food_ClsName;
    }

    public void setFood_ClsName(String food_ClsName) {
        this.food_ClsName = food_ClsName;
    }
}
