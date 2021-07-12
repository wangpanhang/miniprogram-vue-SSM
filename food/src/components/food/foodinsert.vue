<template>
  <div>
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>菜谱管理</el-breadcrumb-item>
        <el-breadcrumb-item>菜谱信息添加</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-card>
      <!-- 菜谱信息添加区域 -->
      <el-form
        :model="foodform"
        label-width="80px"
        class="food_form"
        style="width:50%"
        ref="uploadFormrefs"
      >
        <!-- 菜谱名称 -->
        <el-form-item label="菜谱名称:" prop="username">
          <el-input prefix-icon="el-icon-user" v-model="foodform.username" placeholder="请输入你的用户名"></el-input>
        </el-form-item>
        <!-- 选择图片区域 -->
        <el-form-item label="菜谱图片:">
          <el-upload
            class="upload-demo"
            action="/images/dynamic"
            :on-change="checkfile"
            :auto-upload="false"
            :limit="1"
            :file-list="fileList"
            list-type="picture"
            :http-request="httprequest"
            ref="upload"
          >
            <el-button size="small" type="primary">选取文件</el-button>
            <!-- <el-button size="small" type="primary" @click="submitUpload">提交</el-button> -->
            <div slot="tip" class="el-upload__tip">只能上传jpg/png/jpeg文件，且不超过500kb</div>
            <img />
          </el-upload>
        </el-form-item>
        <el-form-item label="主料:" prop="mainIngred">
          <el-input prefix-icon="el-icon-user" v-model="foodform.mainIngred" placeholder="请输入菜品主料"></el-input>
        </el-form-item>
        <el-form-item label="辅料:" prop="ingred">
          <el-input prefix-icon="el-icon-user" v-model="foodform.ingred" placeholder="请输入菜品辅料"></el-input>
        </el-form-item>
        <el-form-item label="配料:" prop="dosing">
          <el-input prefix-icon="el-icon-user" v-model="foodform.dosing" placeholder="请输入菜品配料"></el-input>
        </el-form-item>
        <el-form-item label="厨师:" prop="author">
          <el-input prefix-icon="el-icon-user" v-model="foodform.author" placeholder="请输入厨师"></el-input>
        </el-form-item>
        <el-form-item label="类别:" prop="clsid">
          <el-select v-model="foodform.clsid" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.clsid"
              :label="item.clsname"
              :value="item.clsid"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="margin-top:15px;">
          <el-button type="primary" @click="submitUpload">上传</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
export default {
  created() {
    this.getFoodCls();
  },
  data() {
    return {
      count: 1,
      foodform: {
        username: "",
        mainIngred: "",
        ingred: "",
        dosing: "",
        author: "",
        clsid: ""
      },
      foodrules: {
        username: [
          { required: true, message: "菜名不能为空", trigger: "blur" }
        ],
        mainIngred: [
          { required: true, message: "主料不能为空", trigger: "blur" }
        ],
        ingred: [{ required: true, message: "辅料不能为空", trigger: "blur" }],
        dosing: [{ required: true, message: "配料不能为空", trigger: "blur" }],
        author: [{ required: true, message: "作者不能为空", trigger: "blur" }],
        clsid: [{ required: true, message: "分类不能为空", trigger: "blur" }]
      },
      options: [],
      fileList: [],
      value: "",
      formData: ""
    };
  },
  methods: {
    checkfile(file, fileList) {
      this.fileList = fileList;
    },
    httprequest(param) {
      console.log(param.file);
      this.formData.append("file", param.file);
    },
    //自定义上传文件
    async submitUpload() {
      let formData = new FormData();
      formData.append("username", this.foodform.username);
      formData.append("mainIngred", this.foodform.mainIngred);
      formData.append("ingred", this.foodform.ingred);
      formData.append("dosing", this.foodform.dosing);
      formData.append("author", this.foodform.author);
      formData.append("clsid", this.foodform.clsid);
      formData.append("file", this.fileList[0].raw);
      const { data: res } = await this.$http.post(
        "foodmanage/insertfood.do",
        formData,
        { headers: { "Content-Type": "multipart/form-data;charset=utf-8" } }
      );
      if (res.meta.status != 200) {
        return this.$message.error(res.meta.msg);
      }
      this.$message.success(res.meta.msg);
      this.$refs.uploadFormrefs.resetFields();
      this.$refs.upload.clearFiles();
    },
    //获取菜品种类
    async getFoodCls() {
      const { data: res } = await this.$http.get("foodmanage/getfoodcls.do");
      // console.log(res);
      this.options = res;
    }
  }
};
</script>
<style lang="less" scoped>
.infinite-list {
  background-color: red;
}
.div {
  // background-color: red;
  color: rgb(2, 212, 2);
  cursor: pointer;
  font-size: 10px;
}
</style>