<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>信息管理</el-breadcrumb-item>
      <el-breadcrumb-item>修改密码</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="pwd_container">
      <el-card class="pwd_box">
        <span>修改密码</span>
        <el-form
          :model="pwdform"
          ref="pwdformrefs"
          label-width="80px"
          :rules="rules"
          class="pwd_form"
        >
          <el-form-item label="新密码:" prop="newpwd">
            <el-input show-password v-model="pwdform.newpwd" placeholder="请输入你的新密码"></el-input>
          </el-form-item>
          <el-form-item label="确认密码:" style="margin-top:10%;" prop="newpwd1">
            <el-input show-password v-model="pwdform.newpwd1" placeholder="请确认你的密码"></el-input>
          </el-form-item>
          <el-form-item class="btns">
            <el-button type="primary" @click="submitpwd">登录</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.pwdform.newpwd !== "") {
          this.$refs.pwdformrefs.validateField("newpwd1");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.pwdform.newpwd) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      pwdform: {
        newpwd: "",
        newpwd1: ""
      },
      rules: {
        newpwd: [{ validator: validatePass, trigger: "blur" }],
        newpwd1: [{ validator: validatePass2, trigger: "blur" }]
      }
    };
  },
  methods: {
    async submitpwd() {
      let formData = new FormData();
      formData.append("username", window.sessionStorage.getItem("user"));
      formData.append("password", this.pwdform.newpwd);
      const { data: res } = await this.$http.post(
        "admin/alertpwd.do",
        formData,
        {
          headers: { "Content-Type": "application/x-www-form-urlencoded" }
        }
      );
      if (res.meta.status != 200) {
        return this.$message.error(res.meta.msg);
      }
      this.$message.success(res.meta.msg);
      window.sessionStorage.clear();
      this.$router.push("/login");
    }
  }
};
</script>
<style lang="less" scoped>
.pwd_container {
  width: 100%;
  //   background-color: #ffffff;
  display: flex;
  justify-content: center;
  align-items: center;
}
.pwd_box {
  width: 40%;
  height: 500px;
  position: relative;
  span {
    font-size: 30px;
    position: absolute;
    color: red;
    left: 50%;
    top: 10%;
    transform: translateX(-50%);
  }
}
.pwd_form {
  width: 80%;
  padding: 0 20px;
  //   box-sizing: border-box;
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translateX(-50%);
  //   background-color: red;
}
.btns {
  margin-top: 20%;
  display: flex;
  //   background-color: green;
  justify-content: center;
}
</style>