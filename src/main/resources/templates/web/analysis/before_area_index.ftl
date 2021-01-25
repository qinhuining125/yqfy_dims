<#include "/common/head.ftl"/>
<#include "/common/cssfile_list.ftl"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content row">
    <div class="col-sm-12">
        <div class="ibox">
            <div class="ibox-content">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-xs-6 col-sm-4 col-lg-4">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><span class="text-danger">*</span>时间段：</label>
                                <div class="col-sm-8">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="timeRange" readonly="readonly"
                                               placeholder="统计日期"/>
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span></div>
                                </div>
                                <input type="hidden" id="startTime" name="startTime">
                                <input type="hidden" id="endTime" name="endTime">
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">乡镇</label>
                                <div class="col-sm-7">
                                    <select name="areaCode" id="areaCode" class="form-control">
                                        <option value="">全部</option>
                                        <#list areaList as obj>
                                            <option value="${(obj.areaCode)!}">${(obj.areaName)!}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="beforeAreaCode" name="beforeAreaCode">
                        <div class="col-xs-6 col-sm-4 col-lg-3">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">返乡前所在省:</label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="province" name=""
                                            onchange="searchP(this);">
                                        <option value="">全部</option>
                                        <#list areaList1 as obj>
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
                            <#--              <div class="form-group">-->
                            <#--                <label class="col-sm-4 control-label">县:</label>-->
                            <#--                <div class="col-sm-7">-->
                            <#--                  <select id="county" class="form-control" name=""-->
                            <#--                          onchange="choseX(this);">-->
                            <#--                    <option value="">全部</option>-->
                            <#--                  </select>-->
                            <#--                </div>-->
                            <#--              </div>-->
                        </div>

                    </div>
                    <div class="text-center">
                        <button id="btn-cx" class="btn btn-primary"><i class="fa fa-search"></i>&nbsp;查询
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <div>
            <div style="width: 45%;height: 500px;float: left" id="pieContainer">
            </div>
            <div style="width: 45%;height: 500px;float: left" id="barContainer">
            </div>
        </div>
    </div>
</div>
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
<#include "/common/scriptfile.ftl"/>
<#include "/common/scriptfile_list.ftl"/>
<script src="${global.staticPath!}static/plugins/laydate/laydate.js"></script>
<script src="${global.staticPath!}static/plugins/echarts/echarts-all.js"></script>
<script>
    function searchP(obj) {
        const provinceArray = $(obj).val().replace(/\s*/g, "").split("-")
        const province = provinceArray[0]
        const beforeReturnPbm = provinceArray[1]
        $("#beforeAreaCode").val(beforeReturnPbm);
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
        $("#beforeAreaCode").val(beforeReturnCbm);
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
        $("#beforeAreaCode").val(beforeReturnXbm);
    }

    laydate.render({
        "elem": "#timeRange",
        "range": "至",
        "format": "yyyy-MM-dd",
        "trigger": "click",
        "done": function (value, startDate, endDate) {
            if (value) {
                $("#startTime").val(+new Date(startDate.year, startDate.month - 1, startDate.date));
                $("#endTime").val(+new Date(endDate.year, endDate.month - 1, endDate.date + 1));
            } else {
                $("#startTime").val("");
                $("#endTime").val("");
            }
        }
    });
    $(document).ready(function () {
        $("#btn-cx").on("click", function () {
            const data = $(this).parent().parent().serialize();
            $.ajax({
                "url": "a/analysis/beforeData.json",
                "type": "POST",
                "data": data,
                "dataType": "json",
                "cache": false,
                "async": false,
                "success": function (data) {
                    if (data.success) {
                        initPieData(data.result);
                        initBarData(data.result);
                    }
                }
            });
            return false;
        });
        $("#btn-cx").click();
    });

    //饼状图
    function initPieData(pieData) {
        var data = [];
        var temp = 0;
        var map = pieData.bingMap;
        for (var key in map) {
            // console.log("key : " + key + " value : " + map[key]);//控制台中打印
            data[temp] = {value: map[key], name: key}
            temp++
        }
        const piedom = document.getElementById("pieContainer");
        const pieChart = echarts.init(piedom);
        const pieOption = {
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b}: {c} ({d}%)'
            },
            legend: {
                orient: 'horizontal',
                left: 10,
                data: pieData.provinceNames
            },
            title: [{
                text: '总量10',
                top: 'center',
                left: 'center'
            }],
            series: [
                {
                    type: 'pie',
                    radius: ['50%', '70%'],
                    data: data
                }
            ]
        };
        if (pieOption && typeof pieOption === "object") {
            pieChart.setOption(pieOption, true);
        }
    }

    //柱状图
    function initBarData(barData) {
        console.log(barData.bingMap)
        var data = [];
        var temp = 0;
        var map = barData.bingMap;
        for (var key in map) {
            // console.log("key : " + key + " value : " + map[key]);//控制台中打印
            data[temp] = map[key]
            // {value: map[key], name: key}
            temp++
        }

        const bardom = document.getElementById("barContainer");
        const barChart = echarts.init(bardom);
        const barOption = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            // legend: {
            //     data: barData.provinceNames
            //     // data: ['自驾', '飞机', '火车', '客车' , '网约车']
            // },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: barData.provinceNames,
                    // axisLabel: {
                    //     interval: 0,
                    //     inside: true,
                    //     rotate: 30
                    // }
                    axisLabel: {
                        interval: 0,
                        inside: true,
                        formatter: function (value) {

                            return value.split("").join("\n")
                        }
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    type: 'bar',
                    stack: '数量',
                    data: data
                }
            ]
        };
        if (barOption && typeof barOption === "object") {
            barChart.setOption(barOption, true);
        }
    }
</script>
</body>
</html>