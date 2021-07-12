<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>动态管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户评论管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <el-table style="width: 100%" border :data="reviewList" class="tableBox">
        <el-table-column type="index" align="center"></el-table-column>
        <el-table-column label="评论者" prop="reviewer" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="评论时间" prop="review_Time" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="评论内容" prop="review_Content" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="动态发表者" prop="dynamicer" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="动态图片" prop="reviewer" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <el-popover placement="left" trigger="click" class="popover" width="160">
              <img :src="scope.row.image_Path" alt style="width:160px;height:160px;" />
              <img
                slot="reference"
                :src="scope.row.image_Path"
                alt
                style="height: 30px;width: 30px;"
              />
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="动态内容" prop="dynamic_Content" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="动态时间" prop="dynamic_Time" show-overflow-tooltip align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="170px">
          <template slot-scope="scope">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="visibleDialog(scope.row.review_Id)"
            ></el-button>
            <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
              <span>是否要删除该评论?</span>
              <span slot="footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="delUserReview()">确 定</el-button>
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
    this.getUserReviewList();
    this.getReviewCount();
  },
  data() {
    return {
      reviewList: [],
      dialogVisible: false,
      reviewId: 0,
      total: 0,
      pagesize: 3,
      currpage: 1
    };
  },
  methods: {
    async getUserReviewList() {
      const { data: res } = await this.$http.get(
        "userreview/getuserreview.do",
        { params: { currpage: this.currpage, pagesize: this.pagesize } }
      );
      this.reviewList = res;
    },
    //获取记录行
    async getReviewCount() {
      const { data: res } = await this.$http.get(
        "userreview/getreviewcount.do"
      );
      this.total = res;
    },
    //分页
    handleCurrentChange(pages) {
      this.currpage = pages;
      this.getUserReviewList();
    },
    //删除用户评论
    visibleDialog(id) {
      this.reviewId = id;
      this.dialogVisible = true;
    },
    //删除用户评论
    async delUserReview() {
      const { data: res } = await this.$http.get(
        "userreview/deluserreview.do",
        {
          params: { review_Id: this.reviewId }
        }
      );
      if (res.meta.status != 200) {
        return this.$message.error(res.meta.msg);
      }
      this.$message.success(res.meta.msg);
      this.getUserReviewList();
      this.getReviewCount();
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