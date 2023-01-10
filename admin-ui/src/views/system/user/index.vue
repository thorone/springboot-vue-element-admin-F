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
    <el-row>
      <el-button type="primary"  @click="addClick()" :disabled="$hasBP('system:user:add')  === false">新增用户</el-button>
    </el-row>
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
          <el-button type="text" size="small" @click="editClick(scope.row)"
            >编辑</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form :inline="true" :model="form">
        <el-form-item label="登陆账号">
          <el-input
            v-model="form.loginName"
            :disabled="form.userId==undefined?false:true"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.userName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱地址">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item v-if="form.userId == undefined" label="用户密码">
          <el-input
            type="password"
            v-model="form.password"
            placeholder="用户密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="form.sex" placeholder="请选择活动区域">
            <el-option label="男" value="0"></el-option>
            <el-option label="女" value="1"></el-option>
            <el-option label="未知" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="form.roleList" multiple placeholder="请选择">
            <el-option
              v-for="roleItem in roleItemList"
              :key="roleItem.roleId"
              :value="roleItem.roleId"
              :label="roleItem.roleName"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
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
import sysUser from "@/api/sysUser";
import role from "@/api/system/role"
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
      roleItemList:[],
    };
  },
  methods: {
    resetForm() {
      this.form = {};
    },
    handleClick(row) {
      this.resetForm();

      console.log(row);

      this.open = true;
    },
    addClick() {
      this.resetForm();
      this.open = true;
    },
    editClick(row) {
      this.open = true;
      sysUserApi.getUserById(row.userId).then((res) => {
        this.form = res.data;
      });
    },
    submit() {
      if (this.form.userId != undefined) {
        sysUserApi.editUser(this.form).then((res) => {
          if (res.code == 200) {
            this.$message.success("修改成功");
            this.open = false;
            this.getList();
          }
        });
      } else {
        sysUser.addUser(this.form).then((res) => {
          if (res.code == 200) {
            this.$message.success("创建成功");
            this.open = false;
            this.getList();
          }
        });
      }
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
    getRoleList(){
      role.getTotalRole().then(res=>{
        this.roleItemList = res.data;
      })
    }
  },

  created() {
    this.getList();
    this.getRoleList();
  },
};
</script>