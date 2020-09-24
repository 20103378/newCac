$.validator.setDefaults({
    submitHandler : function() {
        addSite();
    }
});

var app = new Vue({
    el:"#app",
    methods:{
        validateSite:function () {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules : {
                    id : {
                        required : true
                    }, site : {
                        required : true
                    }, siteName : {
                        required : true
                    }
                },
                messages : {
                    site : {
                        required : icon + "请输入站点"
                    }, siteName : {
                        required : icon + "请输入站点单位"
                    }
                }
            })
        }
    },
    mounted: function () {
        this.validateSite();
    }
});

function addSite(){
    var siteVO = {
        'site':$("#site").val(),
        "siteName":$("#siteName").val(),
        "siteAddr":$("#siteAddr").val(),
    };
    $.ajax({
        cache : true,
        type : "POST",
        url : context + 'site/addSite',
        data :JSON.stringify(siteVO),
        dataType : 'json',
        contentType:'application/json',
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            debugger
            if (data.code === 200) {
                if (data.data.code === 200){
                    parent.layer.msg("操作成功");
                } else if (data.data.code === 501){
                    parent.layer.msg("该站点已存在，操作失败");
                } else if (data.data.code === 500){
                    parent.layer.msg("操作失败");
                }
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            }
        }
    });
}