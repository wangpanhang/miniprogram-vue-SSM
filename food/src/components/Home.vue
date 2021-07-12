<template>
  <el-container class="home_container">
    <!-- 头部区域 -->
    <el-header>
      <div class="font_color">美食菜谱小程序后台管理系统</div>
      <el-button
        type="info"
        @click="dialogVisible = true"
        style="background-color:#DCDFE6;color:#303133"
      >退出</el-button>
      <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
        <span>您确定要退出?</span>
        <span slot="footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="logout()">确 定</el-button>
        </span>
      </el-dialog>
    </el-header>
    <!--主体区域-->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse ? '64px':'200px'">
        <!-- 折叠按钮 -->
        <div class="trigger_button" @click="collapse">
          <div :class="isCollapse ? 'el-icon-s-unfold':'el-icon-s-fold'"></div>
        </div>
        <!-- 侧边菜单栏区域 -->
        <el-menu
          :unique-opened="true"
          :collapse="isCollapse"
          :collapse-transition="false"
          router
          :default-active="activepath"
          background-color="#2b2a2a"
          text-color="#fff"
          active-text-color="#409EFF"
        >
          <!-- 一级菜单区域 -->
          <el-submenu :index="item.m_Id + ''" v-for="item in manageList" :key="item.m_Id">
            <template slot="title">
              <i :class="iconfont[item.m_Id]"></i>
              <span style="color:#ffffff">{{item.m_Name}}</span>
            </template>
            <!-- 二级菜单区域 -->
            <el-menu-item
              :index="'/' + manageItem.paths"
              v-for="manageItem in item.twoManageModelList"
              :key="manageItem.id"
              @click="activeClick('/' + manageItem.paths)"
            >
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>{{manageItem.m_Name}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!-- 内容显示区域 -->
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
  data() {
    return {
      manageList: [],
      iconfont: {
        "1": "el-icon-food",
        "2": "el-icon-s-marketing",
        "3": "el-icon-lock"
      },
      //是否折叠
      isCollapse: false,
      activepath: "",
      dialogVisible: false
    };
  },
  created() {
    this.getManage();
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    //获取管理信息
    async getManage() {
      const { data: res } = await this.$http.get("manage/getmanage.do");
      console.log(res);
      if (res.meta.status != 200) return this.$message.error(res.meta.msg);
      this.manageList = res.data;
    },
    //点击按钮，切换菜单的折叠与展开
    collapse() {
      this.isCollapse = !this.isCollapse;
    },
    activeClick(activepath) {
      window.sessionStorage.setItem("activepath", activepath);
      this.activepath = window.sessionStorage.getItem("activepath");
    }
  }
};
</script>
<style lang="less" scoped>
.home_container {
  height: 100%;
}

.el-header {
  background-color: #2b2a2a;
  // height: 200px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 25px;
}
.el-submenu:hover {
  background-color: #ffffff !important;
}
.font_color {
  color: #ffffff;
}
.el-aside {
  background-color: rgb(43, 42, 42);
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: rgb(243, 243, 243);
}

.trigger_button {
  background-color: rgb(243, 243, 243);
  height: 30px;
  display: flex;
  justify-content: center;
  cursor: pointer;
  > div {
    width: 30px;
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
}

body > .el-container {
  margin-bottom: 40px;
}
</style>