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
                <form class="form-horizontal" id="frm-search-yqfkJZRegister">
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
                                <input type="hidden" id="createBelZhbm" name="createBelZhbm">
                                <input type="hidden" id="createBelCubm" name="createBelCubm">

                                <input type="hidden" id="nowZhbm" name="nowZhbm">
                                <input type="hidden" id="nowCubm" name="nowCubm">

                                <input type="hidden" id="zzDWType1" name="zzDWType1">
                                <input type="hidden" id="zzDWType2" name="zzDWType2">
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">姓名:</label>
                                <div class="col-sm-7">
                                    <input type="text" id="name" name="name" class="form-control" autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">接种状态</label>
                                <div class="col-sm-7">
                                    <select name="jieZhState" class="form-control" id="jieZhState">
                                        <option value="">全部</option>
                                        <option value="接种进行中">接种进行中</option>
                                        <option value="接种已完成">接种已完成</option>
                                        <option value="未接种">未接种</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">网格员所属乡镇:</label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="belTownship" name=""
                                            onchange="belSearchVillage(this);">
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
                                    <input type="text" id="createAccount" name="createAccount" class="form-control"
                                           autocomplete="off">
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">接种疫苗类型</label>
                                <div class="col-sm-7">
                                    <select name="vaccineType" class="form-control" id="vaccineType">
                                        <option value="">全部</option>
                                        <option value="灭活两针次疫苗">灭活两针次疫苗</option>
                                        <option value="重组三针次疫苗">重组三针次疫苗</option>
                                        <option value="一针">一针</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">政治面貌</label>
                                <div class="col-sm-7">
                                    <select name="zzMM" class="form-control" id="zzMM">
                                        <option value="">全部</option>
                                        <option value="中共党员">中共党员</option>
                                        <option value="群众">群众</option>
                                        <option value="其他">其他</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">居住类型</label>
                                <div class="col-sm-7">
                                    <select name="juzhuType" class="form-control" id="juzhuType">
                                        <option value="">全部</option>
                                        <option value="常住">常住</option>
                                        <option value="租户">租户</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label">不接种原因</label>
                                <div class="col-sm-7">
                                    <select name="noJieZhReasonType" class="form-control" id="noJieZhReasonType">
                                        <option value="">全部</option>
                                        <option value="禁忌症">禁忌症</option>
                                        <option value="到接种点后医生建议不接种">到接种点后医生建议不接种</option>
                                        <option value="不愿意接种">不愿意接种</option>
                                        <option value="正在隔离期">正在隔离期</option>
                                        <option value="准备接种">准备接种</option>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-4 control-label">禁忌原因</label>
                                <div class="col-sm-7">
                                    <select name="noJieZhReasonDetails" class="form-control" id="noJieZhReasonDetails">
                                        <option value="">全部</option>
                                        <option value="对疫苗的活性成分、任何一种非活性成分、生产工艺中使用的物质过敏者，或以前接种同类疫苗时出现过敏者">对疫苗的活性成分、任何一种非活性成分、生产工艺中使用的物质过敏者，或以前接种同类疫苗时出现过敏者</option>
                                        <option value="既往发生过疫苗严重过敏反应者（如急性过敏反应、血管神经性水肿、呼吸困难等）">既往发生过疫苗严重过敏反应者（如急性过敏反应、血管神经性水肿、呼吸困难等）</option>
                                        <option value="患有未控制的癫痫和其他严重神经系统疾病者（如横贯性脊髓炎、格林巴利综合症、脱髓鞘疾病等）">患有未控制的癫痫和其他严重神经系统疾病者（如横贯性脊髓炎、格林巴利综合症、脱髓鞘疾病等）</option>
                                        <option value="正在发热者，或患急性疾病，或慢性疾病的急性发作期，或未控制的严重慢性病患者">正在发热者，或患急性疾病，或慢性疾病的急性发作期，或未控制的严重慢性病患者</option>
                                        <option value="妊娠期妇女">妊娠期妇女</option>
                                        <option value="瘫痪在床">瘫痪在床</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">现居住乡镇:</label>
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

                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">工作单位大类:</label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="queryzzDWType1" name=""
                                            onchange="choseZZDW1(this);">
                                        <option value="">全部</option>
                                        <#list zzDWList as obj>
                                            <option value="${(obj.name!)}">${(obj.name)!}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">工作单位小类:</label>
                                <div class="col-sm-7">
                                    <select id="queryzzDWType2" class="form-control" name="" onchange="choseZZDW2(this);">
                                        <option value="">全部</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">工作单位详细:</label>
                                <div class="col-sm-7">
