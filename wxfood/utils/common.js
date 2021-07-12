function getfoodInfo(id,callback){
  wx.request({
    url:'http://localhost:8080/wxfood/food/foodInfo.do',
    data:{
      id:id
    },
    method:'GET',
    dataType:'json',
    success(res){
      // console.log(res)
      var foodInfo = res.data
      return typeof callback=='function' && callback(foodInfo)
    }
  })
}

module.exports={
  getfoodInfo:getfoodInfo
}