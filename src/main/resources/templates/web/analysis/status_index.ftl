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
                "url": "a/analysis/statusData.json",
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
        var sum = pieData.beenhome + pieData.planhome + pieData.nohome + pieData.report
        var sumStr = "总数：" + sum
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
                data: ['已返乡', '拟返乡', '不返乡', '已上报社区']
            },
            title: [{
                text: sumStr,
                top: 'center',
                left: 'center'
            }],
            color: ['rgba(223,123,250,0.64)', 'rgba(255,132,0,0.66)', 'rgba(31,39,194,0.66)', 'rgba(210,42,95,0.66)'],
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
                    radius: ['30%', '50%'],
                    data: [
                        {value: pieData.beenhome, name: '已返乡'},
                        {value: pieData.planhome, name: '拟返乡'},
                        {value: pieData.nohome, name: '不返乡'},
                        {value: pieData.report, name: '已上报社区'}
                    ],

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
                data: ['已返乡', '拟返乡', '不返乡', '已上报社区']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '10%',
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
                    name: '已返乡',
                    type: 'bar',
                    stack: '数量',
                    emphasis: {
                        focus: 'series'
                    },
                    data: barData.beenhomes,
                    itemStyle: {
                        normal: {
                            color: 'rgba(223,123,250,0.64)'
                        }
                    }
                },
                {
                    name: '拟返乡',
                    type: 'bar',
                    stack: '数量',
                    emphasis: {
                        focus: 'series'
                    },
                    data: barData.planhomes,
                    itemStyle: {
                        normal: {
                            color: 'rgba(255,132,0,0.66)'
                        }
                    }
                },
                {
                    name: '不返乡',
                    type: 'bar',
                    stack: '数量',
                    emphasis: {
                        focus: 'series'
                    },
                    data: barData.nohomes,
                    itemStyle: {
                        normal: {
                            color: 'rgba(31,39,194,0.66)'
                        }
                    }
                },
                {
                    name: '已上报社区',
                    type: 'bar',
                    stack: '数量',
                    emphasis: {
                        focus: 'series'
                    },
                    data: barData.reports,
                    itemStyle: {
                        normal: {
                            color: 'rgba(210,42,95,0.66)'
                        }
                    }
                },
                //series中push合计的数据
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
                            textStyle: {color: '#000000'}
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: 'rgba(128,128,128,0)'
                        }
                    },
                    data: barData.sum
                }
                // data: [2,2,2,2,2,2,2,2,2,2,2,5,5,5]
            ]
        };
        if (barOption && typeof barOption === "object") {
            barChart.setOption(barOption, true);
        }
    }
</script>
</body>
</html>
<#--data: ['冷链从业人员', '商业从业人员', '货运物流', '学生' , '机关事业单位', '无业', '其它']-->
<#--"url": "a/analysis/vehicleData.json",-->
<#--"url": "a/analysis/industryData.json",-->

<#--{value: pieData.lenglian, name: '冷链从业人员'},-->
<#--{value: pieData.business, name: '商业从业人员'},-->
<#--{value: pieData.huoyun, name: '货运物流'},-->
<#--{value: pieData.student, name: '学生'},-->
<#--{value: pieData.jiguan, name: '机关事业单位'},-->
<#--{value: pieData.wuye, name: '无业'},-->
<#--{value: pieData.other, name: '其它'}-->


<#--{value: pieData.zj, name: '自驾'},-->
<#--{value: pieData.planej, name: '飞机'},-->
<#--{value: pieData.train, name: '火车'},-->
<#--{value: pieData.bus, name: '客车'},-->
<#--{value: pieData.wybus, name: '网约车'}-->

<#--data: ['自驾', '飞机', '火车', '客车' , '网约车']-->