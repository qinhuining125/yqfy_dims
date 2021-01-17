package com.hengtianyi.dims.api;

import com.hengtianyi.common.core.base.UserDTO;
import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.dims.service.api.SysUserService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
   * 心跳测试接口
   *
   * @return json+token
   */
  @GetMapping(value = "/heart", produces = BaseConstant.JSON)
  public String heart() {
    ServiceResult<Object> result = new ServiceResult<>();
    result.setSuccess(true);
    return result.toJson();
  }
}
