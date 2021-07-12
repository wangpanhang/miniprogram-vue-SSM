// pages/foodInfo/foodInfo.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    foodStep:null,
    foodimage:null
  },

  collectfood:function(){
    var that = this
    var collect = that.data.collect
    var foodArray =  wx.getStorageSync('array')
    var id = foodArray.foodid
    wx.checkSession({
      success:(res)=>{
        var userId = wx.getStorageSync('userId')
        wx.request({
          url: app.func.url+'wxUserCollect/addcollect.do',
          data:{
            user_Id:userId,
            food_Id:id,
            collect:collect
          },
          header:{'content-type':'application/json'},
          success(res){
            if(res.data=="delete"){
              that.setData({
                collect:0,
              })
            }
            else if(res.data=="insert"){
              that.setData({
                collect:1,
              })
            }
          }
        })
      },
      fail:(res)=>{
        wx.showToast({
          title: '请您先登录！',
          icon:"none"
        })
      },
      complete: (res) => {},
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var foodArray =  wx.getStorageSync('array')
    var id = foodArray.foodid
    var foodname = foodArray.foodname
    var foodimage = foodArray.foodimage
    that.setData({
      foodimage:foodimage,
      foodArray : foodArray
    })
    wx.setNavigationBarTitle({
      title: foodname,
    })
    wx.request({
      url: app.func.url+'food/foodStep.do',
      method:'GET',
      data:{
        id:id
      },
      dataType:'json',
      success(res){
        that.setData({foodStep:res.data})
      }
    })
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
    var that = this
    var foodArray =  wx.getStorageSync('array')
    var id = foodArray.foodid
    wx.checkSession({
      success:(res)=>{
        var userId = wx.getStorageSync('userId')
        wx.request({
          url: app.func.url+'wxUserCollect/collect.do',
          data:{
            user_Id:userId,
            food_Id:id
          },
          header:{'content-type':'application/json'},
          success(res){
            if(res.data){
              that.setData({
                collect:1,
              })
            }
            else{
              that.setData({
                collect:0,
              })
            }
          }
        })
      },
      fail:(res)=>{
        that.setData({
          collect:0,
          collects:'收藏'
        })
      },
      complete: (res) => {},
    })
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