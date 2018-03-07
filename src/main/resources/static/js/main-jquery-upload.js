$('#fileupload').fileupload({
    url:'/uploadfile',
    autoUpload:true,
    sequentialUploads: true,
    add: function(e, data) {
        var filename = data.files[0].name;
        var fileListLenght = $('.filesName').find('tr').length;
        for (var i = 0; i < fileListLenght; i++) {
            if (filename == $('.filesName').find('.name').eq(i).text()) {
                $('.tips').text("不能重复上传！");
                return false;
            }
        }
        filesList = "filesList" + fileListLenght;
        var filesListHTML = '<tr class="' + filesList + '">' +
            '<td>' +
            '<p class="name">' + filename + '</p>' +
            '<div class="progress">' +
            '<div class="bar" style="width: 0%;"></div>' +
            '</div>' +
            '<strong class="error"></strong>' +
            '</td>' +
            '</tr>';
        $('.filesName').append(filesListHTML);
        data.context = $("." + filesList);
        data.submit();
    },
    //单个进度条
    progress: function(e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        data.context.find('.bar').css('width', progress + '%');
    },
    //上传失败
    fail: function(e, data) {
        data.context.find('.error').text('上传失败');
    },
    //上传完成
    done : function(e, data) {
        $('.filesName').find('.progress').parent().parent().remove();
        $.each(data.files, function (index, file) {
            console.log(file);
            if(data.result.success){
                var filesList = "filesList" + $('.filesName').find('tr').length;
                var filesListHTML ='<tr class="' + filesList + '">' +
                    '<td>' +
                    '<p class="name">' + file.name + '</p>' +
                    '</td>' +
                    '<td class="btns">' +
                    '<button class="delete">删除</button>&nbsp;' +
                    /*'<button class="download">下载</button>' +*/
                    '</td>' +
                    '</tr>';
                $(".filesName").append(filesListHTML);

                $("."+filesList).find('.delete').click(function(){
                    $('.ui .modal')
                        .modal({

                            closable  : false,
                            onDeny    : function(){
                            },
                            onApprove : function() {
                                $("."+filesList).remove();
                            }
                        })
                        .modal('show')
                    ;
                    /*var b = confirm("信息提示","是否确认删除？");
                    if(b){
                        $("."+filesList).remove();
                    }*/
                });

                $("."+filesList).find('.download').click(function(){
                    downloadFile(res[1],res[3]);
                });
            }
        });
    }
});