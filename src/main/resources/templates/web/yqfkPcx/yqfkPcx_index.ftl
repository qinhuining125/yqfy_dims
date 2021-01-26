<#include "/common/head.ftl"/>
<#include "/common/cssfile_list.ftl"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content row">
    <div class="col-sm-12">
        <div class="ibox">
            <div class="ibox-title">
                <h5>查询</h5>
                <div class="ibox-tools"><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></div>
            </div>
            <div class="ibox-content">
                <form class="form-horizontal" id="frm-search-yqfkRegister">
                    <div class="row">
                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">区域级别</label>
                                <div class="col-sm-7">
                                    <select name="plevel" class="form-control">
                                        <option value="">全部</option>
                                        <option value="1">1级</option>
                                        <option value="2">2级</option>
                                        <option value="3">3级</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">风险级</label>
                                <div class="col-sm-7">
                                    <select name="valid" class="form-control">
                                        <option value="">全部</option>
                                        <option value="1">低风险</option>
                                        <option value="2">中风险</option>
                                        <option value="3">高风险</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">区域名称:</label>
                                <div class="col-sm-7">
                                    <input type="text" name="pname" class="form-control" autocomplete="off">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <button id="btn-search-yqfkRegister" class="btn btn-primary"><i class="fa fa-search"></i> 查询
                        </button>
                        <button type="reset" class="btn"><i class="fa fa-minus"></i> 重置</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="table-responsive ibox-content">
            <div class="clearfix" style="margin-bottom:10px;">
                <#--                <div class="pull-left">-->
                <#--                    <button type="button" class="btn btn-primary" id="btn-add-taskInfo"><i-->
                <#--                                class="fa fa-plus"></i> 新增-->
                <#--                    </button>-->
                <#--                    <button type="button" class="btn btn-danger" id="btn-del-taskInfo"><i-->
                <#--                                class="fa fa-remove"></i> 删除-->
                <#--                    </button>-->
                <#--                </div>-->
                <div class="grid-o pull-right" id="bootgrid-toolbar"></div>
            </div>
            <table id="table-yqfkRegister"
                   class="table table-condensed table-hover table-striped table-bordered no-margins">
                <thead>
                <tr>
                    <th data-column-id="id" data-width="60px" data-formatter="no" data-sortable="false"
                        data-identifier="true">序号
                    </th>
                    <th data-column-id="pcode" data-order="desc" data-visible="true" data-sortable="true">
                        编码
                    </th>
                    <th data-column-id="pname" data-order="desc" data-visible="true" data-sortable="true">
                        名称
                    </th>
                    <th data-column-id="parent" data-order="desc" data-visible="true" data-sortable="true">
                        上级编码
                    </th>
                    <th data-column-id="plevel" data-order="desc" data-visible="true" data-sortable="true">
                        级别
                    </th>
                    <th data-column-id="valid" data-order="desc" data-visible="true" data-sortable="true" data-formatter="fun_valid">
                        风险级别
                    </th>


                    <#--                    <th data-field="valid" data-sortable="true" data-formatter="displaycolor"><span>状态</span></th></span>-->

                    <th data-column-id="link" data-width="180px" data-formatter="commands" data-custom="true"
                        data-sortable="false">操作
                    </th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script>
    function displaycolor(value, row, index) {
        var a = "";
        if (value == "0") {
            var a = '<span style="color:#00ff00">' + value + '</span>';
        } else if (value == "1") {
            var a = '<span style="color:#eae242">' + value + '</span>';
        } else if (value == "2") {
            var a = '<span style="color:#FF0000">' + value + '</span>';
        } else {
            var a = '<span>' + value + '</span>';
        }
        return a;
    }
</script>
<#include "/common/scriptfile.ftl"/>
<#include "/common/scriptfile_list.ftl"/>
<script src="${global.staticPath!}static/plugins/laydate/laydate.js"></script>
<script src="${global.staticPath!}/static/utils/handlebars-tool.js"></script>
<script id="template-yqfkRegister" type="text/x-handlebars-template">
    <td data-id="{{pcode}}">
        <button class="btn btn-primary btn-xs o-ch ops-view"><i class="fa fa-eye"></i> 查看</button>
        <button class="btn btn-warning btn-xs o-c ops-edit"><i class="fa fa-edit"></i> 修改</button>

    </td>
