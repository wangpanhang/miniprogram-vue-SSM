// pages/searchfood/searchfood.js
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  getInputInfo:function(e){
    this.setData({
      food:e.detail.value
    })
  },

  searchfood:function(e){
    var food = this.data.food
    if(food){
      this.getfoodinfo(food)
    }
  },

  getfoodinfo:function(food){
    var that = this
    wx.request({
      url: app.func.url+'food/searchInfo.do',
      method:'GET',
      data:{
        food:food
      },
      success(res){
        that.setData({
          food:food,
          foodArrays:res.data
        })
      }
    })
  },

  navigateToFood:function(res){
    var foodinfo = res.currentTarget.dataset.array
    wx.setStorageSync('array', foodinfo)
    wx.navigateTo({
      url: '../foodInfo/foodInfo'
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var food = options.food
    if(food){
      this.getfoodinfo(food)
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})