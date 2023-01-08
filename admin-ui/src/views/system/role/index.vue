<template>
  <div class="app-container">
    <el-form :inline="true" :model="param" class="demo-form-inline">
      <el-form-item label="角色名称">
        <el-input v-model="param.roleName" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item label="角色备注">
        <el-input v-model="param.remark" placeholder="邮箱地址"></el-input>
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
        prop="roleId"
      ></el-table-column>
      <el-table-column
        label="角色名称"
        width="100"
        prop="roleName"
      ></el-table-column>
      <el-table-column
        label="创建人"
        width="120"
        prop="createBy"
      ></el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="150">
      </el-table-column>
      <el-table-column
        label="更新人"
        width="120"
        prop="updateBy"
      ></el-table-column>
      <el-table-column prop="createTime" label="更新时间" width="150">
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
import role from "@/api/system/role";
export default {
  data() {
    return {
      pageSize: 100,
      currentPage: 1,
      param: {
        roleName: undefined,
        remark: undefined,
      },
      tableData: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    onSubmit() {
        console.log(11);
      this.getList();
    },
    getList() {
      role
        .getRoleListByParams(this.currentPage, this.pageSize, this.param)
        .then((res) => {
          if ("200" == res.code) {
            this.tableData = res.data.records;
          }
        });
    },
    handleSizeChange(size) {
      this.pageSize = size;
      this.getList();
    },
    handleCurrentChange(page) {
      this.currentPage = page;
      this.getList();
    },
  },
};
</script>