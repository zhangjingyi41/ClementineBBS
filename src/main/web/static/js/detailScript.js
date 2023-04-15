$(()=>{
    let pid = getDataFromUrl('pid')
    getPost(pid)
})

// 从url中获取指定参数
const getDataFromUrl = (key)=>{
    let dataList = window.location.search.substring(1).split('&')
    for(let i=0;i<dataList.length;i++){
        let entry = dataList[i].split('=')
        if(entry[0]==key){
            return entry[1]
        }
    }
}

const getPost = (pid)=>{
    fetch("http://localhost:8080/post/detail",{
        method:'POST',
        headers:{
            "Content-Type":"application/json;charset=UTF-8"
        },
        body:JSON.stringify({'pid':pid})
    }).then(response=>{
        return response.json()
    }).then(json=>{
        console.log(123)
    })
}