</script>
<script id="template-province" type="text/x-handlebars-template">
    <option value="">--请选择--</option>
    {{#each this}}
    <option value="{{id}}-{{pcode}}">{{pname}}</option>
    {{/each}}
</script>
<script id="template-city" type="text/x-handlebars-template">
    <option value="">--请选择--</option>
    {{#each this}}
    <option value="{{id}}-{{pcode}}">{{pname}}</option>
    {{/each}}a
</script>
<script id="template-view-yqfkRegister" type="text/x-handlebars-template">
    <div class="form-horizontal" style="margin: 20px;">
        <div class="form-group">
            <label class="col-xs-4 control-label">编码：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{pcode}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">名称：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{pname}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">上级编码：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{parent}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">级别：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{plevel}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">风险级：</label>
            <div class="col-xs-8">
                <div class="form-control-static">
                    {{#ifEqual valid '0'}}无{{/ifEqual}}
                    {{#ifEqual valid '1'}}低{{/ifEqual}}
                    {{#ifEqual valid "2"}}中{{/ifEqual}}
                    {{#ifEqual valid "3"}}高{{/ifEqual}}
                    {{valid}}
                </div>
            </div>
        </div>
    </div>
</script>
<script>
    laydate.render({
        "elem": "#timeRange",
        "range": "至",
        "format": "yyyy-MM-dd",
        "trigger": "click",
        "done": function (value, startDate, endDate) {
            if (value) {
                const array = value.split("至");
                $("#startTime").val(array[0]);
                $("#endTime").val(array[1]);
            } else {
                $("#startTime").val("");
                $("#endTime").val("");
            }
        }
    });

    function searchP(obj) {
        const provinceArray = $(obj).val().replace(/\s*/g, "").split("-")
        const province = provinceArray[0]
        const beforeReturnPbm = provinceArray[1]
        $("#beforeReturnPbm").val(beforeReturnPbm);
        if (province == '') {
            return false;
        }
        $.ajax({
            "type": "GET",
            "cache": false,
            "url": "/a/yqfkRegister/listS.json",
            "data": {
                "pcode": province
            },
            "dataType": "json",
            "success": function (data) {
                const template = Handlebars.compile($("#template-province").html());
                $("#city").html(template(data.result));
            }
        });
    }

    function choseS(obj) {
        const cityArray = $(obj).val().replace(/\s*/g, "").split("-")
        const city = cityArray[0]
        const beforeReturnCbm = cityArray[1]
        $("#beforeReturnCbm").val(beforeReturnCbm);
        if (city == '') {
            return false;
        }
        $.ajax({
            "type": "GET",
            "cache": false,
            "url": "/a/yqfkRegister/listX.json",
            "data": {
                "pcode": city
            },
            "dataType": "json",
            "success": function (data) {
                const template = Handlebars.compile($("#template-city").html());
                $("#county").html(template(data.result));
            }
        });
    }

    function choseX(obj) {
        const countyArray = $(obj).val().replace(/\s*/g, "").split("-")
        const county = countyArray[0]
        const beforeReturnXbm = countyArray[1]
        $("#beforeReturnXbm").val(beforeReturnXbm);
        // if (beforeReturnXbm == '') {
        //     beforeReturnXbm = $("#county").val();
        // }

    }

    function searchVillage(obj) {
        const afterReturnZhbm = $(obj).val();
        $("#afterReturnZhbm").val(afterReturnZhbm);
        if (afterReturnZhbm == '') {
            return false;
        }
        $.ajax({
            "type": "GET",
            "cache": false,
            "url": "/a/village/list.json",
            "data": {
                "areaCode": afterReturnZhbm
            },
            "dataType": "json",
            "success": function (data) {
                const template = Handlebars.compile($("#template-villages").html());
                $("#valliage").html(template(data.result));
            }
        });
    }

    function choseArea(obj) {

        var afterReturnCubm = $(obj).val();
        if (afterReturnCubm == '') {
            afterReturnCubm = $("#township").val();
        }
        $("#afterReturnCubm").val(afterReturnCubm);
    }

    $(document).ready(function () {
        var HBR_TD = Handlebars.compile($("#template-yqfkRegister").html());
        var pageParams = {
            <#-- 模块id，最重要的属性 -->
            "moduleName": "yqfkRegister",
            <#-- 弹窗区域，都是100%，是不显示最大化按钮的 -->
            "area": ["60%", "500px"],
            "url": {
                "list": "${mapping!}/getDataList.json",
                "delete": "${mapping!}/deleteData.json",
                <#-- 会自动把id参数写入 -->
                "view": "${mapping!}/getDataInfo.json",
                "edit": function (id) {
                    return "${mapping!}/edit.html?id=" + id;
                }
            },
            <#-- 这里列表的行渲染方法，配置data-formatter="..." -->
            "formatters": {
                "no": function (column, row) {
                    return row.idx;
                },
                "commands": function (column, row) {
                    return HBR_TD(row);
                },
                "fun_state": function (rolumn, row) {
                    var state = row.state;
                    if (state == 0) {
                        return "未处理";
                    } else if (state == 1 || state == 3) {
                        return "已受理";
                    } else if (state == 2) {
                        return "已办结";
                    }
                },
                "fun_valid": function (column, row) {
                    var valid = row.valid;
                    if (valid === "0") {
                        return "无";
                    } else if (valid === "1") {
                        return "低";
                    } else if (valid === "2") {
                        return "中";
                    } else if (valid === "3") {
                        return "高";
                    }
                },
                "fun_date": function (rolumn, row) {
                    return new Date(row.createTime).Format("yyyy-MM-dd hh:mm:ss");
                }
            },
            <#-- 列表自定义提交数据的附加参数 -->
            "otherParams": {}
        };
        <#-- view的本页渲染的模板 -->
        var HBR_VIEW = Handlebars.compile($("#template-view-yqfkRegister").html());
        <#-- 列表ajax加载，绑定删除和编辑之外的其他按钮 -->

        function bindRowEvent() {
            <#-- 绑定查看，本例是用的本页渲染，也可以使用单独的页面 -->
            <#-- CommonGetData定义在main-list.js -->
            $("#table-yqfkRegister .ops-view").on("click", function () {
                var id = $(this).parent().attr("data-id");
                CommonGetData(pageParams.url.view, {
                    "id": id
                }, function (result) {
                    openLayer({
                        "title": "查看",
                        "btn": ["关闭"],
                        "content": HBR_VIEW(result)
                    });
                    if ($.isFunction(top.clearIframeFocus)) {
                        top.clearIframeFocus();
                    }
                });
            });
        }

        <#-- 通用功能的入口 -->
        CommonListFun(pageParams, bindRowEvent);
    });
</script>
</body>
</html>
