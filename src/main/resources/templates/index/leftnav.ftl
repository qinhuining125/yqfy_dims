<nav class="navbar-default navbar-static-side" role="navigation">
  <div class="nav-close">
    <i class="fa fa-times-circle"></i>
  </div>
  <div class="sidebar-collapse">
    <ul class="nav" id="side-menu">
      <li>
        <div class="dropdown profile-element" style="color:#fff;border-bottom:1px solid #3b4a5a">
          <div class="nav-top-h1"
               style="height:auto;padding:20px;border-bottom: 1px solid #283142;line-height:12px">
            <br>
            <a href="#">寿阳县疫情防控信息平台</a>
          </div>
        </div>
        <div class="logo-element">
        </div>
      </li>
        <#if global.hasPermissions("sys:manage:index")>
          <li>
            <a href="#"><i class="fa fa-columns"></i><span class="nav-label">系统管理</span>
              <span class="fa arrow"></span></a>
            <ul class="nav nav-second-level">
              <li><a class="J_menuItem" href="/a/sysUser/index.html">用户管理</a></li>
              <li><a class="J_menuItem" href="/a/sysRole/index.html">用户角色</a></li>
              <li><a class="J_menuItem" href="/a/sysLog/index.html">系统日志</a></li>
            </ul>
          </li>
        </#if>

      <li>
        <a href="#"><i class="fa fa-edit"></i><span class="nav-label">疫情防控管理</span>
          <span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
          <li><a class="J_menuItem" href="/a/yqfkJZRegister/index.html">接种情况</a></li>
          <li><a class="J_menuItem" href="/a/yqfkRegister/index.html">疫情防控</a></li>
          <li><a class="J_menuItem" href="/a/yqfkPcx/index.html">中高危险地区维护</a></li>
        </ul>
      </li>

      <li>
        <a href="#"><i class="fa fa-magic"></i><span class="nav-label">流程管理</span>
          <span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
          <li><a class="J_menuItem" href="/a/flow/yqfkFlow.html">疫情防控管理流程</a></li>
        </ul>
      </li>

      <li>
        <a href="#"><i class="fa fa-bar-chart-o"></i><span class="nav-label">疫苗接种登记统计分析</span>
          <span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
          <li><a class="J_menuItem" href="/a/analysis/yqfkJZstatus.html">按照接种状态统计</a></li>
          <li><a class="J_menuItem" href="/a/analysis/yqfkNoJZstatus.html">按照不接种原因统计</a></li>
          <li><a class="J_menuItem" href="/a/analysis/yqfkJZzzmm.html">按照政治面貌统计</a></li>
          <li><a class="J_menuItem" href="/a/analysis/yqfkJZzzdw.html">按照工作单位统计</a></li>
          <li><a class="J_menuItem" href="/a/analysis/yqfkJZplace.html">按照接种地点统计</a></li>
        </ul>
      </li>

      <li>
        <a href="#"><i class="fa fa-bar-chart-o"></i><span class="nav-label">来返寿登记统计分析</span>
          <span class="fa arrow"></span></a>
        <ul class="nav nav-second-level">
          <li><a class="J_menuItem" href="/a/analysis/status.html">按照状态统计</a></li>
          <li><a class="J_menuItem" href="/a/analysis/industry.html">按照人员类别统计</a></li>
          <li><a class="J_menuItem" href="/a/analysis/vehicle.html">按照交通工具统计</a></li>
          <li><a class="J_menuItem" href="/a/analysis/risk.html">按照风险等级统计</a></li>
          <li><a class="J_menuItem" href="/a/analysis/before.html">按照返乡前地址统计</a></li>
        </ul>
      </li>

    </ul>
  </div>
</nav>
<#-- 这个是必须要留存的，用于JS触发Tab页 -->
<a class="J_menuItem" href="#" id="top-hidden-link" style="display: none"></a>