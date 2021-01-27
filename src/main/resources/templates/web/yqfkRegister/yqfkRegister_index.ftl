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
                                <label class="col-sm-4 control-label">登记时间段：</label>
                                <div class="col-sm-7">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="timeRange"
                                               readonly="readonly" placeholder="日期"/>
                                    </div>
                                </div>
                                <input type="hidden" id="startTime" name="startTime">
                                <input type="hidden" id="endTime" name="endTime">
                                <input type="hidden" id="beforeReturnPbm" name="beforeReturnPbm">
                                <input type="hidden" id="beforeReturnCbm" name="beforeReturnCbm">
                                <input type="hidden" id="beforeReturnXbm" name="beforeReturnXbm">
                                <input type="hidden" id="afterReturnZhbm" name="afterReturnZhbm">
                                <input type="hidden" id="afterReturnCubm" name="afterReturnCubm">
                                <input type="hidden" id="createBelZhbm" name="createBelZhbm">
                                <input type="hidden" id="createBelCubm" name="createBelCubm">
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">姓名:</label>
                                <div class="col-sm-7">
                                    <input type="text" id="name" name="name" class="form-control" autocomplete="off">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">风险等级</label>
                                <div class="col-sm-7">
                                    <select name="riskLevel" class="form-control" id="riskLevel">
                                        <option value="">全部</option>
                                        <#--                                        <option value="0">无</option>-->
                                        <option value="1">低风险</option>
                                        <option value="2">中风险</option>
                                        <option value="3">高风险</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">是否返乡</label>
                                <div class="col-sm-7">
                                    <select name="returnState" class="form-control" id="returnState">
                                        <option value="">全部</option>
                                        <option value="拟返乡">拟返乡</option>
                                        <option value="已返乡">已返乡</option>
                                        <option value="不返乡">不返乡</option>
                                        <option value="已上报社区">已上报社区</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">网格员所属乡镇:</label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="belTownship" name="" onchange="belSearchVillage(this);">
                                        <option value="">全部</option>
                                        <#list areaListF as obj>
                                            <option value="${(obj.areaCode!)}">${(obj.areaName)!}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">村:</label>
                                <div class="col-sm-7">
                                    <select id="belVlliage" class="form-control" name="" onchange="bleChoseArea(this);">
                                        <option value="">全部</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">网格员:</label>
                                <div class="col-sm-7">
                                    <input type="text" id="createAccount" name="createAccount" class="form-control" autocomplete="off">
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">返乡前所在省:</label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="province" name=""
                                            onchange="searchP(this);">
                                        <option value="">全部</option>
                                        <#list areaList as obj>
                                            <option value="${(obj.id)}-${(obj.pcode)}">${(obj.pname)!}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">市:</label>
                                <div class="col-sm-7">
                                    <select id="city" class="form-control" name=""
                                            onchange="choseS(this);">
                                        <option value="">全部</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">县:</label>
                                <div class="col-sm-7">
                                    <select id="county" class="form-control" name=""
                                            onchange="choseX(this);">
                                        <option value="">全部</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">返乡后所在乡镇:</label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="township" name="" onchange="searchVillage(this);">
                                        <option value="">全部</option>
                                        <#list areaListF as obj>
                                            <option value="${(obj.areaCode!)}">${(obj.areaName)!}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">村:</label>
                                <div class="col-sm-7">
                                    <select id="valliage" class="form-control" name="" onchange="choseArea(this);">
                                        <option value="">全部</option>
                                    </select>
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
                <div class="pull-left">
                    <#--<button type="button" class="btn btn-primary" id="btn-add-taskInfo"><i
                                class="fa fa-plus"></i> 新增
                    </button>-->
                    <button type="button" class="btn btn-warning" onclick="exportData(this)"><i
                                class="fa fa-file-excel-o mr-2"></i>&nbsp;导出
                    </button>
                </div>
                <div class="grid-o pull-right" id="bootgrid-toolbar"></div>
            </div>
            <table id="table-yqfkRegister"
                   class="table table-condensed table-hover table-striped table-bordered no-margins">
                <thead>
                <tr>
                    <th data-column-id="id" data-width="60px" data-formatter="no" data-sortable="false"
                        data-identifier="true">序号
                    </th>
                    <th data-column-id="name" data-order="desc" data-visible="true" data-sortable="true">
                        姓名
                    </th>
                    <th data-column-id="sex" data-order="desc" data-visible="true" data-sortable="true">
                        性别
                    </th>
                    <th data-column-id="age" data-order="desc" data-visible="true" data-sortable="true">
                        年龄
                    </th>
                    <th data-column-id="returnState" data-order="desc" data-visible="true" data-sortable="true">
                        是否返乡
                    </th>
                    <th data-column-id="phone" data-order="desc" data-visible="true" data-sortable="true">
                        手机号
                    </th>
                    <th data-column-id="link" data-width="180px" data-formatter="commands" data-custom="true"
                        data-sortable="false">操作
                    </th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<#include "/common/scriptfile.ftl"/>
