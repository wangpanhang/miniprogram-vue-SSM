// pages/my/my.js
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    blob:true,
    blob1:false
  },

  

  bindcut:function(){
    var that = this
    var blob = that.data.blob
    var blob1 = that.data.blob1
    that.setData({
      blob:!blob,
      blob1:!blob1
    })
  },

  bindcut1:function(){
    var that = this
    var blob = that.data.blob
    var blob1 = that.data.blob1
    var userId = wx.getStorageSync('userId')
    that.setData({
      blob:!blob,
      blob1:!blob1
    })
    that.getUserDynamic(userId)
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
   * 获取收藏的信息
   */
  getUserCollect:function(userId){
    var that = this
    wx.request({
      url: app.func.url+'wxLogin/wxCollectInfo.do',
      data:{
        user_Id:userId
      },
      header:{'content-type':'application/json'},
      success(res){
        that.setData({
          foodArrays:res.data
        })
      }
    })
  },

  /**
   * 获取动态的信息
   */
  getUserDynamic:function(userId){
    var that = this
    wx.request({
      url: app.func.url+'dynamic/getuserdynamic.do',
      data:{
        user_Id:userId
      },
      header:{'content-type':'application/json'},
      success(res){
        that.setData({
          dynamicArrays:res.data
        })
      }
    })
  },

  /**
   * 用户点击刷新图片
   */
  refreshImg:function(){

  },

  /**
   * 用户登录以及获取用户信息
   */
  getUserProfile:function(){
    var that = this
    let code = ''
    wx.login({
      success:(e)=>{
        code = e.code
      }
    });
    wx.getUserProfile({
      desc: '需要你的信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        //这里需要将获取的 res.userInfo 存起来，你可以存在数据库，也可以存在local storage里
        //wx.request...请求接口
        wx.request({
          url: app.func.url+'wxLogin/wxCode.do',
          header:{
            'content-type': 'application/json'
          },
          data:{
            name:res.userInfo.nickName,
            image:res.userInfo.avatarUrl,
            code:code
          },
          method:'GET',
          success(rs){
            that.setData({
              login:false,
              nickName:res.userInfo.nickName,
              imagePath:res.userInfo.avatarUrl
            }),
            that.getUserCollect(rs.data.userId)
            wx.setStorageSync('userId', rs.data.userId)
            wx.setStorageSync('userNickName', res.userInfo.nickName)
            wx.setStorageSync('userAvatarUrl', res.userInfo.avatarUrl)
          }
        })
      }
    });
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
      success:(res)=>{
        var nickName = wx.getStorageSync('userNickName')
        var imagePath = wx.getStorageSync('userAvatarUrl')
        var userId = wx.getStorageSync('userId')
        that.setData({
          login:false,
          nickName:nickName,
          imagePath:imagePath
        })
        that.getUserCollect(userId)
      },
      fail:(res)=>{
        that.setData({
          login:true
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