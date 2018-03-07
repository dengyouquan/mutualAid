function getDocuments(pageIndex, pageSize,category,mode,async,keyword) {
    $.ajax({
        url: "/document",
        contentType : 'application/json',
        data:{
            "page":pageIndex,
            "limit":pageSize,
            "mode":mode,
            "category":category,
            "async":async,
            "keyword":keyword
        },
        success: function(data){
            var str = "#"+mode+"Container";
            console.log(str);
            $(str).html(data);//局部刷新DIV
        },
        error : function() {
            toastr.error("error!");
        }
    });
}

$(function() {
    $("#searchButton").click(function () {
        var keyword = $("#searchText").val();
        window.location.href="/document?page=1&limit=2&keyword="+keyword;
    });
});