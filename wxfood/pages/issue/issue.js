// pages/issue/issue.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  chooseImage: function () {
    wx.chooseImage({
      count: '1',
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: (res) => {
        var tempFilePaths = res.tempFilePaths
        wx.setStorageSync('photo', tempFilePaths[0])
        wx.navigateTo({
          url: '/pages/uploadfile/uploadfile',
        })
      },
      complete: (res) => {},
    })
  },

  /**
   * 获取用户评论
   */
  getReview: function (e) {
    var that = this
    that.setData({
      content: e.detail.value
    })
  },

  /**
   * 发表评论
   */
  submitReview: function (e) {
    var that = this
    var content = that.data.content
    var dynamic_Id = e.currentTarget.dataset.id
    var user_Id = wx.getStorageSync('userId')
    var userNickName = wx.getStorageSync('userNickName')
    var index = e.currentTarget.dataset.index
    wx.request({
      url: app.func.url + 'review/insertReview.do',
      data:{
        dynamic_Id:dynamic_Id,
        user_Id:user_Id,
        userNickName:userNickName,
        content:content
      },
      method: 'GET',
      success(res) {
        wx.showToast({
          title: '发表成功',
          icon:'success',
          duration:500
        }),
        that.setData({
          content:''
        })
        that.getUserReview(index,dynamic_Id)
      }
    })
  },

  /**
   * 刷新用户评论
   */
  getUserReview:function(index,dynamic_Id){
    var that = this
    var dynamic_Id = dynamic_Id
    var index = index
    let list = that.data.dynamicArrays
    wx.request({
      url: app.func.url + 'review/getReview.do',
      data:{
        dynamic_Id:dynamic_Id,
      },
      method: 'GET',
      success(res) {
        list[index].reviewModelList = res.data
        that.setData({
          dynamicArrays:list
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
    wx.checkSession({
      success: (res) => {
        that.setData({
          blob: true
        })
        wx.request({
          url: app.func.url + 'dynamic/getdynamic.do',
          method: 'GET',
          success(res) {
            // console.log(res)
            that.setData({
              dynamicArrays: res.data
            })
            console.log(res.data)
          }
        })
      },
      fail: (res) => {
        that.setData({
          blob: false
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