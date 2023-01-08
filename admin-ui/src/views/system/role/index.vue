<template>
  <div class="app-container">
    <!-- 查询条件 -->
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
    <!-- 工具栏 -->
    <el-row>
      <el-button type="primary" @click="addClick()">新增角色</el-button>
    </el-row>

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

    <!-- 新增和编辑表单 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form"  label-width="100px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="form.roleName" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item label="是否启用">
          <el-radio v-model="form.delFlage" label="0">启用</el-radio>
          <el-radio v-model="form.delFlage" label="1">关闭</el-radio>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限列表">
          <el-tree
            class="tree-border"
            :data="menuData"
            ref="menuRef"
            show-checkbox
            node-key="menuId"
            :props="defaultProps"
          >
          </el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import role from "@/api/system/role";
import menu from "@/api/system/menu";
export default {
  data() {
    return {
      pageSize: 100,
      currentPage: 1,
      open: false,
      total: 0,
      title: "",
      param: {
        roleName: undefined,
        remark: undefined,
      },
      form: {},
      tableData: [],
      menuData: [],
      defaultProps: {
        children: "children",
        label: "menuName",
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    addClick() {
      this.title = "新增角色";
      this.open = true;
      menu.getMenuTreeList().then((res) => {
        this.menuData = res.data;
      });
    },
    // 查询提交方法
    onSubmit() {
      this.getList();
    },
    //新建或修改提交方法
    submit() {
      let a = this.$refs.menuRef.getCheckedKeys();
      let b = this.$refs.menuRef.getHalfCheckedKeys();
      a.push.apply(a,b);
      this.form.menuIdList = a;
      console.log(a);
      console.log(this.form);
      role.addRole(this.form).then(res=>{
        if(res.code !=200){
            this.$message(res.message);
        }else{
            this.$message.success(res.message);
            this.open = false;
            this.getList;
        }

      })

    },
    getList() {
      role
        .getRoleListByParams(this.currentPage, this.pageSize, this.param)
        .then((res) => {
          if ("200" == res.code) {
            this.tableData = res.data.records;
            this.total = res.data.total;
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