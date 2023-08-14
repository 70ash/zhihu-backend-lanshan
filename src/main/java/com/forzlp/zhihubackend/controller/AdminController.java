package com.forzlp.zhihubackend.controller;

import com.forzlp.zhihubackend.mapper.ProblemMapper;
import com.forzlp.zhihubackend.pojo.Page;
import com.forzlp.zhihubackend.pojo.Problem;
import com.forzlp.zhihubackend.service.ProblemService;
import com.forzlp.zhihubackend.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    public AdminController(ProblemService service) {
        this.service = service;
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
}
