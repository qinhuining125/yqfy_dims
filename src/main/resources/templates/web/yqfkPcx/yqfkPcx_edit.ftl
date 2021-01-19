<#include "/common/head.ftl"/>
<link rel="stylesheet" href="${global.staticPath!}static/plugins/select2/select2.min.css">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content row">
  <div class="col-xs-12">
    <div class="form-middle">
      <form id="form-taskInfo" action="${mapping!}/saveData.json?${global.addSign!}=${(entity.id)!}"
            class="form-horizontal" method="post">
        <input type="hidden" name="id" class="form-control"
               value="${(entity.id)!?html}"/>
        <div class="form-group">
          <label class="col-xs-3 control-label"><span class="text-danger">*</span>地区编码：</label>
          <div class="col-xs-9">
            <input type="text" name="pcode" class="form-control"
                   value="${(entity.pcode)!?html}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-3 control-label"><span class="text-danger">*</span>地区名称：</label>
          <div class="col-xs-9">
            <input type="text" name="pname" class="form-control" placeholder="请输入名称"
                   value="${(entity.pname)!?html}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-3 control-label"><span class="text-danger">*</span>父级编码：</label>
          <div class="col-xs-9">
            <input type="text" name="parent" class="form-control" placeholder=""
                   value="${(entity.parent)!?html}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-3 control-label"><span class="text-danger">*</span>地区级别：</label>
          <div class="col-xs-9">
            <input type="text" name="plevel" class="form-control" placeholder=""
                   value="${(entity.plevel)!?html}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-3 control-label"><span class="text-danger">*</span>风险级别：</label>
          <div class="col-xs-9">
            <input type="text" name="valid" class="form-control" placeholder=""
                   value="${(entity.valid)!?html}"/>
          </div>
        </div>
      </form>
      </div>
    </div>
  </div>
</div>
<#include "/common/scriptfile.ftl"/>
<script src="${global.staticPath!}static/plugins/select2/select2.min.js"></script>
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
        "valid": {
          "required": true

        },

      },
      "messages": {
        "valid": {
          "required": "请填写风险级别",
        }
      }
    });
  });


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
