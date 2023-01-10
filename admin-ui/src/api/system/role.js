import request from '@/utils/request'

const role_api = '/system/role';

export default{
    addRole(param){
        return request({
            url:`${role_api}/addRole`,
            method:'post',
            data:param
        })
    },
    removeRoleById(roleId){
        return request({
            url:`${role_api}/removeRoleById/${roleId}`,
            method:'delete',
            params:''
        })
    },
    updateRole(param){
        return request({
            url:`${role_api}/updateRole`,
            method:'post',
            data:param
        })
    },

    getRoleById(roleId){
        return request({
            url:`${role_api}/getRoleById/${roleId}`,
            method:'get',
            params:''
        })
    },
    getRoleListByParams(page,limit,param){
        return request({
            url:`${role_api}/getRoleListByParams/${page}/${limit}`,
            method:'post',
            data:param
        })
    },
    getTotalRole(){
        return request({
            url:`${role_api}/getTotalRole`,
            method:'get',
            params:''
        })
    }
}