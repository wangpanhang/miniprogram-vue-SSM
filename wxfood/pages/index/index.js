const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    classid:1
  },

  /**
   * 获取输入框的值
   */
  getInputInfo:function(e){
    // console.log(e)
    this.setData({
      food:e.detail.value
    })
  },

  /**
   * 获取搜索的内容
   */
  searchfood:function(){
    var that = this
    var food = this.data.food
    wx.navigateTo({
      url: '../searchfood/searchfood?food='+food,
    })
    that.setData({
      food:""
    })
  },
  
  /**
   * 跳转到做菜步骤页面
   */
  navigateToFood:function(res){
    var foodinfo = res.currentTarget.dataset.array
    wx.setStorageSync('array', foodinfo)
    wx.navigateTo({
      url: '../foodInfo/foodInfo'
    })
  },

  /**
   * 回调函数
   */
  callbackInfo(res){
    var that = this
    that.setData({
      foodArrays:res
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var id = that.data.classid
    app.func.getfoodInfo(id,this.callbackInfo);
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