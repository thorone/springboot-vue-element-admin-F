import request from '@/utils/request'

const menu_api = '/system/menu'

export default {
    addMenu(param) {
        return request({
            url: `${menu_api}/addMenu`,
            method: 'post',
            data:param
        })
    },
    removeMenuById(menuId){
        return request({
            url:`${menu_api}/removeMenuById/${menuId}`,
            method: 'delete',
            param:''
        })
    },
    editMenu(param){
        return request({
            url:`${menu_api}/editMenu`,
            method: 'post',
            data:param, 
        })
    },
    getMenuById(menuId){
        return request({
            url: `${menu_api}/getMenuById/${menuId}`,
            method: 'get',
            params:''
        })
    },
    getMenuByParams(page,limit,params){
        return request({
            url:`${menu_api}/getMenuByParams/${page}/${limit}`,
            method:'post',
            data:params
        })
    },
    getMenuTreeList(){
        return request({
            url:`${menu_api}/getMenuTreeList`,
            method:'get',
            params:''
        })
    }

}

