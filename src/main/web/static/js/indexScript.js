$(()=>{
    let param = {
        "pageIndex":1,
        "pageDataCount":10,
        "dataCount":0,
        "maxBtnCount":5,
    }

    getPostCount().then(result=>{
        // 获取文章总数量
        param.dataCount = result.data.postCount
        // 初始化文章列表
        getPosts(param.pageIndex, param.pageDataCount)
        // 初始化分页按钮组
        initPaginationBtnGroup(param.pageIndex,param.pageDataCount,param.dataCount,param.maxBtnCount,$('#pagination-box'))

    })
    
})


/**
 * 发起请求，获取文章的总数量
 */ 
const getPostCount = async ()=>{
    const response = await fetch("http://127.0.0.1:8080/post/postCount", {
        method:"POST",
        headers:{
            "Content-Type":"application/json;charset=UTF-8"
        }
    })
    return await response.json()
}

/**
 * 发起请求，获取一页的数据
 * 
 * @param pageIndex 页码
 * @param pageDataCount 一页的数据量
 */ 
const getPosts = (pageIndex, pageDataCount)=>{
    fetch("http://127.0.0.1:8080/post/posts", {
        method:'POST',
        headers:{
            "Content-Type":"application/json;charset=UTF-8"
        },
        body:JSON.stringify({"pageIndex":pageIndex,"pageDataCount":pageDataCount})
    }).then(response=>{
        return response.json()
    }).then(postsResult=>{
        if(postsResult.ok){
            $('#post-list').empty()
            postsResult.data.forEach(post => {
                $('#post-list').append(`
                    <div class="border padding-left padding-right margin-top margin-bottom">
                        <p class="post-title"><a href="http://127.0.0.1:5500/detail.html?pid=${post.id}">${post.title}</a></p>
                        <p class="post-body">
                            <img class="user-icon border-4" src="/static/img/icon.jpg" alt="头像">
                            <span class="user-name margin-right-small">${post.user.realname}</span>
                            <span>${post.content}</span>
                        </p>
                    </div>
                `)
            })
        }
    })
     
}

/**
 * 初始化分页按钮组（基于jQuery实现）
 * 
 * @param pageIndex 当前页码
 * @param pageDataCount 每页的数据数量
 * @param dataCount 数据的总数量
 * @param maxBtnCount 分页按钮的数量
 * @param paginationBox 分页按钮列表的容器对象
 */
const initPaginationBtnGroup = (pageIndex, pageDataCount, dataCount, maxBtnCount, paginationBox)=>{
    paginationBox.empty()

    // 计算总页数
    let pageCount = dataCount%pageDataCount==0?Math.floor(dataCount/pageDataCount):Math.floor(dataCount/pageDataCount+1)
    // 1 ... 3 4 5 6 7 8 9 ... 100
    // 1 2 3 4 5 6 7 8 ... 100
    // 创建按钮的dom对象
    const prev = $(document.createElement('button'))
    prev.text('上一页')
    prev.click(event=>{prevEvent(pageIndex-1,pageDataCount,dataCount,maxBtnCount,paginationBox)})
    const next = $(document.createElement('button'))
    next.text('下一页')
    next.click(event=>{nextEvent(pageIndex+1,pageDataCount,dataCount,maxBtnCount,paginationBox)})
    let dotPrev = prev.clone(true)
    dotPrev.text('...')
    let dotNext = next.clone(true)
    dotNext.text('...')

    let first = $(document.createElement('button'))
    first.text('1')
    first.click(event=>{pageBtnEvent(1,pageDataCount,dataCount,maxBtnCount,paginationBox)})
    let last = $(document.createElement('button'))
    last.text(pageCount)
    last.click(event=>{pageBtnEvent(pageCount,pageDataCount,dataCount,maxBtnCount,paginationBox)})
    

    // 添加上一页按钮
    paginationBox.append(prev)
    if(pageIndex <= Math.floor(maxBtnCount/2)+1){
        // 若当前页的页码在 第一页 ~（最大按钮数量的一半）之间
        // 只显示后半部分省略号
        let end = maxBtnCount
        if(pageCount<maxBtnCount){
            end = pageCount
        }
        for(let i=1;i<=end;i++){
            let btn = $(document.createElement('button'))
            btn.text(i)
            btn.click(event=>{pageBtnEvent(i,pageDataCount,dataCount,maxBtnCount,paginationBox)})
            if(i==pageIndex){
                btn.addClass('background-secondary')
            }
            paginationBox.append(btn)
        }
        // 若总页数小于规定的最大按钮数量，就不需要省略按钮和最后一页的按钮
        if(pageCount > maxBtnCount){
            paginationBox.append(dotNext)
            paginationBox.append(last)
        }
        
    }else if(pageIndex >= (pageCount-Math.floor(maxBtnCount/2)-1)){
        // 若当前页的页码在（总页数-最大按钮数量的一半）~ 最后一页 之间
        // 只显示前半部分省略号
        if(pageCount > maxBtnCount){
            paginationBox.append(first)
            paginationBox.append(dotPrev)
        }
        for(let i=pageCount-maxBtnCount+1;i<=pageCount;i++){
            let btn = $(document.createElement('button'))
            btn.text(i)
            btn.click(event=>{pageBtnEvent(i,pageDataCount,dataCount,maxBtnCount,paginationBox)})
            if(i==pageIndex){
                btn.addClass('background-secondary')
            }
            paginationBox.append(btn)
        }
    }else{
        // 若当前页不贴近第一页，也不贴近最后一页
        // 显示前半部分和后半部分的省略号按钮
        // if(pageCount > maxBtnCount){
            paginationBox.append(first)
            paginationBox.append(dotPrev)
        // }
        

        // 将当前页的页码按钮置于按钮组中间位置
        let start = pageIndex-Math.floor(maxBtnCount/2)
        let end = pageIndex+Math.floor(maxBtnCount/2)
        for(let i=start;i<=end;i++){
            let btn = $(document.createElement('button'))
            btn.text(i)
            btn.click(event=>{pageBtnEvent(i,pageDataCount,dataCount,maxBtnCount,paginationBox)})
            if(i==pageIndex){
                btn.addClass('background-secondary')
            }
            paginationBox.append(btn)
        }
        paginationBox.append(dotNext)
        paginationBox.append(last)
    }

    paginationBox.append(next)
}


// 上一页按钮事件
const prevEvent = (pageIndex, pageDataCount, dataCount, maxBtnCount, paginationBox)=>{
    if(pageIndex<1){
        return
    }
    getPosts(pageIndex, pageDataCount)
    initPaginationBtnGroup(pageIndex,pageDataCount,dataCount,maxBtnCount,paginationBox)
}
// 下一页按钮事件
const nextEvent = (pageIndex, pageDataCount, dataCount, maxBtnCount, paginationBox)=>{
    let pageCount = dataCount%pageDataCount==0?Math.floor(dataCount/pageDataCount):Math.floor(dataCount/pageDataCount+1)
    if(pageIndex > pageCount){
        return
    }
    getPosts(pageIndex, pageDataCount)
    initPaginationBtnGroup(pageIndex,pageDataCount,dataCount,maxBtnCount,paginationBox)
}
// 页码按钮事件
const pageBtnEvent = (pageIndex, pageDataCount, dataCount, maxBtnCount, paginationBox)=>{
    getPosts(pageIndex, pageDataCount)
    initPaginationBtnGroup(pageIndex,pageDataCount,dataCount,maxBtnCount,paginationBox)
}