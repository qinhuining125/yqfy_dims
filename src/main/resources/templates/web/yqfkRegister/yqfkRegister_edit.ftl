<#include "/common/head.ftl"/>
<link rel="stylesheet" href="${global.staticPath!}static/plugins/select2/select2.min.css">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content row">
  <div class="col-xs-12">
    <div class="form-middle">
      <form id="form-taskInfo" action="${mapping!}/saveData.json?${global.addSign!}=${(entity.id)!}"
            class="form-horizontal" method="post">
        <input type="text" name="id" value="${(entity.id)!}"/>
        <input type="text" name="receiveId" id="receiveId" value=""/>
        <input type="text" name="receiveRoleId" id="receiveRoleId" value=""/>

      </form>


      </div>
    </div>
  </div>
</div>
<#include "/common/scriptfile.ftl"/>
<script src="${global.staticPath!}static/plugins/select2/select2.min.js"></script>
<script id="template-villages" type="text/x-handlebars-template">
  <option value="">--请选择--</option>
  {{#each this}}
  <option value="{{userId}}">{{areaName}}</option>
  {{/each}}
</script>
<script>
  var formObj;
  $(document).ready(function () {
    $('#valliage').select2({
      placeholder:"--请选择--",
      multiple : true
    });
    formObj = $("#form-taskInfo");
    <#-- 这是是作为一个表单验证的示例，针对具体的逻辑进行验证 -->
    formObj.validate({
      "rules": {
        "receiveId": {
          "required": true
        },
        "receiveRoleId": {
          "required": true
        },
        "township": {
          "required": true
        },
        "valliage": {
          "required": true
        },
        "content": {
          "required": true
        },

      },
      "messages": {
        "receiveId": {
          "required": "请选择",
        },
        "receiveRoleId": {
          "required": "请选择",
        },
        "township": {
          "required": "请选择",
        },
        "valliage": {
          "required": "请选择",
        },
        "content": {
          "required": "请输入内容"
        }
      }
    });
  });



  function searchVillage(obj) {
    const township = $(obj).val();
    if (township == '') {
      return false;
    }
    const array = township.split(",");
    $("#receiveId").val(array[0]);
    $("#receiveRoleId").val(1003);
    $.ajax({
      "type": "GET",
      "cache": false,
      "url": "/a/village/list.json",
      "data": {
        "areaCode": array[1]
      },
      "dataType": "json",
      "success": function (data) {
        const template = Handlebars.compile($("#template-villages").html());
        if (data.result==null){
          alert(请选择村)
        }
        $("#valliage").html(template(data.result));
      }
    });
  }

  function choseArea(obj) {
    const userId = $(obj).val();
    if (userId == '') {
      const township = $("#township").val();
      $("#receiveId").val(township.split(",")[0]);
      $("#receiveRoleId").val(1003);
    } else {
      $("#receiveId").val(userId);
      $("#receiveRoleId").val(1002);
    }
  }

  /**
   * 添加和修改
   */
  <#-- 必须有的方法，在这里提交 -->
  function subSave() {
    if (formObj.valid()) {
      // 内部窗口获取layer的索引
      var layerIndex = parent.layer.getFrameIndex(window.name);
      CommonUtils.AjaxUtil.CommonAjaxData(formObj.attr("action"), formObj.serializeJson(), {
        "loadingType": 2,
        "successCallback": function (data) {
          <#-- 成功后刷新列表页的Gird，这个refreshTable方法定义在main-list.js -->
          top.layerCallerWD.get().window.refreshTable();
          window.setTimeout(function () {
            top.layer.close(layerIndex);
          }, 50);
        }
      });
    }
  }
</script>
</body>
</html>
