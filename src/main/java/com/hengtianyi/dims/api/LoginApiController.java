package com.hengtianyi.dims.api;

import com.hengtianyi.common.core.base.UserDTO;
import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.dims.service.api.ChooseOptionService;
import com.hengtianyi.dims.service.api.SysUserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hengtianyi.dims.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

/**
 * 登录api
 *
 * @author LY
 */
@RestController
@RequestMapping(value = "/api")
public class LoginApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginApiController.class);
  @Resource
  private SysUserService sysUserService;


  @Resource
  private ChooseOptionService chooseOptionService;

  /**
   * 登录
   *
   * @param dto 账号  密码
   * @return json+token
   */
  @PostMapping(value = "/login.json", produces = BaseConstant.JSON)
  public String login(@RequestBody UserDTO dto) {
    return sysUserService.appLogin(dto);
  }



  /**
   * 退出
   *
   * @param request HttpServletRequest
   * @return 重定向至登录页面
   */
  @GetMapping(value = "/logout")
  public String logout(HttpServletRequest request) {
    ServiceResult<Object> result = new ServiceResult<>();
    WebUtil.removeUser(request);
    request.getSession().invalidate();
    result.setSuccess(true);
    return result.toJson();
  }

  /**
   * 心跳测试接口
   *
   * @return json+token
   */
  @GetMapping(value = "/heart", produces = BaseConstant.JSON)
  public String heart() {
//    ServiceResult<Object> result = new ServiceResult<>();
//    result.setSuccess(true);
//    return result.toJson();


    //倒计时结束日期
    String timeEnd = chooseOptionService.getFirstCategoryByCode("TIMER_END").get(0).getName();


    String[] timeStr = timeEnd.split("-");
    int yyyy = Integer.parseInt(timeStr[0]);
    int MM = Integer.parseInt(timeStr[1]);
    int dd = Integer.parseInt(timeStr[2]);

    ServiceResult<Object> result = new ServiceResult<>();
    Calendar c;
    long endTime;
    Date date;
    long startTime;
    long midTime;
    try{
      c = Calendar.getInstance();
      c.set(yyyy, MM, dd, 0, 0, 0);// 注意月份的设置，0-11表示1-12月
      endTime = c.getTimeInMillis();
      date = new Date();
      startTime = date.getTime();
      midTime = (endTime - startTime) / 1000;
      if (midTime > 0) {
        midTime--;
        long ddLeft = midTime  / 24 / 60 / 60 ;
        long hh = midTime / 60 / 60 % 24;
        long mm = midTime / 60 % 60;
        long ss = midTime % 60;
        System.out.println("还剩余" + ddLeft + "天" + hh + "小时" + mm + "分钟" + ss + "秒");
        String returnTimer = ddLeft +"";
        result.setResult(returnTimer);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      result.setSuccess(Boolean.TRUE);
    }catch (Exception e) {
      LOGGER.error("[查询待办数量]出错,{}", e.getMessage(), e);
      result.setError("查询待办数量出错");
    }
    return result.toJson();
  }



}
