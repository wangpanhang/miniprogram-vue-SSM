<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>动态管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户动态管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-table style="width: 100%" border :data="dynamicList" class="tableBox">
        <el-table-column type="index" align="center"></el-table-column>
        <el-table-column label="动态发表者" prop="user_NickName" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="动态图片" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-popover placement="left" trigger="click" class="popover" width="160">
              <img :src="scope.row.imagePath" alt style="width:160px;height:160px;" />
              <img
                slot="reference"
                :src="scope.row.imagePath"
                alt
                style="height: 30px;width: 30px;"
              />
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="动态内容" prop="content" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="动态时间" prop="upLoadDate" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="170px">
          <template slot-scope="scope">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="visibleDialog(scope.row.dynamic_Id)"
            ></el-button>
            <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
              <span>是否要删除该动态?</span>
              <span slot="footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="delUserDynamic()">确 定</el-button>
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
    this.getUserDynamicList();
    this.getDynamicCount();
  },
  data() {
    return {
      dynamicList: [],
      dialogVisible: false,
      dynamicId: 0,
      total: 0,
      pagesize: 3,
      currpage: 1
    };
  },
  methods: {
    async getUserDynamicList() {
      const { data: res } = await this.$http.get(
        "userdynamic/getuserdynamic.do",
        {
          params: { currpage: this.currpage, pagesize: this.pagesize }
        }
      );
      this.dynamicList = res;
    },
    //获取记录行
    async getDynamicCount() {
      const { data: res } = await this.$http.get(
        "userdynamic/getdynamiccount.do"
      );
      this.total = res;
    },
    //分页
    handleCurrentChange(pages) {
      this.currpage = pages;
      this.getUserDynamicList();
    },
    //删除用户动态
    visibleDialog(id) {
      this.dynamicId = id;
      this.dialogVisible = true;
    },
    //删除用户动态
    async delUserDynamic() {
      const { data: res } = await this.$http.get(
        "userdynamic/deluserdynamic.do",
        {
          params: { dynamic_Id: this.dynamicId }
        }
      );
      if (res.meta.status != 200) {
        return this.$message.error(res.meta.msg);
      }
      this.$message.success(res.meta.msg);
      this.getUserDynamicList();
      this.getDynamicCount();
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