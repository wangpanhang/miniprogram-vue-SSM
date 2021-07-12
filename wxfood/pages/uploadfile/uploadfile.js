// pages/uploadfile/uploadfile.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    value:''
  },

  getvalue:function(res){
    var that = this
    that.setData({
      value:res.detail.value
    })
  },

  /**
   * 跳转到tarbar页面
   */
  navigateBack:function(value,value1){
    wx.showToast({
      title: value,
      icon: value1
    })
    wx.navigateBack({ changed: true })
  },
  /**
   * 上传图片
   */
  uploadImg:function(){
    var that = this
    var value = that.data.value
    var imagePath = that.data.imagePath
    var user_Id = wx.getStorageSync('userId')
    console.log(user_Id),
    wx.uploadFile({
      filePath: imagePath,
      name: 'file',
      url: app.func.url+'dynamic/getfile.do',
      'Content-Type': 'multipart/form-data',
      formData:{
        'user_Id':user_Id,
        'value':value
      },
      success:(res)=>{
        console.log("成功了")
        if(res.data=="success"){
          that.navigateBack("发表成功","success")
        }
        else if(res.data=="typeerror"){
          that.navigateBack("图片类型错误","none")
        }
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
    var imagePath = wx.getStorageSync('photo')
    var that = this
    that.setData({
      imagePath:imagePath
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