<template>
  <div class="app-container">
    <el-form :inline="true" :model="param" class="demo-form-inline">
      <el-form-item label="用户名">
        <el-input v-model="param.username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="param.email" placeholder="邮箱地址"></el-input>
      </el-form-item>
      <el-form-item label="手机">
        <el-input v-model="param.phonenumber" placeholder="电话号码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" height="500" border style="width: 100%">
      <el-table-column
        fixed
        label="编号"
        width="100"
        prop="userId"
      ></el-table-column>
      <el-table-column
        label="登陆账号"
        width="100"
        prop="loginName"
      ></el-table-column>
      <el-table-column
        label="用户名"
        width="120"
        prop="userName"
      ></el-table-column>
      <el-table-column label="邮箱" width="120" prop="email"></el-table-column>
      <el-table-column label="备注" width="120" prop="remark"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="150">
      </el-table-column>
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small"
            >查看</el-button
          >
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form :model="form">
        <el-form-item label="活动名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动区域" >
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>
  


<script>
import sysUserApi from "@/api/sysUser";
export default {
  data() {
    return {
      currentPage: 1,
      pageSize: 100,
      total: 0,
      title: "编辑用户",
      open: false,
      tableData: [],
      form: {},
      param: {
        username: "",
        email: "",
        phonenumber: "",
      },
    };
  },
  methods: {
    resetForm(){
        this.form={}
    },
    handleClick(row) {
      this.resetForm();

      console.log(row);
      this.open = true;
    },
    onSubmit() {
      this.getList();
    },
    getList() {
      console.log(this.param);
      sysUserApi
        .getUserInfo(this.currentPage, this.pageSize, this.param)
        .then((res) => {
          this.tableData = res.data.records;
          this.total = res.data.total;
        });
    },
    handleSizeChange(pagesize) {
      this.pageSize = pagesize;
      this.getList();
    },
    handleCurrentChange(currentPage) {
      console.log(currentPage);
      this.currentPage = currentPage;
      console.log(this.currentPage);
      this.getList();
    },
  },

  created() {
    this.getList();
  },
};
</script>