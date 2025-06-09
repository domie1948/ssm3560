const base = {
    get() {
                return {
            url : "http://localhost:8080/kaifangshiyanshi/",
            name: "kaifangshiyanshi",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/kaifangshiyanshi/front/index.html'
        };
            },
    getProjectName(){
        return {
            projectName: "开放性实验室管理系统"
        } 
    }
}
export default base
