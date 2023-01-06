import request from '@/utils/request'

const api_name = '/api/user'

export default{
    getUserInfo(page,limit,params){
        return request({
            //接口路径
            url: `${api_name}/getUserList/${page}/${limit}`,
            method: 'post', //提交方式
            //参数
            data: params
        })
    }
}