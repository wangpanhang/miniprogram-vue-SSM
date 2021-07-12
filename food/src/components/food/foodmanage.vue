<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>菜谱管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜谱信息管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <!-- 搜索框 -->
      <el-row>
        <el-col :span="7">
          <el-input
            placeholder="请输入内容"
            class="input-with-select"
            v-model="query"
            clearable
            @clear="clearInput"
          >
            <el-button slot="append" icon="el-icon-search" @click="searchFood"></el-button>
          </el-input>
        </el-col>
      </el-row>
      <!-- 表格区域 -->
      <el-table style="width: 100%" border :data="foodtable" class="tableBox">
        <el-table-column type="index"></el-table-column>
        <el-table-column label="菜谱名称" prop="food_Name" align="center"></el-table-column>
        <el-table-column label="图片" align="center">
          <template slot-scope="scope">
            <el-popover placement="left" trigger="click" class="popover" width="160">
              <img :src="scope.row.food_Image" alt style="width:160px;height:160px;" />
              <img
                slot="reference"
                :src="scope.row.food_Image"
                alt
                style="height: 30px;width: 30px;"
              />
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="菜谱主料" prop="food_MainIngred" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="菜谱辅料" prop="food_Ingred" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="菜谱配料" prop="food_Dosing" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="作者" prop="food_Author" align="center"></el-table-column>
        <el-table-column label="类别" prop="cls_Name" align="center"></el-table-column>
        <el-table-column label="操作" width="170px" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="getFoodInfo(scope.row.food_Id)"
            ></el-button>
            <!-- 菜谱信息修改对话框 -->
            <el-dialog title="菜谱信息修改" :visible.sync="dialogFormVisible" width="680px">
              <el-form :model="foodInfo" label-width="80px" style="width:60%">
                <el-form-item label="菜谱名称">
                  <el-input v-model="foodInfo.food_Name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="图片">
                  <img :src="foodInfo.food_Image" alt style="width:100px;height:100px;" />
                </el-form-item>
                <el-form-item label="菜谱主料">
                  <el-input type="textarea" :rows="3" v-model="foodInfo.food_MainIngred"></el-input>
                </el-form-item>
                <el-form-item label="菜谱辅料">
                  <el-input type="textarea" :rows="2" v-model="foodInfo.food_Ingred"></el-input>
                </el-form-item>
                <el-form-item label="菜谱配料">
                  <el-input type="textarea" :rows="2" v-model="foodInfo.food_Dosing"></el-input>
                </el-form-item>
                <el-form-item label="菜谱作者">
                  <el-input v-model="foodInfo.food_Author"></el-input>
                </el-form-item>
                <el-form-item label="菜谱类别">
                  <el-select v-model="foodInfo.cls_Id" placeholder="请选择类别" @change="getCurrentId">
                    <el-option
                      v-for="item in options"
                      :key="item.clsid"
                      :label="item.clsname"
                      :value="item.clsid"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-form>
              <div slot="footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="alertFoodInfo()">确 定</el-button>
              </div>
            </el-dialog>
            <!-- 删除菜谱按钮 -->
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="visibleDialog(scope.row.food_Id)"
            ></el-button>
            <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
              <span>是否要删除该菜谱?</span>
              <span slot="footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="delFoodInfo()">确 定</el-button>
              </span>
            </el-dialog>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        layout="total, prev, pager, next"
        :total="total"
        @current-change="handleCurrentChange"
        :page-size="pagesize"
      ></el-pagination>
    </el-card>
  </div>
</template>
<script>
export default {
  created() {
    this.getFoodList();
    this.getFoodCount();
  },
  data() {
    return {
      foodtable: [],
      //当前页码
      currpage: 1,
      //每页显示多少条数据
      pagesize: 7,
      //总的条目数
      total: 0,
      //搜索栏内容
      query: "",
      //是否显示对话框
      dialogFormVisible: false,
      dialogVisible: false,
      //单个菜谱信息
      foodInfo: {
        food_Name: "",
        food_Image: "",
        food_MainIngred: "",
        food_Ingred: "",
        food_Dosing: "",
        food_Author: "",
        cls_Id: ""
      },
      //select选择器的绑定值
      options: [],
      //select选择器当前选中的值
      currentId: 0,
      formData: "",
      //当前选中的food_Id
      foodId: 0
    };
  },
  methods: {
    // 获取数据列表
    async getFoodList() {
      const { data: res } = await this.$http.get("/foodmanage/getfoodlist.do", {
        params: { currpage: this.currpage, pagesize: this.pagesize }
      });
      this.foodtable = res;
    },
    //获取记录行
    async getFoodCount() {
      const { data: res } = await this.$http.get("/foodmanage/getfoodcount.do");
      this.total = res;
    },
    handleCurrentChange(pages) {
      this.currpage = pages;
      this.getFoodList();
    },
    //搜索菜谱
    async searchFood() {
      const { data: res } = await this.$http.get(
        "/foodmanage/getsearchfood.do",
        { params: { query: this.query } }
      );
      this.foodtable = res;
      this.total = res.length;
    },
    // 清空输入栏
    clearInput() {
      this.getFoodList();
      this.getFoodCount();
    },
    //获取单个菜谱信息
    async getFoodInfo(id) {
      this.dialogFormVisible = true;
      const { data: res } = await this.$http.get("foodmanage/getfoodinfo.do", {
        params: { food_Id: id }
      });
      this.foodInfo = res;
      this.currentId = res.cls_Id;
      const { data: res1 } = await this.$http.get("foodmanage/getfoodcls.do");
      this.options = res1;
    },
    //获取当前选中的select选择器的值
    getCurrentId(id) {
      this.currentId = id;
    },
    //修改菜谱信息
    async alertFoodInfo() {
      console.log(this.foodInfo.food_Id);
      let formData = new FormData();
      formData.append("food_Id", this.foodInfo.food_Id);
      formData.append("food_Name", this.foodInfo.food_Name);
      formData.append("food_MainIngred", this.foodInfo.food_MainIngred);
      formData.append("food_Ingred", this.foodInfo.food_Ingred);
      formData.append("food_Dosing", this.foodInfo.food_Dosing);
      formData.append("food_Author", this.foodInfo.food_Author);
      formData.append("cls_Id", this.foodInfo.cls_Id);
      const { data: res } = await this.$http.post(
        "foodmanage/alertfoodinfo.do",
        formData,
        { headers: { "Content-Type": "application/x-www-form-urlencoded" } }
      );
      if (res.meta.status != 200) {
        return this.$message.error(res.meta.msg);
      }
      this.$message.success(res.meta.msg);
      this.getFoodList();
      this.getFoodCount();
      this.dialogFormVisible = false;
    },
    //显示删除对话框
    visibleDialog(food_Id) {
      this.dialogVisible = true;
      this.foodId = food_Id;
    },
    // 删除菜谱
    async delFoodInfo() {
      console.log(this.foodId);
      const { data: res } = await this.$http.get("foodmanage/delfoodinfo.do", {
        params: { food_Id: this.foodId }
      });
      if (res.meta.status != 200) {
        return this.$message.error(res.meta.msg);
      }
      this.$message.success(res.meta.msg);
      this.getFoodList();
      this.getFoodCount();
      this.dialogVisible = false;
    }
  }
};
</script>
<style lang="less" scoped>
.tableBox {
  > th {
    padding: 0 !important;
    height: 30px;
    line-height: 30px;
  }
  > td {
    padding: 0 !important;
    height: 30px;
    line-height: 30px;
  }
}
.popover {
  height: 30px;
}
</style>