<#include "/common/scriptfile_list.ftl"/>
<script src="${global.staticPath!}static/plugins/laydate/laydate.js"></script>
<script src="${global.staticPath!}/static/utils/handlebars-tool.js"></script>
<script src="${global.staticPath!}/static/utils/layui.js"></script>
<script src="${global.staticPath!}/static/utils/excel.js"></script>
<script id="template-yqfkRegister" type="text/x-handlebars-template">
    <td data-id="{{id}}">
        <button class="btn btn-primary btn-xs o-ch ops-view"><i class="fa fa-eye"></i> 查看</button>
        <#--<button class="btn btn-warning btn-xs o-c ops-edit"><i class="fa fa-edit"></i> 修改</button>-->
        {{#fun_equals_one rowLocked}}
        <button class="btn btn-xs o-d" disabled="disabled"><i class="fa fa-close"></i> 删除</button>
        {{else}}
        <button class="btn btn-danger btn-xs o-d ops-del"><i class="fa fa-close"></i> 删除</button>
        {{/fun_equals_one}}
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
    {{/each}}
</script>
<script id="template-villages" type="text/x-handlebars-template">
    <option value="">--请选择--</option>
    {{#each this}}
    <option value="{{areaCode}}">{{areaName}}</option>
    {{/each}}
</script>
<script id="template-belvillages" type="text/x-handlebars-template">
    <option value="">--请选择--</option>
    {{#each this}}
    <option value="{{areaCode}}">{{areaName}}</option>
    {{/each}}
</script>
<script id="template-view-yqfkRegister" type="text/x-handlebars-template">
    <div class="form-horizontal" style="margin: 20px;">
        <div class="form-group">
            <label class="col-xs-4 control-label">姓名：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{name}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">性别：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{sex}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">年龄：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{age}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">生日：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate birthday "yyyy-MM-dd "}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">身份证：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{card}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">户籍所在地：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{hj}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">是否常驻：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{sfcz}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">与户主关系：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{relation}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">联系电话：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{phone}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">工作单位：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{workSchool}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">行业：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{industray}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">是否返乡：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{returnState}}</div>
            </div>
        </div>


        <div class="form-group">
            <label class="col-xs-4 control-label">返乡日期：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate returnTime "yyyy-MM-dd hh:mm:ss"}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">返乡方式：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{returnWay}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">返乡车牌号：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{returnCarnum}}</div>
            </div>
        </div>


        <div class="form-group">
            <label class="col-xs-4 control-label">拟返乡日期：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate expReturnTime "yyyy-MM-dd hh:mm:ss"}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">拟返乡方式：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{expReturnWay}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">拟返乡车牌号：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{expReturnCarnum}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">返乡前详细地址：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{beforeReturnAddress}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">返乡后详细地址：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{afterReturnAddress}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">是否本地租户：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{localState}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">核酸检测日期：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate natTime "yyyy-MM-dd hh:mm:ss"}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">核酸检测结果：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{natResult}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">是否接触：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{touchState}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">是否居家隔离：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{isLateStete}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">隔离开始日期：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate isLateStateTime "yyyy-MM-dd hh:mm:ss"}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">当前健康状态是否异常：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{healthState}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">异常说明：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{remark}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">录入信息的网格员账号：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{createAccount}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">创建时间：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate createTime "yyyy-MM-dd hh:mm:ss"}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">更新时间：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate updateTime "yyyy-MM-dd hh:mm:ss"}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">更改信息的网格员账号：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{updateAccount}}</div>
            </div>
        </div>
        <#--        {{#each places}}-->
        <#--        {{name}}-{{Ccode}}-{{Xcode}}-->
        <#--        {{/each}}-->

        <#--        {{#each places}}-->
        <#--        {{name}}-{{Ccode}}-{{Xcode}}-->
        <#--        {{/each}}-->
        <table class="table table-condensed table-hover table-striped table-bordered no-margins">
            <thead>
            <tr>
                <td>近14天去过的地区</td>
                <td>创建时间</td>
            </tr>
            </thead>
            <tbody>
            {{#each places}}
            <tr>
                <td>
                    <div class="form-control-static">
                        {{name}}
                    </div>
                </td>
                <td>
                    <div class="form-control-static">{{formatDate createTime "yyyy-MM-dd hh:mm:ss"}}</div>
                </td>
            </tr>
            {{/each}}
            </tbody>
        </table>
    </div>
</script>
<script>

    function exportData(){
        var excel=LAY_EXCEL;
        //查询参数这里
        var startTime = $("#startTime").val();
        var  endTime= $("#endTime").val();
        var beforeReturnPbm = $("#beforeReturnPbm").val();
        var beforeReturnCbm = $("#beforeReturnCbm").val();
        var beforeReturnXbm = $("#beforeReturnXbm").val();
        var afterReturnZhbm = $("#afterReturnZhbm").val();
        var afterReturnCubm = $("#afterReturnCubm").val();
        var riskLevel = $("#riskLevel").val();
        var createAccount = $("#createAccount").val();
        var returnState = $("#returnState").val();
        var Param1 = JSON.stringify({ "startTime": startTime, "endTime": endTime, "beforeReturnPbm": beforeReturnPbm ,
            "beforeReturnCbm": beforeReturnCbm, "beforeReturnXbm": beforeReturnXbm, "afterReturnZhbm": afterReturnZhbm,
            "afterReturnCubm": afterReturnCubm, "riskLevel": riskLevel, "createAccount": createAccount,"returnState":returnState}); //必须转换为Json对象

        $.ajax({
            "type": "post",
            "cache": false,
            "url": "/a/yqfkRegister/getDataList1.json",
            "contentType": "application/json",//必须要有
            "data": Param1,
            "success": function (res) {
            console.log(res.result);// [{name: 'wang', age: 18, sex: '男'}, {name: 'layui', age: 3, sex: '女'}]
            // 1. 数组头部新增表头
            res.result.unshift({id: '主键ID',name: '姓名', sex: '性别',age: '年龄',card: '身份证',
                hj: '户籍',sfcz: '是否常住',relation: '与户主关系',
                phone: '联系电话',workSchool: '工作单位',industray: '行业', returnState: '是否返乡',returnTime: '返乡日期',
                returnWay: '返乡方式', returnCarnum: '返乡车牌号',expReturnTime: '拟返乡日期',expReturnWay: '拟返乡方式', expReturnCarnum: '拟返乡车牌号',
                beforeReturnAddress: '返乡前住址', afterReturnAddress: '返乡后住址',localState: '是否本地租户',natTime: '核酸检测日期', natResult: '核酸检测结果',
                touchState: '否接触过新冠确诊病人或疑似病人', isLateStete: '是否居家隔离',isLateStateTime: '隔离开始日期',healthState: '当前健康状态是否异常', remark: '异常说明',
                createAccount: '录入者账号', createTimeUnix: '创建时间',riskLevel: '风险等级',placeNames:'14天去过的地方'});
            // 2. 如果需要调整顺序，请执行梳理函数
            var data = excel.filterExportData(res.result, [
                'id',
                'name',
                'sex',
                'age',
                'card',
                'hj',
                'sfcz',
                'relation',
                'phone',
                'workSchool',
                'industray',
                'returnState',
                'returnTime',
                'returnWay',
                'returnCarnum',
                'expReturnTime',
                'expReturnWay',
                'expReturnCarnum',
                'beforeReturnAddress',
                'afterReturnAddress',
                'localState',
                'natTime',
                'natResult',
                'touchState',
                'isLateStete',
                'isLateStateTime',
                'healthState',
                'remark',
                'createAccount',
                'createTimeUnix',
                'riskLevel',
                'placeNames',
            ]);
            // 3. 执行导出函数，系统会弹出弹框
            excel.exportExcel({
                sheet1: data
            }, '导出登记表数据.xlsx', 'xlsx');
        }
    });
    }

   /* layui.use(['jquery', 'excel', 'layer'], function() {
        var $ = layui.jquery;
        var excel = layui.excel;
        $.ajax({
            url: '/a/yqfkRegister/getDataList1.json', //导出数据的url
            dataType: 'json',
            success: function(res) {
                // 假如返回的 res.data 是需要导出的列表数据
                console.log(res.result);// [{name: 'wang', age: 18, sex: '男'}, {name: 'layui', age: 3, sex: '女'}]
                // 1. 数组头部新增表头
                res.result.unshift({name: '用户名',sex: '男', age: '年龄'});
                // 2. 如果需要调整顺序，请执行梳理函数
                var data = excel.filterExportData(res.result, [
                    'name',
                    'sex',
                    'age',
                ]);
                // 3. 执行导出函数，系统会弹出弹框
                excel.exportExcel({
                    sheet1: data
                }, '导出接口数据.xlsx', 'xlsx');
            }
        });
    });

*/



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

    function belSearchVillage(obj) {
        const createBelZhbm = $(obj).val();
        $("#createBelZhbm").val(createBelZhbm);
        if (createBelZhbm == '') {
            return false;
        }

        $.ajax({
            "type": "GET",
            "cache": false,
            "url": "/a/village/list.json",
            "data": {
                "areaCode": createBelZhbm
            },
            "dataType": "json",
            "success": function (data) {
                const template = Handlebars.compile($("#template-belvillages").html());
                $("#belVlliage").html(template(data.result));
            }
        });
    }

    function bleChoseArea(obj) {

        var createBelCubm = $(obj).val();
        if (createBelCubm == '') {
            createBelCubm = $("#belTownship").val();
        }
        $("#createBelCubm").val(createBelCubm);
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
