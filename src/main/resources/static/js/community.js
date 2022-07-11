
/**
 *
 */
function post() {
    const questionId = $("#question_id").val();
    const content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:'application/json',
        data: JSON.stringify( {
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if(response.code === 200){
                $("#comment_section").hide();
            }else{//出现异常
                if(response.code===2003){
                    var isAccepted = confirm(response.message);  //confirm 自带的一个提示框   如果没有登录就弹出去
                    if(isAccepted){//返回true 就去跳转页面
                        //打开一个新地址
                        window.open("https://github.com/login/oauth/authorize?client_id=1b08a10a55c9cfa9e6f3&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        /**
                         * 此处使用 html5 存储
                         * 可查阅以下网页查看资料
                         * https://www.runoob.com/html/html5-webstorage.html
                         */
                        window.localStorage.setItem("closable","true");

                    }
                }else{
                    alert(response.message);
                }
            }
            console.log(response)
        },
        dataType: "json"
    });
}