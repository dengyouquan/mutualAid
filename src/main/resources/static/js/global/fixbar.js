layui.use('util', function(){
    var util = layui.util;

    /*util.fixbar({
        bar1: true
        ,click: function(type){
            console.log(type);
            if(type === 'bar1'){
                alert('点击了bar1')
            }
        }
    });*/


    //固定块
    util.fixbar({
        bar1: true
        , bar2: true
        , css: {right: 50, bottom: 50}
        ,showHeight:0 //用于控制出现TOP按钮的滚动条高度临界值。默认：200
        //要改成0，不然滚动200不会加载，我也不知道为什么
        , bgcolor: '#393D49'
        , click: function (type) {
            console.log(type);
            if (type === 'bar1') {
                layer.msg('联系我们')
            } else if (type === 'bar2') {
                layer.msg('帮助')
            }
        }
    });
});