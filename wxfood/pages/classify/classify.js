const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    classid:1
  },

  navigateToFood:function(e){
    var foodinfo = e.currentTarget.dataset.array
    wx.setStorageSync('array', foodinfo)
    wx.navigateTo({
      url: '../foodInfo/foodInfo'
    })
  },

  getClsId:function(e){
    var that = this
    var id = e.currentTarget.dataset.id
    that.setData({
      classid:id
    }),
    app.func.getfoodInfo(id,this.callbackInfo);
  },

  /** 
   * 回调函数
  */
  callbackInfo:function(res){
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
    var i = that.data.classid
    wx.request({
      url: app.func.url+'foodCls/classify.do',
      method:'GET',
      dataType:"json",
      success:(res)=>{
        that.setData({
          clsArrays:res.data
        })
        console.log(res.data)
      }
    }),
    app.func.getfoodInfo(i,this.callbackInfo);
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

