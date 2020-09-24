$.validator.setDefaults({
    submitHandler : function() {
        addSpace();
    }
});

var app = new Vue({
    el:"#app",
    methods:{
        validateSpace:function () {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules : {
                    id : {
                        required : true
                    }, spaceId : {
                        required : true
                    }, spaceName : {
                        required : true
                    }
                },
                messages : {
                    spaceId : {
                        required : icon + "请输入区域"
                    }, spaceName : {
                        required : icon + "请输入区域名称"
                    }
                }
            })
        }
    },
    mounted: function () {
        this.validateSpace();
    }
});

function addSpace(){
    var spaceVO = {
        'spaceId':$("#spaceId").val(),
        "spaceName":$("#spaceName").val(),
        "objectVoltage":$("#objectVoltage").val(),
        "spaceTag":$("#spaceTag").val()
    };
    $.ajax({
        cache : true,
        type : "POST",
        url : context + 'space/addSpace',
        data :JSON.stringify(spaceVO),
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
                    parent.layer.msg("该区域已存在，操作失败");
                } else if (data.data.code === 500){
                    parent.layer.msg("操作失败");
                }
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            }
        }
    });
}