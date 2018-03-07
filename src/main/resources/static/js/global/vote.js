var addVote = function (did,obj) {
    // 获取CSRF Token
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: "/document/addVote",
        type:'post',
        data:{
            "did":did
        },
        beforeSend: function(request) {
            request.setRequestHeader(csrfHeader, csrfToken); // 添加CSRF Token
        },
        success: function(data){
            if(data.code==0){
                /*layui.use([ 'layer'], function() {
                    var layer = layui.layer;
                    layer.msg("点赞成功！");
                });*/
                //alert(data.data)
                if(data.data!=0) {
                    $(obj).find(".voteSize").text(data.data + ' ');
                }
                else {
                    $(obj).find(".voteSize").text(' ');
                }
                if(data.msg=="1"){
                    //已点赞更改
                    $(obj).find(".thumbs").removeClass("outline");
                }else if(data.msg=="0"){
                    $(obj).find(".thumbs").addClass("outline");
                }
            }
        },
        error : function() {
            layui.use([ 'layer'], function() {
                var layer = layui.layer;
                layer.msg("点赞失败！");
            });
        }
    });
}

var addDownVote = function (did,obj) {
    //find(子孙) children(儿子) filter(同代)
    // 获取CSRF Token
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: "/document/addDownVote",
        type:'post',
        data:{
            "did":did
        },
        beforeSend: function(request) {
            request.setRequestHeader(csrfHeader, csrfToken); // 添加CSRF Token
        },
        success: function(data){
            if(data.code==0){
                /*layui.use([ 'layer'], function() {
                    var layer = layui.layer;
                    layer.msg("踩成功！");
                });*/
                //alert(data.data)
                if(data.data!=0){
                    $(obj).find(".downVoteSize").text(data.data+' ');
                }
                else {
                    $(obj).find(".downVoteSize").text(' ');
                }
                if(data.msg=="1"){
                    //已点赞更改
                    $(obj).find(".thumbs").removeClass("outline");
                }else if(data.msg=="0"){
                    $(obj).find(".thumbs").addClass("outline");
                }
            }
        },
        error : function() {
            layui.use([ 'layer'], function() {
                var layer = layui.layer;
                layer.msg("踩失败！");
            });
        }
    });
}

var addCommentVote = function (dcid,obj) {
    // 获取CSRF Token
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: "/document/comment/addVote",
        type:'post',
        data:{
            "dcid":dcid
        },
        beforeSend: function(request) {
            request.setRequestHeader(csrfHeader, csrfToken); // 添加CSRF Token
        },
        success: function(data){
            if(data.code==0){
                $(obj).prev("a").find(".voteSize").text(data.data + ' ');
                if(data.msg=="1"){
                    //已点赞更改
                    $(obj).find(".thumbs").removeClass("outline");
                }else if(data.msg=="0"){
                    $(obj).find(".thumbs").addClass("outline");
                }
            }
        },
        error : function() {
            layui.use([ 'layer'], function() {
                var layer = layui.layer;
                layer.msg("点赞失败！");
            });
        }
    });
}

var addCommentDownVote = function (dcid,obj) {
    //find(子孙) children(儿子) filter(同代)
    // 获取CSRF Token
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: "/document/comment/addDownVote",
        type:'post',
        data:{
            "dcid":dcid
        },
        beforeSend: function(request) {
            request.setRequestHeader(csrfHeader, csrfToken); // 添加CSRF Token
        },
        success: function(data){
            if(data.code==0){
                $(obj).prev("a").find(".downVoteSize").text(data.data+' ');
                if(data.msg=="1"){
                    //已点赞更改
                    $(obj).find(".thumbs").removeClass("outline");
                }else if(data.msg=="0"){
                    $(obj).find(".thumbs").addClass("outline");
                }
            }
        },
        error : function() {
            layui.use([ 'layer'], function() {
                var layer = layui.layer;
                layer.msg("踩失败！");
            });
        }
    });
}

$(function () {
});
