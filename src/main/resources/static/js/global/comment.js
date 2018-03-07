function addReply(content,comment_id,async) {
    // 获取CSRF Token
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
        url: "/document/addReply",
        type:'post',
        //contentType : 'application/json',//加就是get方法传参（&连接） 不加上是post传参 ？？？
        data:{
            "content":content,
            "async":async,
            "comment_id":comment_id
        },
        beforeSend: function(request) {
            request.setRequestHeader(csrfHeader, csrfToken); // 添加CSRF Token
        },
        success: function(data){
            if(data.code==0){
                layui.use([ 'layer'], function() {
                    var layer = layui.layer;
                    layer.msg("回复成功！");
                });
            }
            var str = "#replyContainer";
            console.log(str);
            console.log(data);
            $(str).html(data);//局部刷新DIV
        },
        error : function() {
            layui.use([ 'layer'], function() {
                var layer = layui.layer;
                layer.msg("回复失败！");
            });
        }
    });
}

function getComments(pageIndex, pageSize,async,d_id) {
    $.ajax({
        url: "/comment",
        contentType : 'application/json',
        data:{
            "page":pageIndex,
            "limit":pageSize,
            "async":async,
            "d_id":d_id
        },
        success: function(data){
            var str = "#commentContainer";
            console.log(str);
            //console.log(data);
            $(str).html(data);//局部刷新DIV
        },
        error : function() {
            toastr.error("error!");
        }
    });
}

var setReplyCall = function () {
    //回复评论(延迟绑定点击事件，不然themeleaf未加载好)
    $(".replyComment").click(function () {
        var comment_id = $(this).attr("data");
        var comment_name = $(this).attr("name");
        //弹出层
        layui.use([ 'layer'], function() {
            var $ = layui.jquery
                , layer = layui.layer;
            //例子2
            layer.prompt({
                formType: 2,//输入框类型，支持0（文本）默认1（密码）2（多行文本）
                value: '',
                title: '回复'+comment_name+":(请输入2-500个字符)",
                area: ['600px', '300px'] //自定义文本域宽高
            }, function(value, index, elem){
                if(value.length>=2 && value.length<=500){
                    addReply(value,comment_id,false);
                    layer.close(index);
                }else{
                    layer.msg("请输入2-500个字符");
                }
            });
        });
    });
}

var getDate = function () {
    var url = location.pathname;
    if(url=="/documentdetail"){
        //显示时间
        var showTime = $('.need-time-ago');
        //console.log(showTime)
        //console.log(showTime.eq(0))
        for(var i=0;i<showTime.length;i++)
            showTime.eq(i).text(timeago.format(showTime.eq(i).text(), 'zh_CN'));
    }
}

var getPage = function(){
    //分页
    layui.use(['laypage'], function() {
        var $ = layui.jquery
        ,laypage = layui.laypage;
        var commentCount = $('#commentCount').text();
        laypage.render({
            elem: 'commentPage'
            ,count: commentCount
            ,limit:5
            ,skip: true //开启跳页
            ,skin: '#2F7111' //自定义选中色值
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
            ,jump: function(obj,first){
                var esDocumentId = $("#esDocumentId").text();
                //首次不执行
                if(!first){
                    getComments(obj.curr,5,true,esDocumentId);
                    window.setTimeout("getDate()",100);
                    window.setTimeout("setReplyCall()",200);
                }
            }
        });
    });
}

var checkCommmentData = function (formData, jqForm, options) {
        // submit the form
        var content = $("#content").val();
        if(content.length==0){
            layui.use([ 'layer'], function() {
                var layer = layui.layer;
                layer.msg("评论内容不能为空！");
            });
            return false;
        }
        if(content.length>=2 && content.length<=500){
            return true;
        }else{
            layui.use([ 'layer'], function() {
                var layer = layui.layer;
                layer.msg("评论字数需在2-500之间！字符数："+content.length);
            });
        }
        // return false to prevent normal browser submit and page navigation
        return false;
}

var callBackAddComment = function (formData, jqForm, options){
    $("#content").val("");
    var esDocumentId = $("#esDocumentId").text();
    getComments(1,5,true,esDocumentId);
    window.location.href="/documentdetail#commentContainer?did="+data.data
    window.setTimeout("getDate()",100);
    window.setTimeout("setReplyCall()",200);
    layui.use([ 'layer'], function() {
        var layer = layui.layer;
        layer.msg("评论成功！");
    });
}

//addComment form回调
var addCommentListener = function () {
    //判断内容是否正确
    var options = {
        beforeSubmit:checkCommmentData,
        success:callBackAddComment,
        error : function(xhr, status, err) {
            layui.use([ 'layer'], function() {
                var layer = layui.layer;
                layer.msg("评论失败！");
            });
        }
    };
    $('#addCommentForm').ajaxForm(options);
}

var setEmojiPicker = function () {
    // Initializes and creates emoji set from sprite sheet
    window.emojiPicker = new EmojiPicker({
        emojiable_selector: '[data-emojiable=true]',
        iconSize:25,
        assetsPath: 'emoji/img/',
        popupButtonClasses: 'fa fa-smile-o'
    });
    // Finds all elements with `emojiable_selector` and converts them to rich emoji input fields
    // You may want to delay this step if you have dynamically created input fields that appear later in the loading process
    // It can be called as many times as necessary; previously converted input fields will not be converted again
    window.emojiPicker.discover();
}

var timeago = timeago();

$(function() {
    //直接加载themeleaf的数据，不能立即获取数据，设置一个定时
    window.setTimeout("getDate()",100);
    window.setTimeout("setReplyCall()",200);
    var url = location.pathname;
    if(url=="/documentdetail"){
        var esDocumentId = $("#esDocumentId").text();
        getComments(1,5,true,esDocumentId);
    }
    addCommentListener();
    setEmojiPicker();
    //从网站加载资料内容(在java中得到)
});



layui.use(['layer','laypage'], function() {
    var $ = layui.jquery
        , laypage = layui.laypage
        , layer = layui.layer;

    setTimeout('getPage()',1000);
});