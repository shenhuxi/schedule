package com.zpself.scheduling.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author LiMingSu
 */
@Controller
@Api(tags = {"任务调度页面入口"})
public class QuartzWelcomeController {

    @GetMapping(value = {"api/jobManager"})
    @ApiOperation(value = "任务调度页面")
    public String welcome(Model model,@RequestHeader("Authorization") String authorization) {
        model.addAttribute("authorization", authorization);
        return "jobManager";
    }
}
