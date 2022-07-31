/**
 * Created by codedrinker on 2019/6/1.
 */

/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);
}
function questionSubmit(frm){
    var title = frm.title;
    var description = frm.description;
    var tag = frm.tag;
    var titleAlert = document.getElementById("title-alert");
    var descriptionAlert = document.getElementById("description-alert");
    var tagAlert = document.getElementById("tag-alert");
    var flag = true;
    if(title.value === ""){
        titleAlert.style.display="block";
        titleAlert.textContent="标题都不要了吗";
        flag = false;
    }else if(title.value>1024){
        titleAlert.style.display="block";
        titleAlert.textContent="标题太大了，喂！";
        flag = false;
    }
    if(description.value === ""){
        descriptionAlert.style.display="block";
        descriptionAlert.textContent="简要不能为空，喂！";
        flag = false;
    }else if(description.value.length>10240000){
        descriptionAlert.style.display="block";
        descriptionAlert.textContent="搞什么，有必要写成一本书吗，喂！";
        flag = false;
    }
    if(tag.value === ""){
        tagAlert.style.display="block";
        tagAlert.textContent="标签都不要了吗，喂！";
        flag = false;
    }else if(tag.value.length>1024){
        tagAlert.style.display="block";
        tagAlert.textContent="什么标签都要？，喂！";
        flag = false;
    }
    debugger
    return flag;
}
function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            debugger
            if (response.code === 200) {
                window.location.reload();
            } else {
                if (response.code === 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        $('#myModal').modal({});
                        // window.open("https://github.com/login/oauth/authorize?client_id=7f316909bf70d1eaa2b2&redirect_uri=" + document.location.origin + "/callback&scope=user&state=1");
                        // window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    const id = e.getAttribute("data-id");
    const comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    const collapse = e.getAttribute("data-collapse");
    if (collapse) {
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        // 折叠二级评论
        const subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length !== 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    const mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();

    if (previous) {
        var index = 0;
        var appear = false; //记录value是否已经作为一个独立的标签出现过
        while (true) {
            index = previous.indexOf(value, index); //value字符串在previous中出现的位置
            if (index == -1) break;
            //判断previous中出现的value是否是另一个标签的一部分
            //即value的前一个和后一个字符都是逗号","或者没有字符时，才说明value是一个独立的标签
            if ((index == 0 || previous.charAt(index - 1) == ",")
                && (index + value.length == previous.length || previous.charAt(index + value.length) == ",")
            ) {
                appear = true;
                break;
            }
            index++; //用于搜索下一个出现位置
        }
        if (!appear) {
            //若value没有作为一个独立的标签出现过
            $("#tag").val(previous + ',' + value);
        }
    }
    else {
        $("#tag").val(value);
    }
}


function username(){
    console.log('用户名可用!')
    alert('用户名可用!');
    return false;
    debugger
    // var regex=/\w*/;
    //
    // var username =document.getElementById('username').value;
    //
    // var a=regex.exec(username);
    //
    // if(a !== ""){
    //
    // alert('用户名可用!');
    //
    // }else{
    //
    // alert('有非法字符');
    //
    //
    // }
}

function postSetting(){
    var accountId = $("#setting_accountId").val();
    var username = $("#username").val();
    console.log(accountId);
    console.log(username);

    if (!username) {
        alert("用户名不能为空");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/setting",
        contentType: 'application/json',
        data: JSON.stringify({
            "accountId": accountId,
            "username": username,
        }),
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                if (response.code === 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        $('#myModal').modal({});
                        // window.open("https://github.com/login/oauth/authorize?client_id=7f316909bf70d1eaa2b2&redirect_uri=" + document.location.origin + "/callback&scope=user&state=1");
                        // window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });

    debugger
}