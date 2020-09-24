var vm = new Vue({
    el: '#app',
    data: {
        tableData: [],
        total: 50,
        page_size: 5,
        current_page: 1
    },
    methods: {
        changeTable (delFlag, index) {
           var flag = delFlag==0? 1:0;
           var _this = this;
            $.ajax({
                url: context + 'site/deleteSite?id=' +  this.tableData[index].id+"&delFlag="+flag,
                type: 'GET',
                success: function (res) {
                    if (res.code === 200){
                        if (res.data.code === 200){
                            layer.msg("操作成功");
                            _this.tableData[index].delFlag = flag;
                            // vm.getSiteList();
                        } else {
                            layer.msg("操作失败");
                        }
                    }
                }
            });
        },
        addSite:function() {
            layer.open({
                type: 2,
                title: '新增',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: context + 'site/add',
                end: function () {
                    vm.getSiteList();
                }
            });
        },
        handleEdit: function(row) {
            console.log(row);
            layer.open({
                type: 2,
                title: '修改',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: context + 'site/update?site='+row.site+"&siteName="+row.siteName+"&siteAddr="+row.siteAddr+"&id="+row.id, // iframe的url
                end: function () {
                    vm.getSiteList();
                }
            });
        },
        handleSizeChange: function (val) {
            vm.page_size = val;
            this.getSiteList();
        },
        handleCurrentChange: function (val) {
            vm.current_page = val;
            this.getSiteList();
        },

        getSiteList: function () {
            $.ajax({
                url: context + 'site/getSiteList?page=' + this.current_page + '&page_size=' + this.page_size,
                type: 'GET',
                success: function (res) {
                    vm.tableData = res.data.siteList;
                    vm.total = res.data.total;
                    vm.page_size = res.data.page_size;
                    vm.current_page = res.data.page;
                }
            });
        }
    },
    mounted: function () {
        this.getSiteList();
    }
});