<#--                                    <select id="queryzzDWType3" class="form-control" name="" onchange="searchZZDW2(this);">-->
<#--                                        <option value="">全部</option>-->
<#--                                    </select>-->
                                    <input type="text" id="queryzzDWType2" name="zzDWType3" class="form-control"
                                           autocomplete="off">
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="text-center">
                        <button id="btn-search-yqfkJZRegister" class="btn btn-primary"><i class="fa fa-search"></i> 查询
                        </button>
                        <button type="reset" class="btn"><i class="fa fa-minus"></i> 重置</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="table-responsive ibox-content">
            <div class="clearfix" style="margin-bottom:10px;">
                <div class="pull-left">
                    <button type="button" class="btn btn-warning" onclick="exportData(this)"><i
                                class="fa fa-file-excel-o mr-2"></i>&nbsp;导出
                    </button>
                </div>
                <div class="grid-o pull-right" id="bootgrid-toolbar"></div>
            </div>
            <table id="table-yqfkJZRegister"
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
                    <th data-column-id="phone" data-order="desc" data-visible="true" data-sortable="true">
                        手机号
                    </th>
                    <th data-column-id="zzMM" data-order="desc" data-visible="true" data-sortable="true">
                        政治面貌
                    </th>
                    <th data-column-id="zzDWType1" data-order="desc" data-visible="true" data-sortable="true">
                        工作单位大类
                    </th>
                    <th data-column-id="zzDWType2" data-order="desc" data-visible="true" data-sortable="true">
                        工作单位详细
                    </th>
                    <th data-column-id="zzDWType3" data-order="desc" data-visible="true" data-sortable="true">
                        工作单位具体名称
                    </th>
                    <th data-column-id="vaccineType" data-order="desc" data-visible="true" data-sortable="true">
                        接种疫苗种类
                    </th>
                    <th data-column-id="jieZhState" data-order="desc" data-visible="true" data-sortable="true">
                        接种状态
                    </th>
                    <th data-column-id="noJieZhReasonType" data-order="desc" data-visible="true" data-sortable="true">
                        未接种原因
                    </th>
                    <th data-column-id="noJieZhReasonDetails" data-order="desc" data-visible="true" data-sortable="true">
                        禁忌原因
                    </th>
                    <th data-column-id="createAccount" data-order="desc" data-visible="true" data-sortable="true">
                        网格员名字
                    </th>
                    <th data-column-id="createTime" data-order="desc" data-visible="true" data-sortable="true"  data-formatter="fun_date">
                        登记时间
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
<script id="template-yqfkJZRegister" type="text/x-handlebars-template">
    <td data-id="{{id}}">
        <button class="btn btn-primary btn-xs o-ch ops-view"><i class="fa fa-eye"></i> 查看</button>
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
<script id="template-zzDWType2" type="text/x-handlebars-template">
    <option value="">--请选择--</option>
    {{#each this}}
    <option value="{{name}}">{{name}}</option>
    {{/each}}
</script>
<script id="template-view-yqfkJZRegister" type="text/x-handlebars-template">
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
            <label class="col-xs-4 control-label">身份证：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{card}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">生日：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate birthday "yyyy-MM-dd "}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">联系电话：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{phone}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">政治面貌：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{zzMM}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">工作单位大类：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{zzDWType1}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">工作单位详细：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{zzDWType2}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">工作单位具体名称：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{zzDWType3}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">接种疫苗种类：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{vaccineType}}</div>
            </div>
        </div>


        <div class="form-group">
            <label class="col-xs-4 control-label">接种状态：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{jieZhState}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">未接种原因：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{noJieZhReasonType}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">禁忌原因：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{noJieZhReasonDetails}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">现住址：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{nowAddress}}</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-4 control-label">居住类型：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{juzhuType}}</div>
            </div>
        </div>



        <div class="form-group">
            <label class="col-xs-4 control-label">第1针接种日期：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate dateFirst "yyyy-MM-dd hh:mm:ss"}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">第1针接种地点：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{addressFirst}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">第2针接种日期：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate dateSecond "yyyy-MM-dd hh:mm:ss"}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">第2针接种地点：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{addressSecond}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">第3针接种日期：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{formatDate dateThird "yyyy-MM-dd hh:mm:ss"}}</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-4 control-label">第3针接种地点：</label>
            <div class="col-xs-8">
                <div class="form-control-static">{{addressThird}}</div>
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

    </div>
