package org.jeecg.modules.user.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.user.entity.User;
import org.jeecg.modules.user.utlis.AppUtils;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * <P>
 *     用户验证模块
 * </P>
 * @Description: 用户验证模块
 * @Author: jeecg-boot
 * @Date: 2020-09-09
 * @Version: V1.0
 */
@Api(tags = "用户验证模块")
@RestController
@RequestMapping("/user/Logo")
@Slf4j
public class LogoController {

    /**
     * 通过code获取登录信息。
     *
     * @param code
     * @param anonymous_code
     * @return
     */
    @AutoLog(value = "用户验证模块-Code信息验证")
    @ApiOperation(value = "用户验证模块-Code信息验证", notes = "用户验证模块-Code信息验证")
    @GetMapping(value = "/querycode")
    public Result<?> queryCode(@RequestParam String code, @RequestParam String anonymous_code, HttpServletRequest request) {
        String url = "https://developer.toutiao.com/api/apps/jscode2session";

        //声明参数
        // {
        //  "appid": "31198cf00b********",
        //  "secret": "942bd785dbf1fbffaaed37bb05d0968d********",
        //  "code": "6d4bd3c8********"
        //}
        Map<String,String> map = new HashMap<>();
        if (!StringUtils.isEmpty(code)){
            map.put("code", code);
        }else {
            map.put("anonymous_code", anonymous_code);
        }
        map.put("secret", AppUtils.secret);
        map.put("appid",AppUtils.appid );
        //发送get请求
        String result = AppUtils.sendGet(url, map);
        //取出返回参数
        Map<String,Object> resultmap = JSON.parseObject(result,Map.class);
        //判断是否获取成功
        Integer error = (Integer) resultmap.get("error");
        if (!(0 == error)){
            log.info("登录失败信息："+resultmap.toString());
            return Result.error(-1, "登录失败,请重新登录！");
        }
        String session_key = (String) resultmap.get("session_key");
        String openid = (String) resultmap.get("openid");
        String anonymous_openid = (String) resultmap.get("anonymous_openid");

        return Result.ok(resultmap);
    }


}
