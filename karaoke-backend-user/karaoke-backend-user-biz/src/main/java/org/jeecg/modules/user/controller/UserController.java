package org.jeecg.modules.user.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.user.entity.User;
import org.jeecg.modules.user.service.IUserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 用户模块
 * @Author: jeecg-boot
 * @Date:   2020-09-09
 * @Version: V1.0
 */
@Api(tags="用户模块")
@RestController
@RequestMapping("/user/user")
@Slf4j
public class UserController extends JeecgController<User, IUserService> {
	@Autowired
	private IUserService userService;

	/**
	 * 分页列表查询
	 *
	 * @param user
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "用户模块-分页列表查询")
	@ApiOperation(value="用户模块-分页列表查询", notes="用户模块-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(User user,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<User> queryWrapper = QueryGenerator.initQueryWrapper(user, req.getParameterMap());
		Page<User> page = new Page<User>(pageNo, pageSize);
		IPage<User> pageList = userService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param user
	 * @return
	 */
	@AutoLog(value = "用户模块-添加")
	@ApiOperation(value="用户模块-添加", notes="用户模块-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody User user) {
		userService.save(user);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param user
	 * @return
	 */
	@AutoLog(value = "用户模块-编辑")
	@ApiOperation(value="用户模块-编辑", notes="用户模块-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody User user) {
		userService.updateById(user);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户模块-通过id删除")
	@ApiOperation(value="用户模块-通过id删除", notes="用户模块-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		userService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "用户模块-批量删除")
	@ApiOperation(value="用户模块-批量删除", notes="用户模块-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.userService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户模块-通过id查询")
	@ApiOperation(value="用户模块-通过id查询", notes="用户模块-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		User user = userService.getById(id);
		if(user==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(user);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param user
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, User user) {
        return super.exportXls(request, user, User.class, "用户模块");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, User.class);
    }

}
