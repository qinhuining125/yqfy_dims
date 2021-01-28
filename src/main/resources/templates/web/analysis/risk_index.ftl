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
                                <label class="col-sm-4 control-label">返乡状态</label>
                                <div class="col-sm-7">
                                    <select name="returnState" class="form-control">
                                        <option value="">全部</option>
                                        <option value="已返乡">已返乡</option>
                                        <option value="拟返乡">拟返乡</option>
                                        <option value="不返乡">不返乡</option>
                                        <option value="已上报社区">已上报社区</option>
                                    </select>
                                </div>
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
<#include "/common/scriptfile.ftl"/>
<#include "/common/scriptfile_list.ftl"/>
<script src="${global.staticPath!}static/plugins/laydate/laydate.js"></script>
<script src="${global.staticPath!}static/plugins/echarts/echarts.min.js"></script>
<script>
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
                "url": "a/analysis/riskData.json",
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
        console.log(pieData)
        var sum =  pieData.dfx  + pieData.zfx + pieData.gfx
        var sumStr = "总数：" + pieData.sum
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
                data: [ '低风险', '中风险', '高风险']
            },
            title: [{
                text: sumStr,
                top: 'center',
                left: 'center'
            }],
            color: [ '#81c784', '#fff176', '#e57373'],
            series: [
                {
                    avoidLabelOverlap: false,
                    //数值和百分比显示
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                formatter: '{b} : {c} ({d}%)'
                            },
                            labelLine: {show: true}
                        }
                    },
                    labelLine: {
                        show: false
                    },
                    name: '数量和占比',
                    type: 'pie',
                    radius: ['35%', '55%'],
                    data: [
                        {value: pieData.dfx, name: '低风险'},
                        {value: pieData.zfx, name: '中风险'},
                        {value: pieData.gfx, name: '高风险'}
                    ]
                }
            ]
        };
        if (pieOption && typeof pieOption === "object") {
            pieChart.setOption(pieOption, true);
        }
    }

    //柱状图
    function initBarData(barData) {
        const bardom = document.getElementById("barContainer");
        const barChart = echarts.init(bardom);
        const barOption = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: [ '低风险', '中风险', '高风险']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '0%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: barData.villageNames,
                    axisLabel: {
                        // rotate: 40,
                        // inside: true,
                        interval: 0,
                        formatter: function (value) {
                            var str = "";
                            var num = 1; //每行显示字数
                            var valLength = value.length; //该项x轴字数
                            var rowNum = Math.ceil(valLength / num); // 行数
                            if (rowNum > 1) {
                                for (var i = 0; i < rowNum; i++) {
                                    var temp = "";
                                    var start = i * num;
                                    var end = start + num;

                                    temp = value.substring(start, end) + "\n";
                                    str += temp;
                                }
                                return str;
                            } else {
                                return value;
                            }
                        }
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLine: {    // 坐标轴 轴线
                        show: true,  // 是否显示
                    }
                }
            ],

            series: [
                {
                    name: '低风险',
                    type: 'bar',
                    stack: '数量',
                    data: barData.dfxs,
                    itemStyle: {
                        normal: {
                            color: '#81c784'
                        }
                    }
                },
                {
                    name: '中风险',
                    type: 'bar',
                    stack: '数量',
                    data: barData.zfxs,
                    itemStyle: {
                        normal: {
                            color: '#fff176'
                        }
                    }
                },
                {
                    name: '高风险',
                    type: 'bar',
                    stack: '数量',
                    data: barData.gfxs,
                    itemStyle: {
                        normal: {
                            color:  '#e57373'
                        }
                    }
                },
                {
                    name: '总数',
                    type: 'bar',
                    stack: '数量',
                    label: {
                        normal: {
                            offset: ['50', '80'],
                            show: true,
                            position: 'insideBottom',
                            formatter: '{c}',
                            textStyle: {color: '#000'}
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: 'rgba(128, 128, 128, 0)'
                        }
                    },
                    data: barData.sums
                    // data: barData.sum
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
