package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.mapper.ProblemMapper;
import com.forzlp.zhihubackend.pojo.Page;
import com.forzlp.zhihubackend.pojo.Problem;
import com.forzlp.zhihubackend.pojo.User;
import com.forzlp.zhihubackend.service.ProblemService;
import com.forzlp.zhihubackend.service.UserService;
import com.forzlp.zhihubackend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zlp
 * @date 2023/8/4 22:11
 * 管理员进行操作
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private ProblemService service;
    private UserService userService;
    @Autowired
    public AdminController(ProblemService service,UserService userService) {
        this.service = service;
        this.userService = userService;
    }
    // 删除问题
    @DeleteMapping("delProblem")
    public Result delProblem(Long id) {
        int i = service.delProblemById(id);
        if(i == 1) {
            log.info("删除成功");
            return Result.success();
        }else {
            log.info("删除失败");
            return Result.fail();
        }
    }
    // 取消会员身份
    @PutMapping("delMember")
    public Result delMember(Long id) {
        User user = userService.getById(id);
        if(user.getIsMember() == 0) return Result.fail("取消会员失败,也许你还不是一个会员");
        int i = userService.delMember(id);
        if(i == 1) {
            return Result.success("取消会员成功");
        }else {
            return Result.fail("取消会员失败,也许你还不是一个会员");
        }
    }
}
