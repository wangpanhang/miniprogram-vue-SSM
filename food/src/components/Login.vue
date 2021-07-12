<template>
  <div class="login_container">
    <div class="login_box">
      <div class="login_img">
        <img src="../assets/logo.png" />
      </div>
      <el-form
        :model="loginform"
        :rules="loginrules"
        label-width="0px"
        class="login_form"
        ref="loginformrefs"
      >
        <!--用户名-->
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" v-model="loginform.username" placeholder="请输入你的用户名"></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="password">
          <el-input
            prefix-icon="el-icon-lock"
            show-password
            v-model="loginform.password"
            placeholder="请输入你的密码"
          ></el-input>
        </el-form-item>
        <!--按钮-->
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //用户数据绑定
      loginform: {
        username: "",
        password: ""
      },
      //表单验证
      loginrules: {
        username: [
          {
            required: true,
            message: "请输入登录名称",
            trigger: "blur"
          },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur"
          },
          {
            min: 6,
            max: 15,
            message: "长度在 6 到 15 个字符",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    //重置用户数据
    reset() {
      // console.log(this)
      this.$refs.loginformrefs.resetFields();
    },
    //用户登录
    login() {
      //登录前需要验证表单
      var that = this;
      that.$refs.loginformrefs.validate(async valid => {
        console.log(valid);
        console.log(that.loginform.username);
        if (!valid) return;
        // const { data : res } = await that.$http.post("admin/test.do",that.loginform);
        const { data: res } = await that.$http.post(
          "admin/login.do",
          that.loginform
        );
        if (res.meta.status != 200) {
          return that.$message.error(res.meta.msg);
        }
        that.$message.success(res.meta.msg);
        window.sessionStorage.setItem("token", res.data.token);
        window.sessionStorage.setItem("user", res.data.user);
        that.$router.push("/home");
      });
    }
  }
};
</script>

<style lang='less' scoped>
.login_container {
  height: 100%;
  background-color: #2b2a2a;
}

.login_box {
  width: 32%;
  height: 50%;
  border-radius: 5px;
  background-color: #fff;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.login_img {
  width: 160px;
  height: 160px;
  padding: 10px;
  border-radius: 50%;
  border: 1px solid #eee;
  box-shadow: 0 0 10px #eee;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background-color: #eee;
  }
}

.login_form {
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
  position: absolute;
  bottom: 20px;
}

.btns {
  display: flex;
  justify-content: center;
}
</style>
