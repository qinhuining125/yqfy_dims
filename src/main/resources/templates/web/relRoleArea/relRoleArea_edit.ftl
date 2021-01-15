<#include "/common/head.ftl"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content row">
  <div class="col-xs-12">
    <div class="form-middle">
      <form id="form-relRoleArea"
            action="${mapping!}/saveData.json?${global.addSign!}=${(entity.id)!}"
            class="form-horizontal" method="post">
        <input type="hidden" name="id" value="${(entity.id)!}"/>
        <div class="col-xs-12">

          <div class="form-group">
            <label class="col-xs-3 control-label"><span class="text-danger">*</span>roleId：</label>
            <div class="col-xs-9">
              <input type="text" name="roleId" class="form-control" placeholder="请输入roleId"
                     value="${(entity.roleId)!}"/><span class="help-block m-b-none">
              </span>
            </div>
          </div>

          <div class="form-group">
            <label class="col-xs-3 control-label"><span
                  class="text-danger">*</span>areaCode：</label>
            <div class="col-xs-9">
              <input type="text" name="areaCode" class="form-control" placeholder="请输入areaCode"
                     value="${(entity.areaCode)!}"/><span class="help-block m-b-none">
              </span>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<#include "/common/scriptfile.ftl"/>
<script>
  var formObj;
  $(document).ready(function () {
    formObj = $("#form-relRoleArea");
    <#-- 这是是作为一个表单验证的示例，针对具体的逻辑进行验证 -->
    formObj.validate({
      "rules": {
        "name": {
          "required": true,
          "minlength": 2,
          "maxlength": 20
        },
        "url": {
          "required": true
        }
      },
      "messages": {
        "name": {
          "required": "请输入名称",
          "minlength": "长度不能小于2",
          "maxlength": "长度不能大于20"
        },
        "url": {
          "required": "请输入地址"
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
