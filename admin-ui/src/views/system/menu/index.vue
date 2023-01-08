<template>
  <div>
    <el-table
      :data="tableData"
      style="width: 100%; margin-bottom: 20px"
      row-key="menuId"
      border
      default-expand-all
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <el-table-column prop="menuName" label="菜单名称" sortable width="180">
      </el-table-column>
      <el-table-column prop="perms" label="权限符" sortable width="180">
      </el-table-column>
      <el-table-column prop="icon" label="图标"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
import menuApi from "@/api/system/menu";
export default {
  data() {
    return {
      pageSize: 100,
      currentPage: 1,
      tableData:[]
    };
  },
  created() {
    menuApi.getMenuTreeList().then((res) => {
      this.tableData = res.data;
    });
  },
  methods:{

  },
};
</script>