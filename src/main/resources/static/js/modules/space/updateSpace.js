$.validator.setDefaults({
    submitHandler : function() {
        updateSpace();
    }
});

var id=$("#id").val();
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
    mounted:function () {
        this.validateSpace();
    }
});

function updateSpace(){
    var spaceVO = {
        'id':$("#id").val(),
        'spaceId':$("#spaceId").val(),
        "spaceName":$("#spaceName").val(),
        "objectVoltage":$("#objectVoltage").val(),
        "spaceTag":$("#spaceTag").val()
    };
    $.ajax({
        cache : true,
        type : "POST",
        url : context + 'space/updateSpace',
        data :JSON.stringify(spaceVO),
        dataType : 'json',
        contentType:'application/json',
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code === 200) {
                if (data.data.code === 200){
                    parent.layer.msg("操作成功");
                } else if (data.data.code === 500){
                    parent.layer.msg("操作失败");
                }
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            }
        }
    });
}