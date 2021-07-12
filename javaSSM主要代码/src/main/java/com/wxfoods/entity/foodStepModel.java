package com.wxfoods.entity;

public class foodStepModel {
    private String food_Images;
    private String food_Practice;
    public void setFoodimage(String foodimage){
        this.food_Images = foodimage;
    }

    public void setFoodpractice(String foodpractice){
        this.food_Practice = foodpractice;
    }

    public String getFoodimage(){
        return food_Images;
    }

    public String getFoodpractice(){
        return food_Practice;
    }
}