</script>
<script>

    function exportData() {
        var excel = LAY_EXCEL;
        //查询参数这里
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var name = $("#name").val();
        var sex = $("#sex").val();
        var age = $("#age").val();
        var card = $("#card").val();
        var phone = $("#phone").val();
        var nowAddress = $("#nowAddress").val();
        var zzMM = $("#zzMM").val();
        var zzDWType1 = $("#zzDWType1").val();
        var zzDWType2 = $("#zzDWType2").val();
        var zzDWType3 = $("#zzDWType3").val();
        var juzhuType = $("#juzhuType").val();
        var jieZhType = $("#jieZhType").val();
        var vaccineType = $("#vaccineType").val();
        var dateFirst = $("#dateFirst").val();
        var addressFirst = $("#addressFirst").val();
        var dateSecond = $("#dateSecond").val();
        var addressSecond = $("#addressSecond").val();
        var dateThird = $("#dateThird").val();
        var addressThird = $("#addressThird").val();
        var noJieZhReasonType = $("#noJieZhReasonType").val();
        var noJieZhReasonDetails = $("#noJieZhReasonDetails").val();
        var jieZhState = $("#jieZhState").val();
        var createAccount = $("#createAccount").val();
        var createBelZhbm = $("#createBelZhbm").val();
        var createBelCubm = $("#createBelCubm").val();



        var Param1 = JSON.stringify({
            "startTime": startTime,
            "endTime": endTime,
            "name": name,
            "sex": sex,
            "age": age,
            "card": card,
            "phone": phone,
            "nowAddress": nowAddress,
            "zzMM": zzMM,
            "zzDWType1": zzDWType1,
            "zzDWType2": zzDWType2,
            "zzDWType3": zzDWType3,
            "juzhuType": juzhuType,
            "jieZhType": jieZhType,
            "vaccineType": vaccineType,
            "dateFirst": dateFirst,
            "addressFirst": addressFirst,
            "dateSecond": dateSecond,
            "addressSecond": addressSecond,
            "dateThird": dateThird,
            "addressThird": addressThird,
            "noJieZhReasonType": noJieZhReasonType,
            "noJieZhReasonDetails": noJieZhReasonDetails,
            "jieZhState": jieZhState,
            "createAccount": createAccount,
            "createBelZhbm": createBelZhbm,
            "createBelCubm": createBelCubm,

        }); //必须转换为Json对象


        $.ajax({
            "type": "post",
            "cache": false,
            "url": "/a/yqfkJZRegister/getDataList1.json",
            "contentType": "application/json",//必须要有
            "data": Param1,
            "success": function (res) {
                // 1. 数组头部新增表头
                res.result.unshift({
                    id: '主键ID',
                    name: '姓名',
                    sex: '性别',
                    age: '年龄',
                    card: '身份证',
                    phone: '联系电话',
                    nowAddress: '现住址',
                    zzMM: '政治面貌',
                    zzDWType1: '工作单位大类',
                    zzDWType2: '工作单位详细',
                    zzDWType3: '工作单位具体名称',
                    juzhuType: '居住类型',
                    jieZhType: '接种类型',
                    vaccineType: '接种疫苗种类',
                    dateFirst: '第1针接种日期',
                    addressFirst: '第1针接种地点',
                    dateSecond: '第2针接种日期',
                    addressSecond: '第2针接种地点',
                    dateThird: '第3针接种日期',
                    addressThird: '第3针接种地点',
                    noJieZhReasonType: '未接种原因',
                    noJieZhReasonDetails: '禁忌原因',
                    jieZhState: '接种状态',
                    createAccount: '录入者账号',
                    createTimeUnix: '创建时间',

                });
                // 2. 如果需要调整顺序，请执行梳理函数
                var data = excel.filterExportData(res.result, [
                    'id',
                    'name',
                    'sex',
                    'age',
                    'card',
                    'phone',
                    'nowAddress',
                    'zzMM',
                    'zzDWType1',
                    'zzDWType2',
                    'zzDWType3',
                    'juzhuType',
                    'jieZhType',
                    'vaccineType',
                    'dateFirst',
                    'addressFirst',
                    'dateSecond',
                    'addressSecond',
                    'dateThird',
                    'addressThird',
                    'noJieZhReasonType',
                    'noJieZhReasonDetails',
                    'jieZhState',
                    'createAccount',
                    'createTimeUnix',

                ]);
                // 3. 执行导出函数，系统会弹出弹框
                excel.exportExcel({
                    sheet1: data
                }, '导出疫苗接种登记表数据.xlsx', 'xlsx');
            }
        });
    }



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


    function searchVillage(obj) {
        const nowZhbm = $(obj).val();
        $("#nowZhbm").val(nowZhbm);
        if (nowZhbm == '') {
            return false;
        }
        $.ajax({
            "type": "GET",
            "cache": false,
            "url": "/a/village/list.json",
            "data": {
                "areaCode": nowZhbm
            },
            "dataType": "json",
            "success": function (data) {
                const template = Handlebars.compile($("#template-villages").html());
                $("#valliage").html(template(data.result));
            }
        });
    }

    function choseArea(obj) {

        var nowCubm = $(obj).val();
        if (nowCubm == '') {
            nowCubm = $("#township").val();
        }
        $("#nowCubm").val(nowCubm);
    }



    function choseZZDW1(obj) {
        const zzDWType1 = $(obj).val();
        $("#zzDWType1").val(zzDWType1);
        if (zzDWType1 == '') {
            return false;
        }
        console.log(zzDWType1)
        $.ajax({
            "type": "GET",
            "cache": false,
            "url": "/a/yqfkJZRegister/listZZDW2.json",
            "data": {
                "zzDWType1": zzDWType1
            },
            "dataType": "json",
            "success": function (data) {
                console.log(data)
                const template = Handlebars.compile($("#template-zzDWType2").html());
                $("#queryzzDWType2").html(template(data.result));
            }
        });
    }
    function choseZZDW2(obj) {
        const zzDWType2 = $(obj).val();
        $("#zzDWType2").val(zzDWType2);
        if (zzDWType2 == '') {
            return false;
        }
    }

    function searchZZDW2(obj) {
        const zzDWType1 = $(obj).val();
        $("#zzDWType1").val(zzDWType1);
        if (zzDWType1 == '') {
            return false;
        }
        console.log(zzDWType1)
        $.ajax({
            "type": "GET",
            "cache": false,
            "url": "/a/yqfkJZRegister/listZZDW2.json",
            "data": {
                "name": zzDWType1
            },
            "dataType": "json",
            "success": function (data) {
                const template = Handlebars.compile($("#template-zzDWType2").html());
                $("#queryzzDWType2").html(template(data.result));
            }
        });
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
        var HBR_TD = Handlebars.compile($("#template-yqfkJZRegister").html());
        var pageParams = {
            <#-- 模块id，最重要的属性 -->
            "moduleName": "yqfkJZRegister",
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
                "fun_date": function (rolumn, row) {
                    return new Date(row.createTime).Format("yyyy-MM-dd hh:mm");
                }
            },
            <#-- 列表自定义提交数据的附加参数 -->
            "otherParams": {}
        };
        <#-- view的本页渲染的模板 -->
        var HBR_VIEW = Handlebars.compile($("#template-view-yqfkJZRegister").html());
        <#-- 列表ajax加载，绑定删除和编辑之外的其他按钮 -->

        function bindRowEvent() {
            <#-- 绑定查看，本例是用的本页渲染，也可以使用单独的页面 -->
            <#-- CommonGetData定义在main-list.js -->
            $("#table-yqfkJZRegister .ops-view").on("click", function () {
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
